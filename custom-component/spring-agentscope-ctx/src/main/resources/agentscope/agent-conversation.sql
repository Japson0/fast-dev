CREATE TABLE agent_conversation (
    session_id VARCHAR(36) PRIMARY KEY,
    agent_id VARCHAR(64) NOT NULL,
    user_id BIGINT NOT NULL,
    tenant_key VARCHAR(32) NOT NULL,
    title VARCHAR(200) NOT NULL,
    created_at BIGINT NOT NULL,
    archived BOOLEAN NOT NULL DEFAULT FALSE
);
CREATE INDEX idx_agent_conversation_owner ON agent_conversation (user_id, tenant_key, created_at);
