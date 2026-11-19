package net.github.fast.agentscope;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.agentscope.core.agui.converter.AguiMessageConverter;
import io.agentscope.core.agui.model.AguiToolCall;
import io.agentscope.core.message.*;
import io.agentscope.core.session.SessionLog;
import io.agentscope.core.util.JsonUtils;
import java.util.*;

/** Public AG-UI messages; never expose model requests, system prompts or checkpoint state. */
public final class ConversationView {
    private static final AguiMessageConverter CONVERTER = new AguiMessageConverter();
    private static final Set<String> MESSAGE_EVENTS = Set.of(
            "message/user", "message/assistant", "turn/output", "tool/result");

    private ConversationView() { }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Message(String id, String role, Object content, List<AguiToolCall> toolCalls,
                          String toolCallId) { }
    public record Snapshot(String threadId, long cursor, boolean hasMore, List<Message> messages) { }

    public static Snapshot snapshot(SessionLog log, String threadId, long after, int limit) {
        long head = log.head().seq();
        var events = log.readAfter(after, limit);
        var messages = new LinkedHashMap<String, Message>();
        long cursor = after;
        for (var event : events) {
            if (event.seq() > head) break;
            cursor = event.seq();
            if (!MESSAGE_EVENTS.contains(event.type())) continue;
            Object value = event.data().get("message");
            if (value == null) continue;
            Msg message = JsonUtils.getJsonCodec().convertValue(value, Msg.class);
            project(message, messages);
        }
        return new Snapshot(threadId, cursor, cursor < head, List.copyOf(messages.values()));
    }

    private static void project(Msg message, Map<String, Message> messages) {
        if (message.getRole() == MsgRole.SYSTEM) return;
        if (message.getRole() == MsgRole.USER
                && message.getMetadata().get(MessageMetadata.BUSINESS_MARKER_INDEX) instanceof Number marker) {
            int index = marker.intValue();
            if (index >= 0 && index < message.getContent().size()
                    && message.getContent().get(index) instanceof TextBlock) {
                var blocks = new ArrayList<>(message.getContent());
                blocks.remove(index);
                message = message.withContent(blocks);
            }
        }
        var results = message.getContentBlocks(ToolResultBlock.class);
        if (!results.isEmpty()) {
            for (var result : results) {
                String id = results.size() == 1 ? message.getId() : message.getId() + ":" + result.getId();
                var converted = CONVERTER.toAguiMessage(Msg.builder().id(id).role(MsgRole.TOOL)
                        .content(result).build());
                messages.put(id, new Message(id, "tool", Objects.requireNonNullElse(converted.getTextContent(), ""),
                        null, result.getId()));
            }
            return;
        }
        var converted = CONVERTER.toAguiMessage(message);
        Object content = converted.getTextContent();
        boolean structured = message.getRole() == MsgRole.USER && message.getContent().stream().anyMatch(block ->
                block instanceof ImageBlock || block instanceof AudioBlock || block instanceof VideoBlock
                        || block instanceof TextBlock text && DocumentAttachmentMiddleware.isMarker(text.getText()));
        if (structured) content = inputContent(message);
        if (content == null && !converted.hasToolCalls()) return;
        messages.put(message.getId(), new Message(message.getId(), converted.getRole(), content,
                converted.hasToolCalls() ? converted.getToolCalls() : null, null));
    }

    private static List<Map<String, Object>> inputContent(Msg message) {
        var parts = new ArrayList<Map<String, Object>>();
        for (var block : message.getContent()) {
            if (block instanceof TextBlock text) {
                if (DocumentAttachmentMiddleware.isMarker(text.getText())) {
                    var document = DocumentAttachmentMiddleware.payload(text.getText());
                    parts.add(Map.of("type", "document", "source", document.get("source"),
                            "metadata", document.get("metadata")));
                } else {
                    parts.add(Map.of("type", "text", "text", text.getText()));
                }
            } else if (block instanceof ImageBlock image) {
                parts.add(media("image", image.getSource()));
            } else if (block instanceof AudioBlock audio) {
                parts.add(media("audio", audio.getSource()));
            } else if (block instanceof VideoBlock video) {
                parts.add(media("video", video.getSource()));
            }
        }
        return parts;
    }

    private static Map<String, Object> media(String type, Source source) {
        var value = new LinkedHashMap<String, Object>();
        if (source instanceof URLSource url) {
            value.put("type", "url");
            value.put("value", url.getUrl());
            if (url.getMimeType() != null) value.put("mimeType", url.getMimeType());
        } else if (source instanceof Base64Source data) {
            value.put("type", "data");
            value.put("value", data.getData());
            value.put("mimeType", data.getMediaType());
        } else {
            throw new IllegalArgumentException("Unsupported AG-UI content source: " + source.getClass());
        }
        return Map.of("type", type, "source", value);
    }
}
