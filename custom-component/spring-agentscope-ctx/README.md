# spring-agentscope-ctx

Java 17 / Spring Boot 3 的 AgentScope Harness 与会话 MVC 组件。业务服务需要提供 `UserContextProvider` Bean；身份必须来自业务侧已认证的上下文，组件不读取用户请求头。

```java
@Bean
UserContextProvider agentUserContextProvider() {
    return () -> {
        // 替换为业务系统已认证的用户上下文；未登录时返回 null。
        if (!BusinessUserContext.hasUser()) return null;
        return new UserContext(BusinessUserContext.getUserId(), BusinessUserContext.getTenantId());
    };
}
```

注册 `HarnessAgentDefinition` Bean，并由业务方负责注册模型（或重写 `model()`）。模块使用业务服务现有 `DataSource`：会话元数据表请由业务迁移工具执行 `classpath:agentscope/agent-conversation.sql`；AgentScope 日志的 JDBC 表按 SDK 要求初始化，或设置 `fast.agentscope.session-log.auto-create-table=true`。可通过自定义 `ConversationRepository`、`SessionLogStore` Bean 替换默认实现。

```yaml
fast:
  agentscope:
    enabled: true
    controller-enabled: true
    path-prefix: /api
    workspace: ./data/agentscope
    session-log:
      auto-create-table: false
```

默认提供 `GET /api/agents`、会话创建和列表、会话历史、会话归档以及 `POST /api/agents/{agentCode}/sessions/{id}/messages` 同步执行接口。Controller 可通过 `controller-enabled=false` 关闭。历史返回经过投影，不返回完整日志内部状态。

AG-UI SSE 已包含在 MVC 适配中，使用 SDK 的 AG-UI 事件适配器和编码器，

```text
POST /api/agui/run                 (X-Agent-Id 请求头)
POST /api/agui/run/{agentId}       (路径中的 agentId)
```

请求体为 SDK 的 `RunAgentInput` JSON，`threadId` 对应会话 ID，`runId` 必填。执行前按注入的 `UserContextProvider` 校验/创建该用户和租户的会话；已有会话不可被其他用户认领。resume 暂不支持。参数为 `fast.agentscope.agui-enabled`（默认启用）、`fast.agentscope.agui.path-prefix`（默认 `/api/agui`）、`fast.agentscope.agui.sse-timeout`。发生执行异常时返回通用 AG-UI `RUN_ERROR`，不向客户端泄露内部异常信息。

**限制**：普通消息接口是同步 MVC 调用；AG-UI SSE 使用异步 MVC。执行互斥仅在单个进程内有效，不提供集群锁。默认 Session Log 使用新的 JDBC 命名空间，原服务历史日志需单独迁移或注入兼容的 `SessionLogStore`；不要假设旧历史自动可见。
