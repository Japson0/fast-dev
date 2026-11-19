package net.github.fast.agentscope;

import io.agentscope.core.agui.adapter.AguiAdapterConfig;
import io.agentscope.core.agui.adapter.AguiAgentAdapter;
import io.agentscope.core.agui.adapter.AguiAgentAdapterFactory;
import io.agentscope.core.agui.model.RunAgentInput;
import io.agentscope.core.agent.Agent;
import io.agentscope.core.agent.RuntimeContext;
import io.agentscope.core.agui.event.AguiEvent;
import reactor.core.publisher.Flux;

/** Normalizes document parts to SDK-supported text markers before AG-UI conversion. */
public final class DocumentAguiAdapterFactory implements AguiAgentAdapterFactory {
    private final AguiAdapterConfig config;

    public DocumentAguiAdapterFactory(AguiAdapterConfig config) { this.config = config; }

    @Override
    public AguiAgentAdapter create(Agent agent, AguiAdapterConfig ignored) {
        return new AguiAgentAdapter(agent, config) {
            @Override
            public Flux<AguiEvent> run(RunAgentInput input, RuntimeContext context) {
                DocumentAttachmentMiddleware middleware = context.get(DocumentAttachmentMiddleware.class);
                var prepared = middleware.prepare(input, context.get(io.agentscope.core.session.SessionLog.class),
                        middleware.imageToolsEnabled());
                DocumentAttachmentMiddleware.attachRequest(context, prepared.attachments());
                return super.run(prepared.input(), context).map(event -> {
                    if (event instanceof AguiEvent.RunStarted started)
                        return new AguiEvent.RunStarted(started.threadId(), started.runId(), started.parentRunId(),
                                input, started.timestamp(), started.rawEvent());
                    return event;
                });
            }
        };
    }
}
