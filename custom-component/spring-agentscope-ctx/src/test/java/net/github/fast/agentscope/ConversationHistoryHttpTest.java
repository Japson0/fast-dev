package net.github.fast.agentscope;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.agentscope.core.message.Msg;
import io.agentscope.core.message.TextBlock;
import io.agentscope.core.model.*;
import io.agentscope.harness.agent.HarnessAgent;
import java.net.URI;
import java.net.http.*;
import java.nio.file.Path;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import reactor.core.publisher.Flux;
import static org.assertj.core.api.Assertions.assertThat;

class ConversationHistoryHttpTest {
    @TempDir Path workspace;
    private final JsonMapper json = JsonMapper.builder().build();
    private final HttpClient http = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();

    @Configuration(proxyBeanMethods = false)
    @EnableAutoConfiguration
    @Import(AgentScopeAutoConfiguration.class)
    static class Application {
        @Bean DataSource dataSource() {
            var source = new DriverManagerDataSource("jdbc:h2:mem:" + UUID.randomUUID()
                    + ";DB_CLOSE_DELAY=-1", "sa", "");
            new ResourceDatabasePopulator(new ClassPathResource("agentscope/agent-conversation.sql")).execute(source);
            return source;
        }
        @Bean UserContextProvider users() { return () -> new UserContext(1L, 10L); }
        @Bean AtomicInteger modelCalls() { return new AtomicInteger(); }
        @Bean HarnessAgentDefinition definition(AtomicInteger calls) {
            return new HarnessAgentDefinition() {
                public String id() { return "demo"; }
                public String name() { return "Demo"; }
                public String description() { return "Offline history verification"; }
                protected String modelName() { return "offline"; }
                protected Model model() {
                    return new Model() {
                        public String getModelName() { return "offline"; }
                        public Flux<ChatResponse> stream(List<Msg> messages, List<ToolSchema> tools, GenerateOptions options) {
                            int turn = calls.incrementAndGet();
                            boolean continuation = messages.stream().map(Msg::getTextContent)
                                    .filter(java.util.Objects::nonNull).anyMatch(text -> text.contains("continue"));
                            if (continuation) {
                                assertThat(messages.stream().map(Msg::getTextContent).filter(java.util.Objects::nonNull)
                                        .anyMatch(text -> text.contains("demo/report.pdf")))
                                        .as("model messages: %s", messages.stream().map(Msg::getTextContent).toList()).isTrue();
                            }
                            return Flux.just(ChatResponse.builder().content(List.of(TextBlock.builder()
                                    .text(continuation ? "reply-2" : "reply-1").build())).finishReason("stop").build());
                        }
                    };
                }
                public void configure(HarnessAgent.Builder builder) {
                    builder.sysPrompt("private-system-prompt").disableSubagents().disableCompaction();
                }
                @Override public boolean enableParseImageAttractFile() { return false; }
            };
        }
    }

