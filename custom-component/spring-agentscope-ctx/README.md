# spring-agentscope-ctx

## 数据源

默认复用 Spring Boot 的业务 `DataSource`（通常由 `spring.datasource.*` 配置；多个数据源时使用 `@Primary`）。如需独立存储，配置：

```yaml
fast:
  agentscope:
    datasource:
      url: jdbc:postgresql://localhost:5432/agentscope
      username: ${AGENT_DB_USERNAME}
      password: ${AGENT_DB_PASSWORD}
      driver-class-name: org.postgresql.Driver
      hikari:
        maximum-pool-size: 10
```

配置 `fast.agentscope.datasource.url` 后，会话元数据和 Session Log 默认实现统一使用独立连接池。独立池不会替换业务 `DataSource`，随 Spring 容器关闭；数据库驱动由业务服务引入。会话建表脚本需在实际使用的数据库执行。自定义 `ConversationRepository` / `SessionLogStore` 则自行决定数据源。

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

注册 `HarnessAgentDefinition` Bean。模块默认复用业务服务的 `DataSource`；会话元数据表请由业务迁移工具执行 `classpath:agentscope/agent-conversation.sql`；AgentScope 日志的 JDBC 表按 SDK 要求初始化，或设置 `fast.agentscope.session-log.auto-create-table=true`。可通过自定义 `ConversationRepository`、`SessionLogStore` Bean 替换默认实现。

```yaml
fast:
  agentscope:
    enabled: true
    controller-enabled: true
    path-prefix: /api
    workspace: ./data/agentscope
    user-id-header: X-User-Id
    tenant-id-header: X-Tenant-Id
    session-log:
      auto-create-table: false
```

环境变量 `AGI_CHAT_URL` 等价于属性 `agi-chat.url`，可以直接作为模型 URL 来源；`fast.agentscope.model-base-url` 有更高优先级。

默认提供 `GET /api/agents`、会话创建和列表、会话历史。Controller 可通过 `controller-enabled=false` 关闭。历史返回 AG-UI 消息快照，不返回完整日志内部状态。

AG-UI SSE 已包含在 MVC 适配中，使用 SDK 的 AG-UI 事件适配器和编码器，不引入面向 Boot 4 的 starter。

```text
POST /api/agui/run                 (X-Agent-Id 请求头)
POST /api/agui/run/{agentId}       (路径中的 agentId)
```

请求体为 SDK 的 `RunAgentInput` JSON，`threadId` 对应会话 ID，`runId` 必填。执行前按注入的 `UserContextProvider` 校验/创建该用户和租户的会话；已有会话不可被其他用户认领。resume 暂不支持。参数为 `fast.agentscope.agui-enabled`（默认启用）、`fast.agentscope.agui.path-prefix`（默认 `/api/agui`）、`fast.agentscope.agui.sse-timeout`。发生执行异常时返回通用 AG-UI `RUN_ERROR`，不向客户端泄露内部异常信息。

**限制**：普通消息接口是同步 MVC 调用；AG-UI SSE 使用异步 MVC。执行互斥仅在单个进程内有效，不提供集群锁。默认 Session Log 使用新的 JDBC 命名空间，原服务历史日志需单独迁移或注入兼容的 `SessionLogStore`；不要假设旧历史自动可见。

## AG-UI 会话历史与增量续聊

`GET /api/agents/{agentCode}/sessions/{id}` 返回以下消息快照，替代原来的自定义 `events`：

```json
{
  "threadId": "SESSION_ID",
  "cursor": 12,
  "hasMore": false,
  "messages": [
    {"id":"u1","role":"user","content":"你好"},
    {"id":"a1","role":"assistant","content":"你好，有什么可以帮你？"}
  ]
}
```

首次查询使用 `after=0`；`limit` 默认 200、范围 1 到 1000，限制每页扫描的日志事件数。`hasMore=true` 时用返回的 `cursor` 作为下一次 `after`，直到 `hasMore=false`。内部日志会被过滤，某页 `messages` 可能为空；不能通过空消息列表判断分页结束。

