package net.github.fast.agentscope;

public record Conversation(String sessionId, String agentId, long userId, String tenantKey,
                           String title, long createdAt, boolean archived) { }
