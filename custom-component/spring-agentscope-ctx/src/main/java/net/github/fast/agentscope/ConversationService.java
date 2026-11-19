package net.github.fast.agentscope;

import io.agentscope.core.message.MsgRole;
import io.agentscope.core.message.Msg;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import io.agentscope.core.session.SessionLog;
import io.agentscope.core.agent.RuntimeContext;
import io.agentscope.harness.agent.HarnessAgent;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConversationService {
    private final AgentRegistry registry;
    private final ConversationRepository repository;
    private final Set<String> busy = ConcurrentHashMap.newKeySet();

    public ConversationService(AgentRegistry registry, ConversationRepository repository) {
        this.registry = registry;
        this.repository = repository;
    }
    public Conversation create(String agentId, String title, UserContext owner) {
        AgentRegistry.Descriptor agent = registry.descriptor(agentId);
        if (title != null && title.length() > 200) throw badRequest("标题不能超过200字符");
        return repository.create(new Conversation(UUID.randomUUID().toString(), agentId, owner.userId(),
                owner.tenantKey(), title == null || title.isBlank() ? agent.name() : title.strip(), System.currentTimeMillis(), false));
    }
    public PageRequest<Conversation> page(String agentId, UserContext owner, PageRequest<SessionQuery> request) {
        registry.descriptor(agentId);
        long page = request.getPage();
        long size = request.getSize();
        if (page < 0 || size < 1 || size > 200) throw badRequest("分页参数无效");
        String title = request.getCondition() == null ? null : request.getCondition().title();
        final long offset;
        try {
            offset = Math.multiplyExact(page, size);
        } catch (ArithmeticException failure) {
            throw badRequest("分页参数超出支持范围");
        }
        var records = repository.list(agentId, owner, (int) offset, (int) size, title);
        return PageRequest.result(request.getPage(), size, repository.count(agentId, owner, title), records);
    }
    public Conversation require(String agentId, String id, UserContext owner) {
        registry.descriptor(agentId);
        return repository.find(agentId, id, owner).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "会话不存在"));
    }
    public ConversationView.Snapshot history(String agentId, String id, UserContext owner, long after, int limit) {
        if (after < 0 || limit < 1 || limit > 1000) throw badRequest("历史查询参数无效");
        return ConversationView.snapshot(registry.log(require(agentId, id, owner)), id, after, limit);
    }
    public void archive(String agentId, String id, UserContext owner) {
        Conversation c = require(agentId, id, owner);
        acquire(id);
        try { repository.archive(c); registry.release(c); }
        finally { busy.remove(id); }
    }
    public String run(String agentId, String id, UserContext owner, String text) {
        if (text == null || text.isBlank()) throw badRequest("消息不能为空");
        Conversation c = require(agentId, id, owner);
        acquire(id);
        try {
            // Re-read after acquiring the lock: archive might have completed in the meantime.
            c = require(agentId, id, owner);
            if (c.archived()) throw new ResponseStatusException(HttpStatus.CONFLICT, "会话已归档");
            Msg input = Msg.builder().name("user").role(MsgRole.USER).textContent(text).build();
            Msg result = registry.agent(c).call(input, registry.context(c)).block();
            return result == null ? "" : result.getTextContent();
        } finally { busy.remove(id); }
    }
    /** Acquire a direct AG-UI run. The verified owner is captured before leaving the request thread. */
    public Execution beginDirect(String agentId, String id, UserContext owner) {
        if (id == null || !id.matches("[a-zA-Z0-9_-]{1,36}"))
            throw badRequest("threadId 必须为1到36位字母、数字、下划线或短横线");
        AgentRegistry.Descriptor descriptor = registry.descriptor(agentId);
        acquire(id);
        try {
            Conversation c = repository.find(agentId, id, owner).orElseGet(() -> {
                try {
                    return repository.create(new Conversation(id, agentId, owner.userId(), owner.tenantKey(),
                            descriptor.name(), System.currentTimeMillis(), false));
                } catch (DuplicateKeyException conflict) {
                    // A different owner/agent must never be able to claim an existing thread ID.
                    return require(agentId, id, owner);
                }
            });
            if (c.archived()) throw new ResponseStatusException(HttpStatus.CONFLICT, "会话已归档");
            HarnessAgent agent = registry.agent(c);
            RuntimeContext context = registry.context(c);
            context.put(SessionLog.class, registry.log(c));
            DocumentAttachmentMiddleware middleware = registry.attachmentMiddleware(c);
            if (middleware != null) context.put(DocumentAttachmentMiddleware.class, middleware);
            return new Execution(agent, context, () -> busy.remove(id));
        } catch (RuntimeException | Error failure) {
            busy.remove(id);
            throw failure;
        }
    }

    public static final class Execution implements AutoCloseable {
        private final HarnessAgent agent;
        private final RuntimeContext context;
        private final Runnable release;
        private final AtomicBoolean closed = new AtomicBoolean();

        Execution(HarnessAgent agent, RuntimeContext context, Runnable release) {
            this.agent = agent; this.context = context; this.release = release;
        }
        public HarnessAgent agent() { return agent; }
        public RuntimeContext context() { return context; }
        @Override public void close() { if (closed.compareAndSet(false, true)) release.run(); }
    }
    private void acquire(String id) {
        if (!busy.add(id)) throw new ResponseStatusException(HttpStatus.CONFLICT, "会话正在执行");
    }
    private static ResponseStatusException badRequest(String message) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }
}
