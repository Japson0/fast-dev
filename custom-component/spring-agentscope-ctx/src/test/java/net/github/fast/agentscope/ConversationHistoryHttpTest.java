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
                            if (turn == 2) {
                                assertThat(messages.stream().filter(m -> "remember-marker".equals(m.getTextContent())))
                                        .hasSize(1);
                                assertThat(messages.stream().filter(m -> "reply-1".equals(m.getTextContent())))
                                        .hasSize(1);
                                assertThat(messages.stream().filter(m -> "continue".equals(m.getTextContent())))
                                        .hasSize(1);
                            }
                            return Flux.just(ChatResponse.builder().content(List.of(TextBlock.builder()
                                    .text("reply-" + turn).build())).finishReason("stop").build());
                        }
                    };
                }
                public void configure(HarnessAgent.Builder builder) {
                    builder.sysPrompt("private-system-prompt").disableSubagents().disableCompaction();
                }
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
            assertThat(run(base, id, "run-1", "u1", "remember-marker"))
                    .contains("reply-1", "RUN_FINISHED").doesNotContain("RUN_ERROR");
            String path = base + "/agents/demo/sessions/" + id;
            var detail = get(path).path("data");
            assertThat(detail.path("threadId").asText()).isEqualTo(id);
            assertThat(detail.path("hasMore").asBoolean()).isFalse();
            assertThat(detail.path("messages").size()).isEqualTo(2);
            assertThat(detail.path("messages").get(0).path("role").asText()).isEqualTo("user");
            assertThat(detail.path("messages").get(0).path("content").asText()).isEqualTo("remember-marker");
            assertThat(detail.path("messages").get(1).path("role").asText()).isEqualTo("assistant");
            assertThat(detail.path("messages").get(1).path("content").asText()).isEqualTo("reply-1");
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
            assertThat(restored.values()).extracting(message -> message.path("content").asText())
                    .containsExactly("remember-marker", "reply-1");
            assertThat(context.getBean(AtomicInteger.class)).hasValue(1);

            var listBody = post(base + "/agents/demo/sessions/list",
                    "{\"page\":0,\"size\":5,\"queryParams\":{\"title\":\"Demo\"}}");
            assertThat(listBody.path("result").asBoolean()).isTrue();
            assertThat(listBody.path("code").asText()).isEqualTo("00000");
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
            assertThat(context.getBean(AtomicInteger.class)).hasValue(2);
            assertThat(get(path).path("data").path("messages").size()).isEqualTo(4);
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
        String body = """
                {"threadId":"%s","runId":"%s","messages":[{"id":"%s","role":"user","content":"%s"}],
                 "tools":[],"context":[],"state":{},"forwardedProps":{}}
                """.formatted(id, run, messageId, text);
        var response = http.send(HttpRequest.newBuilder(URI.create(base + "/agui/run/demo"))
                .timeout(Duration.ofSeconds(30)).header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body)).build(), HttpResponse.BodyHandlers.ofString());
        assertThat(response.statusCode()).isEqualTo(200);
        return response.body();
    }
}
