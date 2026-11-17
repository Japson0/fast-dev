package net.github.fast.agentscope;

import java.util.List;
import java.util.Optional;

/** All owner-scoped operations must filter by both user and tenant. */
public interface ConversationRepository {
    Conversation create(Conversation conversation);
    Optional<Conversation> find(String agentId, String sessionId, UserContext owner);
    long count(String agentId, UserContext owner, String title);
    List<Conversation> list(String agentId, UserContext owner, int offset, int limit, String title);
    void archive(Conversation conversation);
}