    @Test
    void restoresAguiHistoryAndContinuesWithOnlyNewMessageAfterAgentRecreation() throws Exception {
        try (var context = new SpringApplicationBuilder(Application.class).run("--server.port=0",
                "--server.address=127.0.0.1",
                "--fast.agentscope.workspace=" + workspace, "--fast.agentscope.session-log.auto-create-table=true",
                "--spring.main.banner-mode=off")) {
            String base = "http://127.0.0.1:"
                    + ((ServletWebServerApplicationContext) context).getWebServer().getPort() + "/api";
            String id = UUID.randomUUID().toString();
            assertThat(run(base, id, "run-1", "u1", "first message", true))
                    .contains("reply-1", "RUN_FINISHED").doesNotContain("RUN_ERROR", "AGUI_DOCUMENT");
            String path = base + "/agents/demo/sessions/" + id;
            var detail = get(path).path("data");
            assertThat(detail.path("threadId").asText()).isEqualTo(id);
            assertThat(detail.path("hasMore").asBoolean()).isFalse();
            var userMessage = detail.path("messages").get(0);
            assertThat(userMessage).as("history: %s", detail).isNotNull();
            assertThat(userMessage.path("content").isArray()).as("persisted history: %s", detail).isTrue();
            var document = java.util.stream.StreamSupport.stream(userMessage.path("content").spliterator(), false)
                    .filter(part -> "document".equals(part.path("type").asText())).findFirst().orElseThrow();
            assertThat(document.path("metadata").path("objectKey").asText()).isEqualTo("demo/report.pdf");
            assertThat(document.path("metadata").path("fileName").asText()).isEqualTo("report.pdf");
            assertThat(document.path("metadata").path("tag").asText()).isEqualTo("kept");
            assertThat(detail.has("events")).isFalse();
            assertThat(detail.toString()).doesNotContain("private-system-prompt", "textContent");

            long cursor = 0;
            boolean hasMore;
            var restored = new LinkedHashMap<String, JsonNode>();
            do {
                var page = get(path + "?limit=1&after=" + cursor).path("data");
                assertThat(page.path("cursor").asLong()).isGreaterThan(cursor);
                cursor = page.path("cursor").asLong();
                page.path("messages").forEach(message -> restored.put(message.path("id").asText(), message));
                hasMore = page.path("hasMore").asBoolean();
            } while (hasMore);
            var pagedUser = restored.values().stream().filter(message -> "user".equals(message.path("role").asText())).findFirst().orElseThrow();
            assertThat(pagedUser.path("content").get(0).path("text").asText()).isEqualTo("first message");
            assertThat(pagedUser.path("content").get(1).path("type").asText()).isEqualTo("document");
            assertThat(pagedUser.path("content").get(1).path("metadata").path("tag").asText()).isEqualTo("kept");
            assertThat(restored.values()).extracting(message -> message.path("role").asText()).contains("assistant");

            var listBody = post(base + "/agents/demo/sessions/list",
                    "{\"page\":0,\"size\":5,\"queryParams\":{\"title\":\"Demo\"}}");
            assertThat(listBody.path("result").asBoolean()).isTrue();
            assertThat(listBody.path("code").asText()).isEqualTo("200");
            assertThat(listBody.path("data").path("total").asLong()).isEqualTo(1);
            assertThat(listBody.path("data").path("records").size()).isEqualTo(1);
            assertThat(listBody.path("data").path("records").get(0).path("sessionId").asText()).isEqualTo(id);
            assertThat(listBody.path("data").has("queryParams")).isFalse();
            var emptyFilter = post(base + "/agents/demo/sessions/list",
                    "{\"page\":0,\"size\":5,\"queryParams\":{\"title\":\"nope\"}}");
            assertThat(emptyFilter.path("data").path("total").asLong()).isZero();
            var agentsBody = get(base + "/agents");
            assertThat(agentsBody.path("data").size()).isEqualTo(1);
            assertThat(agentsBody.path("data").get(0).path("id").asText()).isEqualTo("demo");
            var detailAgain = get(path).path("data");
            assertThat(detailAgain.path("threadId").asText()).isEqualTo(id);

            var service = context.getBean(ConversationService.class);
            context.getBean(AgentRegistry.class).release(service.require("demo", id, new UserContext(1L, 10L)));
            assertThat(run(base, id, "run-2", "u2", "continue"))
                    .contains("reply-2", "RUN_FINISHED").doesNotContain("RUN_ERROR");
        }
    }

    private JsonNode get(String url) throws Exception {
        var response = http.send(HttpRequest.newBuilder(URI.create(url)).timeout(Duration.ofSeconds(30)).GET().build(),
                HttpResponse.BodyHandlers.ofString());
        assertThat(response.statusCode()).isEqualTo(200);
        return json.readTree(response.body());
    }
    private JsonNode post(String url, String body) throws Exception {
        var response = http.send(HttpRequest.newBuilder(URI.create(url)).timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body)).build(), HttpResponse.BodyHandlers.ofString());
        assertThat(response.statusCode()).isEqualTo(200);
        return json.readTree(response.body());
    }

    private String run(String base, String id, String run, String messageId, String text) throws Exception {
        return run(base, id, run, messageId, text, false);
    }

    private String run(String base, String id, String run, String messageId, String text, boolean document)
            throws Exception {
        String content = document
                ? "[{\"type\":\"text\",\"text\":\"%s\"},{\"type\":\"document\",\"source\":{\"type\":\"url\",\"value\":\"https://files.example.com/report.pdf\",\"mimeType\":\"application/pdf\"},\"metadata\":{\"objectKey\":\"demo/report.pdf\",\"fileName\":\"report.pdf\",\"tag\":\"kept\"}}]".formatted(text)
                : "\"%s\"".formatted(text);
        String body = """
                {"threadId":"%s","runId":"%s","messages":[{"id":"%s","role":"user","content":%s}],
                 "tools":[],"context":[],"state":{},"forwardedProps":{}}
                """.formatted(id, run, messageId, content);
        var response = http.send(HttpRequest.newBuilder(URI.create(base + "/agui/run/demo"))
                .timeout(Duration.ofSeconds(30)).header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body)).build(), HttpResponse.BodyHandlers.ofString());
        assertThat(response.statusCode()).isEqualTo(200);
        return response.body();
    }
}