前端按 `messages[].id` 合并多页结果，同 ID 消息替换已有值，避免最终回复重复展示。文本 `content` 为字符串；用户图片、音频和视频保留 AG-UI content blocks。工具调用使用 `toolCalls`，工具结果使用 `role: "tool"` 和 `toolCallId`；并行调用结果拆成各自关联的工具消息。系统提示、检查点、原始模型请求、推理内容和内部元数据不返回。

业务 Agent 如需让模型看到标记、但不在会话详情展示，应由服务端写入独立 `TextBlock`，并在消息元数据中用 `MessageMetadata.BUSINESS_MARKER_INDEX`（`agent.business-marker-index`）记录该块的零基索引。详情投影仅过滤该用户消息中对应的文本块，不修改持久化历史或模型上下文。不要使用客户端传入的索引或新增业务专属过滤规则。

### AG-UI 文档引用

### 附件工具

AG-UI 用户消息可在 `content` 中携带 `type: "document"` part；文本附件通过宿主提供的 `AttachmentFileService` 解析，Agent 定义通过 `enableParseTextAttractFile()` 控制是否注册 `read_text_attachment`。图片识别使用独立视觉模型，开关为 `enableParseImageAttractFile()`，模型名由 `fast.agentscope.vision-model-name` 配置。两个开关默认关闭；启用文本工具时必须提供 SPI Bean。

文本工具将 `agentId`、原始 `objectKey` 和已认证的 `UserContext` 交给 SPI。图片工具接收原始 `objectKey` 与 HTTP(S) `fileUrl`，使用独立视觉模型识别。两种工具都只接受当前 Agent 目录下的 key。AG-UI 输入示例：

```json
{"id":"u1","role":"user","content":[{"type":"text","text":"总结附件"},{"type":"document","source":{"type":"url","value":"https://files.example.com/report.pdf","mimeType":"application/pdf"},"metadata":{"objectKey":"demo/report.pdf","fileName":"report.pdf"}}]}
```

实现 `AttachmentFileService` 并返回 `ParsedAttachment`，其中 `content` 是解析文本，`truncated` 表示是否截断。Document 的引用保存在 Session Log；会话重建后可继续调用工具，不必重复上传。内部 marker 不会出现在 SSE 或历史响应中。

继续对话仍调用 AG-UI `/run/{agentId}`，复用 `threadId`，每轮使用新的 `runId` 和消息 ID，`messages` 只包含本轮新增消息。后端从 Session Log 恢复模型上下文，前端不回传历史消息。详情仅用于恢复展示，不重放 SSE，刷新页面不能重复提交原消息。

Java 17 验证：

```bash
JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64 mvn -B -ntp -pl custom-component/spring-agentscope-ctx -am test -DargLine=-Djava.net.preferIPv4Stack=true
```

本次 Java 17 验证共 15 个测试通过（14 个模块测试及单独重跑通过的 HTTP 场景）。HTTP 场景使用临时 H2、真实 MVC/AG-UI 入口和离线模型，覆盖历史分页、Agent 释放重建后从 JDBC 日志恢复上下文、仅提交新增消息续聊；未调用外部模型或数据库。

## `agi:` Relay 模型

默认从环境变量 `AGI_CHAT_URL`（或 Spring 属性 `agi-chat.url`）读取 Relay URL。配置 `fast.agentscope.model-base-url` 可覆盖该默认值。URL 非空时模块注册 `agi:<model-name>` 模型工厂，使用 AgentScope OpenAI 模型扩展连接 OpenAI-compatible Relay。模型请求会加入当前已认证会话的用户/租户 Header（默认 `X-User-Id`、`X-Tenant-Id`，可配置），并设置 `extra_body.agent_code` 为 Agent 定义的 `id()`。自定义 `model()` 可继续使用其他模型及其自身认证策略。
