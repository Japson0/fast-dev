package net.github.fast.agentscope;

import com.fasterxml.jackson.core.type.TypeReference;
import io.agentscope.core.agui.model.AguiMessage;
import io.agentscope.core.agui.model.DocumentInputContent;
import io.agentscope.core.agui.model.ImageInputContent;
import io.agentscope.core.agui.model.InputContent;
import io.agentscope.core.agui.model.InputContentDataSource;
import io.agentscope.core.agui.model.InputContentUrlSource;
import io.agentscope.core.agui.model.MessageContent;
import io.agentscope.core.agui.model.RunAgentInput;
import io.agentscope.core.agui.model.TextInputContent;
import io.agentscope.core.agent.Agent;
import io.agentscope.core.agent.RuntimeContext;
import io.agentscope.core.event.AgentEvent;
import io.agentscope.core.message.ImageBlock;
import io.agentscope.core.message.Msg;
import io.agentscope.core.message.MsgRole;
import io.agentscope.core.message.TextBlock;
import io.agentscope.core.middleware.AgentInput;
import io.agentscope.core.middleware.MiddlewareBase;
import io.agentscope.core.middleware.ModelCallInput;
import io.agentscope.core.tool.Toolkit;
import io.agentscope.core.session.SessionEvent;
import io.agentscope.core.session.SessionLog;
import io.agentscope.core.util.JsonUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** Persists attachment markers and serves enabled tools for one agent session. */
public final class DocumentAttachmentMiddleware implements MiddlewareBase {
    private static final String REQUEST_KEY = "fast.agentscope.attachment-request";
    private static final String MARKER_PREFIX = "[[AGUI_ATTACHMENT:";
    private static final String MARKER_SUFFIX = "]]";
    private static final String TEXT_PROMPT = "文本附件读取\n1. 本轮消息带有文本类文件元数据（objectKey）时，先调用 read_text_attachment 读取内容再作答；不要猜测附件内容。\n2. objectKey 必须原样传入，不要拼接、改写或猜测。\n3. 返回 code 为 00000 表示成功，data.content 是解析文本；00001 是参数或文件问题，99999 是系统异常。失败时如实说明原因。\n4. 多个附件逐个调用；data.truncated 为 true 说明内容被截断。";
    private static final String IMAGE_PROMPT = "图片附件读取\n1. 本轮消息带有图片类文件元数据（objectKey、fileUrl）时，先调用 read_image_attachment 读取内容再作答；不要猜测图片内容。\n2. objectKey 与 fileUrl 必须原样传入。\n3. 返回 code 为 00000 表示成功，data.content 是识别结果；00001 是参数或文件问题，99999 是系统异常。失败时如实说明原因。\n4. 多张图片逐个调用。";

    public record AttachmentPart(String marker, Map<String, Object> payload) { }
    public record RequestAttachments(List<AttachmentPart> parts, SessionLog log,
                                    Map<String, List<Map<String, Object>>> byMessage) { }
    public record Prepared(RunAgentInput input, RequestAttachments attachments) { }

    private final boolean textEnabled;
    private final boolean imageEnabled;
    private final List<Object> tools;
    private final String prompt;
    private volatile Toolkit registered;
    public DocumentAttachmentMiddleware(String agentId, boolean textEnabled, boolean imageEnabled,
                                        UserContext owner, AttachmentFileService files, String visionModel) {
        this.textEnabled = textEnabled;
        this.imageEnabled = imageEnabled;
        var selected = new ArrayList<Object>();
        var prompts = new ArrayList<String>();
        if (textEnabled) {
            if (files == null) throw new IllegalStateException("文本附件工具启用时必须提供 AttachmentFileService");
            selected.add(new ReadTextAttachmentTool(agentId, owner, files));
            prompts.add(TEXT_PROMPT);
        }
        if (imageEnabled) {
            selected.add(new ReadImageAttachmentTool(agentId, owner, visionModel));
            prompts.add(IMAGE_PROMPT);
        }
        tools = List.copyOf(selected);
        prompt = String.join("\n\n", prompts);
    }
    public boolean imageToolsEnabled() { return imageEnabled; }
    public static void attachRequest(RuntimeContext context, RequestAttachments request) {
        context.put(REQUEST_KEY, request);
    }

