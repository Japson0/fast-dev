package net.github.fast.agentscope;

import com.fasterxml.jackson.databind.json.JsonMapper;
import io.agentscope.core.agent.RuntimeContext;
import io.agentscope.core.message.ToolResultBlock;
import java.util.Map;
import java.util.regex.Pattern;

final class AttachmentToolSupport {
    static final String AGENT_ID_KEY = "fast.agentscope.agent-id";
    static final String SUCCEED = "00000";
    static final String FAILED = "00001";
    static final String SYSTEM_ERROR = "99999";
    private static final JsonMapper JSON = JsonMapper.builder().build();
    private static final Pattern AGENT_ID = Pattern.compile("[A-Za-z0-9_-]{1,64}");

    private AttachmentToolSupport() { }
    static Check check(String agentId, String objectKey) {
        if (agentId == null || !AGENT_ID.matcher(agentId).matches())
            return new Check(null, response(false, FAILED, null, "附件工具缺少有效的 agentId"));
        if (objectKey == null || objectKey.isBlank())
            return new Check(agentId, response(false, FAILED, null, "objectKey 不能为空"));
        if (!objectKey.startsWith(agentId + "/"))
            return new Check(agentId, response(false, FAILED, null, "objectKey 必须位于当前智能体目录 " + agentId + "/ 下"));
        return new Check(agentId, null);
    }
    static ToolResultBlock response(boolean result, String code, Object data, String message) {
        try {
            return ToolResultBlock.text(JSON.writeValueAsString(Map.of("result", result, "code", code,
                    "data", data == null ? Map.of() : data, "message", message == null ? "" : message)));
        } catch (com.fasterxml.jackson.core.JsonProcessingException failure) {
            return ToolResultBlock.text("{\"result\":false,\"code\":\"99999\",\"data\":{},\"message\":\"附件结果序列化失败\"}");
        }
    }

    record Check(String agentId, ToolResultBlock failure) { }
}
