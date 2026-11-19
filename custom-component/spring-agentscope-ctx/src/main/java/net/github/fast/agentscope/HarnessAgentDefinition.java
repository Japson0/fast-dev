package net.github.fast.agentscope;

import io.agentscope.core.agent.RuntimeContext;
import io.agentscope.core.model.GenerateOptions;
import io.agentscope.core.model.Model;
import io.agentscope.core.model.ModelRegistry;
import io.agentscope.harness.agent.HarnessAgent;
import io.agentscope.harness.agent.IsolationScope;
import io.agentscope.harness.agent.filesystem.spec.LocalFilesystemSpec;
import io.agentscope.harness.agent.memory.MemoryConfig;
import java.util.Objects;
import java.util.function.Consumer;

/** Business applications supply agent definitions as Spring beans. */
public abstract class HarnessAgentDefinition {
    public abstract String id();
    public abstract String name();
    public abstract String description();
    protected abstract String modelName();
    protected Model model() { return ModelRegistry.resolve(Objects.requireNonNull(modelName())); }
    public GenerateOptions generateOptions() { return null; }
    public IsolationScope isolationScope() { return IsolationScope.SESSION; }
    public boolean memoryEnabled() { return false; }
    public boolean enableParseTextAttractFile() { return false; }
    public boolean enableParseImageAttractFile() { return false; }
    public abstract void configure(HarnessAgent.Builder builder);

    public final HarnessAgent build(RuntimeContext context, Consumer<HarnessAgent.Builder> infrastructure) {
        return build(context, infrastructure, null, null);
    }

    public final HarnessAgent build(RuntimeContext context, Consumer<HarnessAgent.Builder> infrastructure,
                                    AgentScopeProperties properties) {
        return build(context, infrastructure, properties, null);
    }

    public final HarnessAgent build(RuntimeContext context, Consumer<HarnessAgent.Builder> infrastructure,
                                    AgentScopeProperties properties, AttachmentFileService files) {
        HarnessAgent.Builder builder = HarnessAgent.builder();
        if (memoryEnabled()) builder.memory(MemoryConfig.defaults());
        configure(builder);
        Model selectedModel = AgiRelayModel.forSession(Objects.requireNonNull(model()), context);
        builder.model(selectedModel).agentId(id()).name(name()).description(description())
                .generateOptions(AgentCodeOptions.apply(id(), generateOptions()));
        builder.filesystem(new LocalFilesystemSpec().isolationScope(Objects.requireNonNull(isolationScope())));
        infrastructure.accept(builder);

        boolean text = enableParseTextAttractFile();
        boolean image = enableParseImageAttractFile();
        if (text && files == null) throw new IllegalStateException("文本附件工具启用时必须提供 AttachmentFileService");
        var middleware = new DocumentAttachmentMiddleware(id(), text, image, context.get(UserContext.class),
                files, properties == null ? "agi:vl-llm-latest" : properties.getVisionModelName());
        builder.middleware(middleware);
        context.put(DocumentAttachmentMiddleware.class, middleware);
        return builder.build();
    }
}
