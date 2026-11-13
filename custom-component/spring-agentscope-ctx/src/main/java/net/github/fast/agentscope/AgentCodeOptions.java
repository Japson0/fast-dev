package net.github.fast.agentscope;

import io.agentscope.core.model.GenerateOptions;
import java.util.LinkedHashMap;
import java.util.Map;

final class AgentCodeOptions {
    private AgentCodeOptions() { }
    static GenerateOptions apply(String agentCode, GenerateOptions options) {
        Map<String, Object> extraBody = new LinkedHashMap<>();
        Object existing = options == null ? null : options.getAdditionalBodyParams().get("extra_body");
        if (existing != null) {
            if (!(existing instanceof Map<?, ?> fields)) throw new IllegalArgumentException("extra_body must be a JSON object");
            fields.forEach((key, value) -> {
                if (!(key instanceof String name)) throw new IllegalArgumentException("extra_body field names must be strings");
                extraBody.put(name, value);
            });
        }
        extraBody.put("agent_code", agentCode);
        GenerateOptions identity = GenerateOptions.builder().additionalBodyParam("extra_body", extraBody).build();
        return GenerateOptions.mergeOptions(identity, options);
    }
}
