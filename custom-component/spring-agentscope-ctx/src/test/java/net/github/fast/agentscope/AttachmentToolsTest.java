package net.github.fast.agentscope;

import io.agentscope.core.agent.RuntimeContext;
import io.agentscope.core.message.Msg;
import io.agentscope.core.message.MsgRole;
import io.agentscope.core.message.TextBlock;
import io.agentscope.core.message.ToolResultBlock;
import io.agentscope.core.message.ToolUseBlock;
import io.agentscope.core.model.ChatResponse;
import io.agentscope.core.model.GenerateOptions;
import io.agentscope.core.model.Model;
import io.agentscope.core.model.ModelRegistry;
import io.agentscope.core.model.ToolSchema;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import static org.assertj.core.api.Assertions.assertThat;

class AttachmentToolsTest {
    @AfterEach void resetModels() { ModelRegistry.reset(); }
    @Test
    void attachmentPromptsRequireAnsweringTheUserQuestion() {
        var owner = new UserContext(7L, 9L);
        Model vision = new Model() {
            @Override public String getModelName() { return "offline-vision"; }
            @Override public Flux<ChatResponse> stream(List<Msg> messages, List<ToolSchema> tools, GenerateOptions options) {
                return answer("recognized image");
            }
        };
        ModelRegistry.register("offline-vision", vision);
        var imageOnly = new DocumentAttachmentMiddleware("demo", false, true, owner, null, "offline-vision");
        var textOnly = new DocumentAttachmentMiddleware("demo", true, false, owner,
                (agentId, objectKey, user) -> null, "offline-vision");
        var both = new DocumentAttachmentMiddleware("demo", true, true, owner,
                (agentId, objectKey, user) -> null, "offline-vision");
        for (var middleware : List.of(imageOnly, textOnly, both)) {
            String prompt = middleware.onSystemPrompt(null, runtime(owner, "prompt-session", "demo"), "base")
                    .block();
            assertThat(prompt).contains("必须结合 data.content 回答用户关于附件的问题", "不要只报告读取或识别成功")
                    .doesNotContain("安全策略", "即使用户要求查看也不例外");
        }
    }


    @Test
    void dispatchesTextAttachmentToolAndReturnsParsedContentToModel() {
        var owner = new UserContext(7L, 9L);
        var calls = new AtomicInteger();
        var parsedResultSeen = new AtomicBoolean();
        var fileCall = new java.util.concurrent.atomic.AtomicReference<String>("");
        Model model = new Model() {
            @Override public String getModelName() { return "offline-text-tool"; }
            @Override public Flux<ChatResponse> stream(List<Msg> messages, List<ToolSchema> tools, GenerateOptions options) {
                if (calls.getAndIncrement() == 0) {
                    assertThat(tools).extracting(ToolSchema::getName).contains("read_text_attachment");
                    return toolCall("read_text_attachment", Map.of("objectKey", "demo/report.pdf"));
                }
                parsedResultSeen.set(toolResults(messages).contains("parsed report body"));
                fileCall.set("tool-result-visible=" + parsedResultSeen.get());
                return answer("done");
            }
        };
        ModelRegistry.register("offline-text-tool", model);
        var definition = new HarnessAgentDefinition() {
            @Override public String id() { return "demo"; }
            @Override public String name() { return "Demo"; }
            @Override public String description() { return "attachment test"; }
            @Override protected String modelName() { return "offline-text-tool"; }
            @Override protected Model model() { return model; }
            @Override public boolean enableParseTextAttractFile() { return true; }
            @Override public void configure(io.agentscope.harness.agent.HarnessAgent.Builder builder) {
                builder.disableSubagents().disableCompaction();
            }
        };
        AttachmentFileService fileService = (agentId, objectKey, user) -> {
            assertThat(agentId).isEqualTo("demo");
            assertThat(objectKey).isEqualTo("demo/report.pdf");
            assertThat(user).isEqualTo(owner);
            return new AttachmentFileService.ParsedAttachment(true, "00000", null, objectKey, "report.pdf",
                    "application/pdf", "parsed report body", Map.of(), false);
        };
        var runtime = runtime(owner, "text-session", "demo");
        try (var agent = definition.build(runtime, builder -> builder.workspace(Path.of("target/attachment-tool-text")),
                new AgentScopeProperties(), fileService)) {
            assertThat(call(agent, runtime, "Summarize attached report").getTextContent()).isEqualTo("done");
            assertThat(parsedResultSeen).isTrue();
        }
    }

