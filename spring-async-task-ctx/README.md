# spring-async-task-ctx 使用指南

持久化后台任务组件：通过 JDBC 或 Redis 保存任务，按 `businessCode` 路由到 Spring 管理的 Handler，支持状态查询、异常重试、集群抢占及超租约任务回收。

适用于报表生成、批量处理等需要保存执行状态的异步业务。不是普通 `@Async` 的同义替代，也不提供完整工作流、任务取消或恰好一次执行保证。

## 1. 引入依赖

组件自行管理任务执行、轮询和回收，业务侧只需实现 Handler 并通过 TaskEngine 提交任务，无需自行创建线程池。

```xml
<dependency>
    <groupId>net.github.fast</groupId>
    <artifactId>spring-async-task-ctx</artifactId>
</dependency>
```

依赖版本沿用项目统一管理；未管理时在 dependencyManagement 中配置项目使用的 FastDev 版本。确认所选存储的运行时依赖齐全：JDBC 通常使用 `spring-boot-starter-jdbc`、数据库驱动及 DataSource；Redis 使用 `spring-boot-starter-data-redis`。两种引擎均需 `com.fasterxml.jackson.databind.ObjectMapper` Bean，不要误用其他包的同名类型。

自动配置入口在 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`。无需额外启用注解，但必须明确配置 `fastdev.task.store=jdbc` 或 `redis`；未配置时不会自动选择引擎。

## 2. 选择存储并配置

### JDBC

```yaml
spring:
  application:
    name: report-service
  datasource:
    url: ${TASK_DB_URL}
    username: ${TASK_DB_USERNAME}
    password: ${TASK_DB_PASSWORD}
fastdev:
  task:
    store: jdbc
    queue-name: report-service
    auto-create-table: false
    workers: 4
    pollers: 2
    poll-interval-millis: 3000
    lease-millis: 600000
    retry:
      max-retry: 3
      backoff-millis: 30000
```

引擎通过 Spring JDBC 访问 `async_task_record`，不依赖 MyBatis。生产环境应先通过数据库迁移创建表及索引。

**当前分支资源目录缺少 `sql/async_task_record.sql`**，而初始化器需要该资源。因此表不存在时，不要直接开启自动建表；应先准备兼容当前 JdbcTaskStore 的 DDL 并人工迁移，或在应用 classpath 提供审核后的该路径脚本。表已存在时初始化器跳过建表；该检查不会验证或升级字段结构。

至少核对字段 `TASK_ID`、`SERVICE`、`BUSINESS_CODE`、`TAG`、`PAYLOAD`、`STATUS`、`RETRY_COUNT`、`NEXT_RUN_TIME`、`LOCK_OWNER`、`RESULT`、`ERROR_MSG`、`CREATE_TIME`、`UPDATE_TIME`，以及 `(SERVICE, STATUS, NEXT_RUN_TIME)` 等查询索引。类型、长度及创建/更新人字段以当前存储 SQL 和项目迁移为准。当前查询使用 `LIMIT 1`，不能仅因使用 JDBC 就认为支持 Oracle 等所有数据库。

### Redis

```yaml
spring:
  application:
    name: report-service
  data:
    redis:
      host: ${TASK_REDIS_HOST:localhost}
      port: ${TASK_REDIS_PORT:6379}
fastdev:
  task:
    store: redis
    queue-name: report-service
    workers: 4
    pollers: 2
```

确认有可用的 `StringRedisTemplate`。Redis 用 List 排队、Hash 存元数据、Set 存业务编码/标签索引。元数据和部分索引设置 30 天 TTL，生命周期不是永久归档。恢复保障还取决于 AOF/RDB、复制、故障切换和淘汰策略。

两种存储的命名空间均按显式 `queue-name`、`spring.application.name`、随机名称的顺序选择。需要重启恢复时务必使用稳定名称；同服务集群共享名称和 Handler，不同服务使用不同名称。JDBC 用 SERVICE 列隔离，Redis 用 key 前缀隔离；这不是租户鉴权机制。

## 3. 编写 Handler

以下示例业务类型、包路径和编码请替换为应用实际约定。`ReportService.generateIdempotently` 是示例业务方法，不是框架 API。

```java
import net.github.fastdev.asynctask.TaskContext;
import net.github.fastdev.asynctask.TaskHandler;
import net.github.fastdev.asynctask.TaskResult;
import org.springframework.stereotype.Component;

