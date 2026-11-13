package net.github.fast.agentscope;

import io.agentscope.core.agent.AgentBase;
import io.agentscope.core.agent.RuntimeContext;
import io.agentscope.core.message.Msg;
import io.agentscope.core.model.ChatResponse;
import io.agentscope.core.model.GenerateOptions;
import io.agentscope.core.model.Model;
import io.agentscope.core.model.ToolSchema;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Flux;

/** Injects the verified session owner into requests made by agi: relay models. */
public final class AgiRelayModel implements Model {
    private final Model delegate;
    private final AgentScopeProperties properties;
    private final UserContext sessionOwner;

    private AgiRelayModel(Model delegate, AgentScopeProperties properties, UserContext owner) {
        this.delegate = delegate; this.properties = properties; this.sessionOwner = owner;
    }
    static Model createDelegate(Model delegate, String modelName) {
        return new NamedModel(delegate, modelName);
    }
    public static Model wrap(Model model, AgentScopeProperties properties) {
        if (model instanceof AgiRelayModel) return model;
        if (model.getModelName() == null || !model.getModelName().startsWith("agi:")) return model;
        return new AgiRelayModel(model, properties, null);
    }
    public static Model forSession(Model model, RuntimeContext context) {
        if (!(model instanceof AgiRelayModel relay)) return model;
        UserContext owner = context == null ? null : context.get(UserContext.class);
        if (owner == null) owner = decodeOwner(context);
        return new AgiRelayModel(relay.delegate, relay.properties, owner);
    }
    private static UserContext decodeOwner(RuntimeContext context) {
        if (context == null || context.getUserId() == null) return null;
        String[] identity = context.getUserId().split("_", -1);
        if (identity.length != 2) return null;
        try { return new UserContext(Long.valueOf(identity[1]), "none".equals(identity[0]) ? null : Long.valueOf(identity[0])); }
        catch (NumberFormatException ignored) { return null; }
    }
    @Override public Flux<ChatResponse> stream(List<Msg> messages, List<ToolSchema> tools, GenerateOptions options) {
        return Flux.deferContextual(view -> {
            RuntimeContext context = view.getOrDefault(AgentBase.RUNTIME_CONTEXT_KEY, null);
            UserContext owner = context != null && context.get(UserContext.class) != null
                    ? context.get(UserContext.class) : sessionOwner;
            if (owner == null) return Flux.error(new IllegalStateException("agi: model call requires a trusted user context"));
            Map<String, String> headers = new LinkedHashMap<>();
            if (options != null) headers.putAll(options.getAdditionalHeaders());
            headers.put(properties.getUserIdHeader(), owner.userId().toString());
            headers.put(properties.getTenantIdHeader(), owner.tenantId() == null ? "" : owner.tenantId().toString());
            GenerateOptions identity = GenerateOptions.builder().additionalHeaders(headers).build();
            return delegate.stream(messages, tools, GenerateOptions.mergeOptions(identity, options));
        });
    }
    @Override public String getModelName() { return delegate.getModelName(); }
    @Override public boolean supportsNativeStructuredOutput() { return delegate.supportsNativeStructuredOutput(); }
    @Override public boolean supportsNativeStructuredOutputWithTools() { return delegate.supportsNativeStructuredOutputWithTools(); }
    @Override public int getContextWindowSize() { return delegate.getContextWindowSize(); }

    private record NamedModel(Model delegate, String modelName) implements Model {
        @Override public Flux<ChatResponse> stream(List<Msg> messages, List<ToolSchema> tools, GenerateOptions options) {
            return delegate.stream(messages, tools, options);
        }
        @Override public String getModelName() { return modelName; }
        @Override public boolean supportsNativeStructuredOutput() { return delegate.supportsNativeStructuredOutput(); }
        @Override public boolean supportsNativeStructuredOutputWithTools() { return delegate.supportsNativeStructuredOutputWithTools(); }
        @Override public int getContextWindowSize() { return delegate.getContextWindowSize(); }
    }
}
