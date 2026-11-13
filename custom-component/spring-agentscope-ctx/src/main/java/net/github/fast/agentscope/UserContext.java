package net.github.fast.agentscope;

/** A trusted, immutable snapshot of the identity supplied by the host application. */
public record UserContext(Long userId, Long tenantId) {
    public UserContext {
        if (userId == null) throw new IllegalArgumentException("userId is required");
    }

    public String tenantKey() { return tenantId == null ? "none" : tenantId.toString(); }
}