    public static Prepared prepare(RunAgentInput input, SessionLog log, boolean imageToolEnabled) {
        var attachments = new ArrayList<AttachmentPart>();
        var byMessage = new LinkedHashMap<String, List<Map<String, Object>>>();
        var messages = input.getMessages().stream().map(message -> {
            if (!message.isUserMessage() || !(message.getContent() instanceof MessageContent.Blocks blocks)) return message;
            var parts = new ArrayList<InputContent>();
            var refs = new ArrayList<Map<String, Object>>();
            for (InputContent part : blocks.parts()) {
                int before = attachments.size();
                if (part instanceof DocumentInputContent document) {
                    var metadata = new LinkedHashMap<String, Object>();
                    if (document.metadata() != null) metadata.putAll(document.metadata());
                    add(attachments, "document", source(document), metadata);
                    parts.add(new TextInputContent(attachments.get(attachments.size() - 1).marker()));
                } else if (imageToolEnabled && part instanceof ImageInputContent image
                        && image.metadata() != null && image.metadata().get("objectKey") instanceof String
                        && image.source() instanceof InputContentUrlSource url) {
                    var source = new LinkedHashMap<String, Object>();
                    source.put("type", "url");
                    source.put("value", url.value());
                    if (url.mimeType() != null) source.put("mimeType", url.mimeType());
                    add(attachments, "image", source, image.metadata());
                    parts.add(new TextInputContent(attachments.get(attachments.size() - 1).marker()));
                } else parts.add(part);
                if (attachments.size() > before) refs.add(attachments.get(attachments.size() - 1).payload());
            }
            if (!refs.isEmpty()) byMessage.put(message.getId(), List.copyOf(refs));
            return parts.equals(blocks.parts()) ? message : AguiMessage.userMessage(message.getId(), parts);
        }).toList();
        var normalized = attachments.isEmpty() ? input : new RunAgentInput(input.getThreadId(), input.getRunId(), messages,
                input.getTools(), input.getContext(), input.getState(), input.getForwardedProps());
        return new Prepared(normalized, new RequestAttachments(List.copyOf(attachments), log, Map.copyOf(byMessage)));
    }

    private static void add(List<AttachmentPart> parts, String kind, Map<String, Object> source,
                            Map<String, Object> metadata) {
        var payload = new LinkedHashMap<String, Object>();
        payload.put("kind", kind);
        payload.put("source", source);
        payload.put("metadata", metadata);
        String marker = MARKER_PREFIX + JsonUtils.getJsonCodec().toJson(payload) + MARKER_SUFFIX;
        parts.add(new AttachmentPart(marker, Map.copyOf(payload)));
    }

    private static Map<String, Object> source(DocumentInputContent document) {
        var source = new LinkedHashMap<String, Object>();
        if (document.source() instanceof InputContentUrlSource url) {
            source.put("type", "url");
            source.put("value", url.value());
            if (url.mimeType() != null) source.put("mimeType", url.mimeType());
        } else if (document.source() instanceof InputContentDataSource data) {
            source.put("type", "data");
            source.put("value", data.value());
            source.put("mimeType", data.mimeType());
        } else throw new IllegalArgumentException("AG-UI document source 仅支持 url 或 data");
        return source;
    }

    public static boolean isMarker(String text) { return text != null && text.startsWith(MARKER_PREFIX) && text.endsWith(MARKER_SUFFIX); }
    public static Map<String, Object> payload(String marker) {
        if (!isMarker(marker)) return null;
        return JsonUtils.getJsonCodec().fromJson(marker.substring(MARKER_PREFIX.length(),
                marker.length() - MARKER_SUFFIX.length()), new TypeReference<Map<String, Object>>() { });
    }

    @Override
    public Mono<String> onSystemPrompt(Agent agent, RuntimeContext context, String currentPrompt) {
        Toolkit toolkit = agent == null ? null : agent.getToolkit();
        if (toolkit != null && toolkit != registered) {
            tools.forEach(toolkit::registerTool);
            registered = toolkit;
        }
        String base = currentPrompt == null ? "" : currentPrompt.strip();
        return Mono.just(prompt.isEmpty() ? base : base.isEmpty() ? prompt : base + "\n\n" + prompt);
    }