    @Test
    void dispatchesImageToolThroughDedicatedVisionModel() {
        var visionCalls = new AtomicInteger();
        Model vision = new Model() {
            @Override public String getModelName() { return "offline-vision-tool"; }
            @Override public Flux<ChatResponse> stream(List<Msg> messages, List<ToolSchema> tools, GenerateOptions options) {
                assertThat(messages.get(0).getContent().stream()
                        .anyMatch(block -> block instanceof io.agentscope.core.message.ImageBlock image
                                && "https://files.example.com/photo.png".equals(((io.agentscope.core.message.URLSource) image.getSource()).getUrl())))
                        .isTrue();
                visionCalls.incrementAndGet();
                return answer("recognized image text");
            }
        };
        ModelRegistry.register("offline-vision-tool", vision);
        var calls = new AtomicInteger();
        Model main = new Model() {
            @Override public String getModelName() { return "offline-image-tool"; }
            @Override public Flux<ChatResponse> stream(List<Msg> messages, List<ToolSchema> tools, GenerateOptions options) {
                if (calls.getAndIncrement() == 0) {
                    assertThat(tools).extracting(ToolSchema::getName).contains("read_image_attachment");
                    return toolCall("read_image_attachment", Map.of("objectKey", "demo/photo.png",
                            "fileUrl", "https://files.example.com/photo.png"));
                }
                return answer("recognized");
            }
        };
        ModelRegistry.register("offline-image-tool", main);
        var definition = new HarnessAgentDefinition() {
            @Override public String id() { return "demo"; }
            @Override public String name() { return "Demo"; }
            @Override public String description() { return "image tool test"; }
            @Override protected String modelName() { return "offline-image-tool"; }
            @Override protected Model model() { return main; }
            @Override public boolean enableParseImageAttractFile() { return true; }
            @Override public void configure(io.agentscope.harness.agent.HarnessAgent.Builder builder) {
                builder.disableSubagents().disableCompaction();
            }
        };
        var properties = new AgentScopeProperties();
        properties.setVisionModelName("offline-vision-tool");
        var runtime = runtime(new UserContext(7L, 9L), "image-session", "demo");
        try (var agent = definition.build(runtime, builder -> builder.workspace(Path.of("target/attachment-tool-image")), properties)) {
            assertThat(call(agent, runtime, "Describe this picture").getTextContent()).isEqualTo("recognized");
            assertThat(visionCalls).hasValue(1);
            assertThat(calls.get()).isGreaterThanOrEqualTo(2);
        }
    }

    @Test
    void rejectsTextKeyOutsideAgentDirectoryWithoutCallingFileService() {
        var owner = new UserContext(1L, 2L);
        var tool = new ReadTextAttachmentTool("demo", owner, (agentId, objectKey, trustedOwner) -> {
            throw new AssertionError("file service must not receive a foreign key");
        });
        var result = tool.readTextAttachment("other/secret.pdf");
        assertThat(result.getOutput()).extracting(block -> ((TextBlock) block).getText())
                .anyMatch(text -> text.contains("00001") && text.contains("demo/"));
    }

    private static RuntimeContext runtime(UserContext owner, String sessionId, String agentId) {
        return RuntimeContext.builder().userId(owner.tenantKey() + "_" + owner.userId())
                .sessionId(sessionId + "-" + UUID.randomUUID())
                .put(UserContext.class, owner).put(AttachmentToolSupport.AGENT_ID_KEY, agentId).build();
    }

    private static Msg call(io.agentscope.harness.agent.HarnessAgent agent, RuntimeContext context, String text) {
        return agent.call(Msg.builder().role(MsgRole.USER).content(TextBlock.builder().text(text).build()).build(), context).block();
    }

    private static Flux<ChatResponse> toolCall(String name, Map<String, Object> input) {
        return Flux.just(ChatResponse.builder().content(List.of(ToolUseBlock.builder().id("call-" + name)
                .name(name).input(input).content(io.agentscope.core.util.JsonUtils.getJsonCodec().toJson(input)).build()))
                .finishReason("tool_calls").build());
    }

    private static Flux<ChatResponse> answer(String text) {
        return Flux.just(ChatResponse.builder().content(List.of(TextBlock.builder().text(text).build()))
                .finishReason("stop").build());
    }

    private static String toolResults(List<Msg> messages) {
        return messages.stream().flatMap(message -> message.getContent().stream())
                .filter(ToolResultBlock.class::isInstance).map(ToolResultBlock.class::cast)
                .flatMap(result -> result.getOutput().stream()).filter(TextBlock.class::isInstance)
                .map(TextBlock.class::cast).map(TextBlock::getText).collect(java.util.stream.Collectors.joining("\n"));
    }
}
