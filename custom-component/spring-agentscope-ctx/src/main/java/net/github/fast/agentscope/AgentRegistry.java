package net.github.fast.agentscope;

import io.agentscope.core.agent.RuntimeContext;
import io.agentscope.core.session.SessionLogStore;
import io.agentscope.harness.agent.HarnessAgent;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AgentRegistry implements AutoCloseable {
    public record Descriptor(String id, String name, String description) { }
    private final Map<String, HarnessAgentDefinition> definitions = new TreeMap<>();
    private final Map<String, DocumentAttachmentMiddleware> attachmentMiddleware = new ConcurrentHashMap<>();
    private final Map<String, HarnessAgent> agents = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock lifecycle = new ReentrantReadWriteLock();
    private final Path workspace;
    private final SessionLogStore logStore;
    private final AgentScopeProperties properties;
    private final AttachmentFileService attachmentFiles;
    private boolean closed;

    public AgentRegistry(List<HarnessAgentDefinition> definitions, Path workspace, SessionLogStore logStore,
                         AgentScopeProperties properties) {
        this(definitions, workspace, logStore, properties, null);
    }

    public AgentRegistry(List<HarnessAgentDefinition> definitions, Path workspace, SessionLogStore logStore,
                         AgentScopeProperties properties, AttachmentFileService attachmentFiles) {
        this.workspace = workspace.toAbsolutePath().normalize();
        this.logStore = logStore;
        this.properties = properties;
        this.attachmentFiles = attachmentFiles;
        for (HarnessAgentDefinition definition : definitions) {
            if (definition.id() == null || !definition.id().matches("[a-zA-Z0-9_-]{1,64}")
                    || definition.name() == null || definition.name().isBlank()
                    || this.definitions.putIfAbsent(definition.id(), definition) != null)
                throw new IllegalArgumentException("Invalid or duplicate agent: " + definition.id());
        }
    }
    public List<Descriptor> list() { return definitions.keySet().stream().map(this::descriptor).toList(); }
    public Descriptor descriptor(String id) {
        HarnessAgentDefinition definition = definitions.get(id);
        if (definition == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "智能体不存在");
        return new Descriptor(definition.id(), definition.name(), definition.description());
    }
    public RuntimeContext context(Conversation c) {
        UserContext owner = new UserContext(c.userId(), "none".equals(c.tenantKey()) ? null : Long.valueOf(c.tenantKey()));
        return RuntimeContext.builder().userId(c.tenantKey() + "_" + c.userId())
                .sessionId(c.sessionId()).put(UserContext.class, owner)
                .put(AttachmentToolSupport.AGENT_ID_KEY, c.agentId()).build();
    }
    public HarnessAgent agent(Conversation c) {
        lifecycle.readLock().lock();
        try {
            if (closed) throw new IllegalStateException("Agent registry is closed");
            descriptor(c.agentId());
            return agents.computeIfAbsent(c.sessionId(), id -> {
                RuntimeContext buildContext = context(c);
                HarnessAgent agent = definitions.get(c.agentId()).build(buildContext,
                        builder -> builder.workspace(workspace.resolve(c.agentId())).sessionLogStore(logStore),
                        properties, attachmentFiles);
                DocumentAttachmentMiddleware middleware = buildContext.get(DocumentAttachmentMiddleware.class);
                if (middleware != null) attachmentMiddleware.put(c.sessionId(), middleware);
                return agent;
            });
        } finally { lifecycle.readLock().unlock(); }
    }
    public DocumentAttachmentMiddleware attachmentMiddleware(Conversation c) {
        return attachmentMiddleware.get(c.sessionId());
    }
    public io.agentscope.core.session.SessionLog log(Conversation c) {
        RuntimeContext context = context(c);
        return logStore.open(new io.agentscope.core.session.SessionKey(context.getUserId(), c.agentId(), c.sessionId()), context);
    }
    public void release(Conversation c) {
        lifecycle.readLock().lock();
        try {
            HarnessAgent agent = agents.remove(c.sessionId());
            attachmentMiddleware.remove(c.sessionId());
            if (agent != null) agent.close();
        } finally { lifecycle.readLock().unlock(); }
    }
    @Override public void close() {
        lifecycle.writeLock().lock();
        try { closed = true; agents.values().forEach(HarnessAgent::close); agents.clear(); attachmentMiddleware.clear(); }
        finally { lifecycle.writeLock().unlock(); }
    }
}
