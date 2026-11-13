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

/** Business applications supply definitions as Spring beans. */
public abstract class HarnessAgentDefinition {
    public abstract String id();
    public abstract String name();
    public abstract String description();
    protected abstract String modelName();
    protected Model model() { return ModelRegistry.resolve(Objects.requireNonNull(modelName())); }
    public GenerateOptions generateOptions() { return null; }
    public IsolationScope isolationScope() { return IsolationScope.SESSION; }
    public boolean memoryEnabled() { return false; }
    public abstract void configure(HarnessAgent.Builder builder);

    public final HarnessAgent build(RuntimeContext context, Consumer<HarnessAgent.Builder> infrastructure) {
        HarnessAgent.Builder builder = HarnessAgent.builder();
        if (memoryEnabled()) builder.memory(MemoryConfig.defaults());
        configure(builder);
        builder.model(Objects.requireNonNull(model())).agentId(id()).name(name()).description(description());
        if (generateOptions() != null) builder.generateOptions(generateOptions());
        builder.filesystem(new LocalFilesystemSpec().isolationScope(Objects.requireNonNull(isolationScope())));
        infrastructure.accept(builder);
        if (!memoryEnabled()) builder.disableMemoryTools().disableMemoryHooks();
        return builder.build();
    }
}
