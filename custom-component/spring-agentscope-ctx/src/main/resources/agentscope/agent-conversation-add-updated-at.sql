ALTER TABLE agent_conversation ADD COLUMN updated_at BIGINT;
UPDATE agent_conversation SET updated_at = created_at WHERE updated_at IS NULL;
ALTER TABLE agent_conversation ALTER COLUMN updated_at SET NOT NULL;
CREATE INDEX idx_agent_conversation_activity ON agent_conversation (agent_id, user_id, tenant_key, updated_at DESC, session_id DESC);