@Component
public class ReportTaskHandler implements TaskHandler {
    private final ReportService reportService;

    public ReportTaskHandler(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public String businessCode() {
        return "report.generate";
    }

    @Override
    public TaskResult handle(TaskContext context) throws Exception {
        String reportId = context.getString("reportId");
        String tenantId = context.getString("tenantId");
        if (reportId == null || tenantId == null) {
            throw new IllegalArgumentException("reportId/tenantId required");
        }
        reportService.generateIdempotently(tenantId, reportId);
        return TaskResult.success(reportId);
    }
}
```

启动时自动收集 TaskHandler Bean；businessCode 必须非空且唯一。提交必须传入 Spring 管理的 Handler，不能使用临时 new、lambda 或匿名实例。更改编码或删除 Handler 前需处理历史任务，否则恢复后找不到路由。

payload 会经过 JSON 序列化并恢复成 `Map<String, Object>`，不是原 DTO 实例。可使用 `getString`、`getLong`、`getInteger`、`getPayload` 取参数。建议保存业务 ID 和必要快照，不保存请求对象、流、连接、Bean、登录令牌或不必要的敏感信息；优先使用对象结构 payload。

## 4. 提交、查询与等待

在业务 Service 中构造器注入 `TaskEngine` 与 `ReportTaskHandler`。下面代码在已注入 `taskEngine`、`handler` 的方法中执行：

```java
Map<String, Object> payload = new HashMap<>();
payload.put("tenantId", tenantId);
payload.put("reportId", reportId);
String tag = tenantId + ":" + reportId;
String taskId = taskEngine.submit(handler, tag, payload);

List<TaskRecord> records = taskEngine.query(
        TaskQuery.of("report.generate", tag));
List<TaskRecord> failed = taskEngine.query(
        TaskQuery.of("report.generate", tag).statuses(TaskStatus.FAILED));
TaskResult result = taskEngine.await(taskId, Duration.ofSeconds(2));
```

所需类型：`java.util.Map/HashMap/List`、`java.time.Duration`，以及 `net.github.fastdev.asynctask` 下的 TaskEngine、TaskRecord、TaskQuery、TaskStatus、TaskResult。

- `submit(handler, tag, payload)` 返回任务 ID，仅表示任务已保存，不代表业务完成。tag 可为空，只用于过滤，不是幂等键或授权凭证；重复提交会生成不同任务。
- `query` 返回列表，可按业务编码、tag、状态及 `createTimeBetween(Timestamp, Timestamp)` 过滤；没有分页，也没有直接按任务 ID 查询的 TaskEngine API。
- TaskRecord 包含 taskId、status、retryCount、result、errorMsg 等。状态接口应映射为业务 DTO，校验任务归属并过滤敏感信息，不直接暴露完整 payload 或内部异常。
- `await` 是有界阻塞，不取消任务。当前 latch 只在本实例通知，且存在登记竞争；其他实例完成或竞争发生时可能直到超时后的兜底查询才返回。失败任务结果也可能为 null，不能只用 null 判定状态。

## 5. 状态与重试

```text
PENDING → 抢占 → RUNNING → 正常返回 → SUCCESS
                       ├─ 异常且可重试 → PENDING
                       ├─ 异常且次数耗尽 → FAILED
                       └─ 超过租约被扫描回收 → PENDING
```

找不到 Handler 时写 FAILED。枚举虽然包含 EXPIRED，当前回收实现实际重置为 PENDING，不会按该名称自动写 EXPIRED。

**Handler 正常返回 null 或 `TaskResult.failure()`，引擎仍写 SUCCESS。** failure 只改变结果对象的布尔值；需要走现有重试流程时必须抛异常。当前没有不可重试异常分类接口。最大重试次数不包含首次执行，超租约回收的次数处理另见实际 store，不能套用为同一套限制。

JDBC 拉取会检查 NEXT_RUN_TIME；Redis 当前写入 nextRunTime 后立即重新入队，poll 不检查到期时间，因此不保证配置的退避间隔。

## 6. 完整配置与调度

| 配置（前缀 fastdev.task） | 默认值 | 说明 |
| --- | --- | --- |
| store | 无 | 必须显式选 jdbc / redis，控制自动装配 |
| queue-name | 未设置 | 稳定命名空间，未设则尝试应用名 |
| workers | 4 | 单实例在途上限，包括拉取中和已提交未完成任务 |
| pollers | 2 | 独立轮询调度并发，各注册一个周期任务 |
| poll-interval-millis | 3000 | 毫秒，每次轮询完成到下次开始的间隔，也用于初次延迟 |
| lease-millis | 600000 | 毫秒，RUNNING 超时回收阈值，不是强制取消时限 |
| retry.max-retry | 3 | Handler 异常后的最大重试次数 |
| retry.backoff-millis | 30000 | 毫秒，退避配置；Redis 当前不保证实际延迟 |
| auto-create-table | false | JDBC 开发表初始化开关，见当前分支脚本缺口 |

所有 poller 共用 workers 个许可，抢占前获取，任务完成 finally 释放。没有容量就不抢占，任务继续留在存储中；每个轮询器每轮最多尝试拉取 32 条，无任务或异常时提前结束。执行完成不立即唤醒轮询。workers/pollers 小于 1 时按 1 处理，轮询间隔须配置为正值。

多实例并发会累加。按 CPU、内存、数据库/HTTP 连接和下游限流压测配置，不要绕过引擎的并发限制直接提交执行。回收扫描间隔为 `max(1000ms, leaseMillis / 2)`，不是精确到期立即回收。

## 7. 事务、幂等与可靠性边界

- JDBC 任务保存能否与业务修改原子提交，取决于是否使用同一 DataSource、事务管理器及事务传播；必须验证回滚。Redis 不会自动参与数据库事务。
- 提交后投递避免读到未提交数据，但业务提交后、任务保存前仍可能崩溃漏投。严格不漏投需同事务任务落库或 outbox/补偿，不把 afterCommit 当成可靠投递保证。不要在同一未提交事务内 submit 后 await。
- 用户/租户 ThreadLocal 不会跨线程、持久化和重启自动传播。提交前鉴权，显式保存必要标识；按租户调用业务。需恢复上下文时按项目 API 设置，并在 finally 清理，不能信任请求传入的 tenantId 或伪造管理员身份。
- 按可能重复执行设计幂等，使用稳定业务键、唯一约束或原子状态流转。不能承诺恰好一次。
- 无自动心跳续租，也无每次执行的凭证校验。超租约回收不会终止原 Handler，可能重复执行及旧结果覆盖；设置外部调用超时，合理规划租约。需要长任务续租或取消时单独扩展，当前没有现成 API。
- Redis 出队与状态抢占不是整体原子操作，中间崩溃有任务丢失消费机会的风险；回收与重试也有竞争限制。不要承诺严格不丢失，关键业务应专项修复并做故障测试。
- JDBC 无内建历史记录清理机制，需自行制定归档/清理；Redis TTL 和索引维护不是永久审计存储。

停机先将 running 置 false，关闭轮询，再关闭工作执行器，最后关闭回收扫描。各执行器最多等待 10 秒，超时发出中断，不保证业务被强制终止。极端在途抢占或提交拒绝时记录任务 ID，依赖租约回收恢复，不无条件改回 PENDING。

## 8. 排错与验证

| 现象 | 检查项 |
| --- | --- |
| 没有 TaskEngine Bean | store 是否显式配置、自动配置是否加载、依赖及 ObjectMapper/存储 Bean 是否齐全 |
| 任务一直不执行 | 稳定命名空间是否一致、队列/表是否可用、workers 是否占满、NEXT_RUN_TIME 是否到期 |
| handler not found / 编码冲突 | Handler 是否被组件扫描、所有实例是否有相同路由、编码是否更改/重复 |
| 自动建表失败 | 脚本资源缺失、DDL 权限、SQL 方言；生产改为审核迁移 |
| Redis 重试过快 | 当前 poll 没有按 nextRunTime 延迟消费 |
| 重复执行 / await 等待很久 | 租约、幂等、本地 latch、多实例完成与登记竞争 |

项目根目录执行：

```bash
mvn -pl spring-async-task-ctx -am test -Dtest=AbstractTaskEngineTest -Dsurefire.failIfNoSpecifiedTests=false
```

这些单元测试使用 mock 存储验证调度，不代表已验证真实 JDBC/Redis 的故障恢复。上线前还应验证保存与查询、事务回滚、异常重试、进程重启、多实例抢占、重复业务幂等、租约过期及权限隔离。
