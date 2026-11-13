package net.github.fast.agentscope;

import io.agentscope.core.session.SessionEvent;
import io.agentscope.core.session.SessionLog;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Public projection; never expose model requests, system prompts or checkpoint state. */
public final class ConversationView {
    private ConversationView() { }
    public record Event(long cursor, String eventId, String type, String turnId, String runId, Map<String, Object> data) { }
    public record Snapshot(long cursor, List<Event> events) { }
    public static Snapshot snapshot(SessionLog log, long after, int limit) {
        List<Event> events = log.readAfter(after, limit).stream().map(ConversationView::project).toList();
        return new Snapshot(events.isEmpty() ? after : events.get(events.size() - 1).cursor(), events);
    }
    private static Event project(SessionEvent event) {
        Map<String, Object> source = event.data();
        Map<String, Object> result = new LinkedHashMap<>();
        String type = event.type();
        switch (type) {
            case "message/user", "message/assistant", "turn/output", "tool/result" -> copyMessage(source.get("message"), result);
            case "model/chunk" -> {
                if (source.get("chunk") instanceof Map<?, ?> chunk) {
                    result.put("messageId", chunk.get("id"));
                    result.put("content", publicContent(chunk.get("content")));
                }
                result.put("modelCallId", source.get("modelCallId"));
            }
            case "tool/requested", "interaction/requested" -> {
                for (String field : List.of("requestId", "kind", "messageId", "toolCallId", "call"))
                    if (source.containsKey(field)) result.put(field, field.equals("call") ? publicBlock(source.get(field)) : source.get(field));
            }
            case "interaction/resolved" -> {
                for (String field : List.of("requestId", "kind")) if (source.containsKey(field)) result.put(field, source.get(field));
            }
            case "turn/completed", "turn/suspended", "turn/failed", "turn/interrupted", "turn/cancelled", "run/start", "run/end" -> {
                if (source.containsKey("status")) result.put("status", source.get("status"));
            }
            default -> type = "cursor";
        }
        return new Event(event.seq(), event.eventId(), type, event.turnId(), event.executionRunId(), result);
    }
    private static void copyMessage(Object value, Map<String, Object> result) {
        if (value instanceof Map<?, ?> message) {
            result.put("messageId", message.get("id")); result.put("role", message.get("role"));
            result.put("content", publicContent(message.get("content")));
        }
    }
    private static List<?> publicContent(Object value) {
        if (!(value instanceof List<?> content)) return List.of();
        return content.stream().filter(block -> block instanceof Map<?, ?> map && !"thinking".equals(map.get("type")))
                .map(ConversationView::publicBlock).toList();
    }
    private static Object publicBlock(Object value) {
        if (!(value instanceof Map<?, ?> block)) return Map.of();
        Map<String, Object> clean = new LinkedHashMap<>();
        block.forEach((key, item) -> {
            String name = String.valueOf(key);
            if (!Set.of("metadata", "executionDetails").contains(name))
                clean.put(name, name.equals("output") ? publicContent(item) : item);
        });
        return clean;
    }
}
