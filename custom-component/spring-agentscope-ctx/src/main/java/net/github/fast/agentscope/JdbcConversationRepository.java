package net.github.fast.agentscope;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcConversationRepository implements ConversationRepository {
    private final JdbcTemplate jdbc;
    private final RowMapper<Conversation> mapper = (rs, n) -> new Conversation(
            rs.getString("session_id"), rs.getString("agent_id"), rs.getLong("user_id"),
            rs.getString("tenant_key"), rs.getString("title"), rs.getLong("created_at"), rs.getBoolean("archived"));

    public JdbcConversationRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    @Override public Conversation create(Conversation c) {
        jdbc.update("INSERT INTO agent_conversation (session_id, agent_id, user_id, tenant_key, title, created_at, archived) VALUES (?, ?, ?, ?, ?, ?, ?)",
                c.sessionId(), c.agentId(), c.userId(), c.tenantKey(), c.title(), c.createdAt(), c.archived());
        return c;
    }
    @Override public Optional<Conversation> find(String agentId, String sessionId, UserContext owner) {
        return jdbc.query("SELECT * FROM agent_conversation WHERE agent_id=? AND session_id=? AND user_id=? AND tenant_key=?",
                mapper, agentId, sessionId, owner.userId(), owner.tenantKey()).stream().findFirst();
    }
    @Override public long count(String agentId, UserContext owner, String title) {
        var total = jdbc.queryForObject("SELECT COUNT(*) FROM agent_conversation WHERE agent_id=? AND user_id=? AND tenant_key=?"
                + titleFilter(title), Long.class, ownerParameters(agentId, owner, title));
        return total == null ? 0L : total;
    }
    @Override public List<Conversation> list(String agentId, UserContext owner, int offset, int limit, String title) {
        return jdbc.query("SELECT * FROM agent_conversation WHERE agent_id=? AND user_id=? AND tenant_key=?"
                        + titleFilter(title) + " ORDER BY created_at DESC, session_id DESC LIMIT ? OFFSET ?",
                mapper, withPaging(ownerParameters(agentId, owner, title), limit, offset));
    }
    private String titleFilter(String title) { return title == null || title.isBlank() ? "" : " AND title LIKE ?"; }
    private Object[] ownerParameters(String agentId, UserContext owner, String title) {
        return title == null || title.isBlank()
                ? new Object[]{agentId, owner.userId(), owner.tenantKey()}
                : new Object[]{agentId, owner.userId(), owner.tenantKey(), "%" + title.strip() + "%"};
    }
    private Object[] withPaging(Object[] owner, int limit, int offset) {
        var all = new Object[owner.length + 2];
        System.arraycopy(owner, 0, all, 0, owner.length);
        all[owner.length] = limit;
        all[owner.length + 1] = offset;
        return all;
    }
    @Override public void archive(Conversation c) {
        jdbc.update("UPDATE agent_conversation SET archived=TRUE WHERE session_id=? AND agent_id=? AND user_id=? AND tenant_key=?",
                c.sessionId(), c.agentId(), c.userId(), c.tenantKey());
    }
}
