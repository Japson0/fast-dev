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
    @Override public List<Conversation> list(String agentId, UserContext owner, int offset, int limit) {
        return jdbc.query("SELECT * FROM agent_conversation WHERE agent_id=? AND user_id=? AND tenant_key=? ORDER BY created_at DESC, session_id DESC LIMIT ? OFFSET ?",
                mapper, agentId, owner.userId(), owner.tenantKey(), limit, offset);
    }
    @Override public void archive(Conversation c) {
        jdbc.update("UPDATE agent_conversation SET archived=TRUE WHERE session_id=? AND agent_id=? AND user_id=? AND tenant_key=?",
                c.sessionId(), c.agentId(), c.userId(), c.tenantKey());
    }
}