    @Override
    public Flux<AgentEvent> onAgent(Agent agent, RuntimeContext context, AgentInput input,
                                   Function<AgentInput, Flux<AgentEvent>> next) {
        RequestAttachments request = context.get(REQUEST_KEY, RequestAttachments.class);
        SessionLog log = request == null ? context.get(SessionLog.class) : request.log();
        var byMessage = new LinkedHashMap<String, List<Map<String, Object>>>();
        collect(log, byMessage);
        if (request != null) request.byMessage().forEach(byMessage::put);
        if (byMessage.isEmpty()) return next.apply(input);
        var messages = input.msgs().stream().map(message -> {
            List<Map<String, Object>> parts = byMessage.get(message.getId());
            if (message.getRole() != MsgRole.USER || parts == null) return message;
            var metadata = new LinkedHashMap<>(message.getMetadata());
            metadata.put("fast.agentscope.documents", parts);
            return message.withMetadata(metadata);
        }).toList();
        return next.apply(new AgentInput(messages));
    }
    private static void collect(SessionLog log, Map<String, List<Map<String, Object>>> refs) {
        if (log == null) return;
        long cursor = 0;
        while (cursor < log.head().seq()) {
            List<SessionEvent> events = log.readAfter(cursor, 500);
            if (events.isEmpty()) break;
            for (SessionEvent event : events) {
                cursor = event.seq();
                Object raw = event.data().get("message");
                if (raw == null) continue;
                Msg message = JsonUtils.getJsonCodec().convertValue(raw, Msg.class);
                if (message.getRole() != MsgRole.USER) continue;
                var parts = new ArrayList<Map<String, Object>>();
                for (var block : message.getContent())
                    if (block instanceof TextBlock text && isMarker(text.getText())) parts.add(payload(text.getText()));
                if (!parts.isEmpty()) refs.putIfAbsent(message.getId(), List.copyOf(parts));
            }
        }
    }


    @Override
    public Flux<AgentEvent> onModelCall(Agent agent, RuntimeContext context, ModelCallInput input,
                                        Function<ModelCallInput, Flux<AgentEvent>> next) {
        var messages = new ArrayList<Msg>(input.messages().size());
        for (Msg message : input.messages()) {
            var refs = new ArrayList<Map<String, Object>>();
            Object value = message.getMetadata().get("fast.agentscope.documents");
            if (value instanceof List<?> values)
                values.stream().filter(Map.class::isInstance).map(item -> (Map<String, Object>) item).forEach(refs::add);
            if (message.getRole() == MsgRole.USER)
                for (var block : message.getContent())
                    if (block instanceof TextBlock text && isMarker(text.getText())) refs.add(payload(text.getText()));
            refs = refs.stream().filter(ref -> "document".equals(ref.get("kind"))
                    || ("image".equals(ref.get("kind")) && imageEnabled))
                    .map(DocumentAttachmentMiddleware::toolReference)
                    .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
            var content = new ArrayList<io.agentscope.core.message.ContentBlock>();
            for (var block : message.getContent()) {
                if (block instanceof TextBlock text && isMarker(text.getText())) continue;
                if (imageEnabled && message.getRole() == MsgRole.USER && block instanceof ImageBlock) continue;
                content.add(block);
            }
            Msg projected = content.size() == message.getContent().size() ? message : message.withContent(content);
            if (!refs.isEmpty()) {
                content = new ArrayList<>(projected.getContent());
                content.add(TextBlock.builder().text("附件工具参数（按需调用）：" + JsonUtils.getJsonCodec().toJson(refs)).build());
                projected = projected.withContent(content);
            }
            messages.add(projected);
        }
        return next.apply(new ModelCallInput(messages, input.tools(), input.options(), input.model()));
    }

    private static Map<String, Object> toolReference(Map<String, Object> ref) {
        var result = new LinkedHashMap<String, Object>();
        result.put("kind", ref.get("kind"));
        if (ref.get("metadata") instanceof Map<?, ?> metadata)
            for (String field : List.of("objectKey", "fileName"))
                if (metadata.get(field) instanceof String value) result.put(field, value);
        if ("image".equals(ref.get("kind")) && ref.get("source") instanceof Map<?, ?> source
                && source.get("value") instanceof String url) result.put("fileUrl", url);
        return Map.copyOf(result);
    }
}
