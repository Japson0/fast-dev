package net.github.fastdev.asynctask;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <P><B>抽象任务引擎:</B></P>
 * 通用调度逻辑，与存储无关：虚拟线程执行、并发限制、重试、超时回收、businessCode路由。
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public abstract class AbstractTaskEngine implements TaskEngine, SmartInitializingSingleton, DisposableBean {


    private static final Logger logger = LoggerFactory.getLogger(AbstractTaskEngine.class);

    private static final int POLL_BATCH_SIZE = 32;

    protected final TaskStore store;

    protected final ApplicationContext applicationContext;

    protected final AsyncTaskProperties properties;

    private final ObjectMapper objectMapper;

    /**
     * businessCode -> handler
     */
    private final Map<String, TaskHandler> handlerRegistry = new ConcurrentHashMap<>();

    /**
     * taskId -> 等待锁
     */
    private final Map<String, CountDownLatch> awaitLatchs = new ConcurrentHashMap<>();

    /**
     * 每任务一个虚拟线程，由executionPermits限制在途任务数
     */
    private ExecutorService workerPool;

    private Semaphore executionPermits;

    /**
     * 轮询线程（每poller一个poll调度）
     */
    private ScheduledExecutorService pollPool;

    /**
     * 超时回收扫描
     */
    private ScheduledExecutorService recyclePool;

    private volatile boolean running = false;

    protected AbstractTaskEngine(TaskStore store, ApplicationContext applicationContext,
                                 AsyncTaskProperties properties, ObjectMapper objectMapper) {
        this.store = store;
        this.applicationContext = applicationContext;
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    @Override
    public String submit(TaskHandler handler, String tag, Object payload) {
        if (handler == null) {
            throw new IllegalArgumentException("handler不能为空");
        }
        String businessCode = handler.businessCode();
        if (businessCode == null || businessCode.trim().isEmpty()) {
            throw new IllegalArgumentException("handler的businessCode不能为空");
        }
        TaskHandler existed = handlerRegistry.get(businessCode);
        if (existed != null && existed != handler) {
            throw new IllegalArgumentException("businessCode冲突，已存在同编码Handler:" + businessCode);
        }
        if (applicationContext.getBeanNamesForType(handler.getClass()).length == 0) {
            throw new IllegalArgumentException("handler必须是Spring容器中的bean，"
                    + "lambda/匿名实例无法在重启后恢复:" + handler.getClass().getName());
        }
        String payloadJson = serializePayload(payload);
        TaskRecord record = new TaskRecord();
        record.setBusinessCode(businessCode);
        record.setTag(tag);
        record.setPayload(payloadJson);
        record.setStatus(TaskStatus.PENDING);
        record.setRetryCount(0);
        return store.save(record);
    }

    @Override
    public TaskResult await(String taskId, Duration timeout) {
        TaskRecord record = store.get(taskId);
        if (record == null) {
            return null;
        }
        if (record.getStatus() != TaskStatus.PENDING && record.getStatus() != TaskStatus.RUNNING) {
            return record.getResult();
        }
        CountDownLatch latch = awaitLatchs.computeIfAbsent(taskId, k -> new CountDownLatch(1));
        try {
            if (latch.await(timeout.toMillis(), TimeUnit.MILLISECONDS)) {
                TaskRecord finTask = store.get(taskId);
                return finTask == null ? null : finTask.getResult();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            awaitLatchs.remove(taskId, latch);
        }
        // 超时兜底再查一次
        TaskRecord finTask = store.get(taskId);
        return finTask == null ? null : finTask.getResult();
    }

    @Override
    public List<TaskRecord> query(TaskQuery query) {
        return store.query(query);
    }

    /**
     * 启动时自动收集容器里所有TaskHandler bean注册路由表
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     */
    @Override
    public void afterSingletonsInstantiated() {
        Map<String, TaskHandler> handlers = applicationContext.getBeansOfType(TaskHandler.class);
        for (TaskHandler handler : handlers.values()) {
            String code = handler.businessCode();
            if (code == null || code.trim().isEmpty()) {
                throw new IllegalStateException("TaskHandler的businessCode不能为空:" + handler.getClass());
            }
            TaskHandler existed = handlerRegistry.putIfAbsent(code, handler);
            if (existed != null) {
                throw new IllegalStateException("businessCode冲突:" + code + "，"
                        + existed.getClass() + " 与 " + handler.getClass());
            }
        }
        logger.info("异步任务引擎初始化完成，注册Handler数量:{}", handlerRegistry.size());
        start();
    }

    protected void start() {
        running = true;
        int workers = Math.max(1, properties.getWorkers());
        int pollers = Math.max(1, properties.getPollers());
        executionPermits = new Semaphore(workers);
        workerPool = Executors.newThreadPerTaskExecutor(
                Thread.ofVirtual().name("async-task-worker-", 0).factory());
        pollPool = Executors.newScheduledThreadPool(pollers, namedThreadFactory("async-task-poll"));
        for (int i = 0; i < pollers; i++) {
            pollPool.scheduleWithFixedDelay(this::pollAndExecute, properties.getPollIntervalMillis(),
                    properties.getPollIntervalMillis(), TimeUnit.MILLISECONDS);
        }
        recyclePool = Executors.newSingleThreadScheduledExecutor(namedThreadFactory("async-task-recycle"));
        recyclePool.scheduleWithFixedDelay(this::recycle, Math.max(1000L, properties.getLeaseMillis() / 2),
                Math.max(1000L, properties.getLeaseMillis() / 2), TimeUnit.MILLISECONDS);
    }

    protected void stop() {
        running = false;
        shutdownPool(pollPool);
        shutdownPool(workerPool);
        shutdownPool(recyclePool);
    }

    private void pollAndExecute() {
        for (int i = 0; i < POLL_BATCH_SIZE && running; i++) {
            if (!executionPermits.tryAcquire()) {
                return;
            }
            boolean submitted = false;
            TaskRecord record = null;
            try {
                if (!running) {
                    return;
                }
                record = store.poll(lockOwner());
                if (record == null) {
                    return;
                }
                TaskRecord claimed = record;
                workerPool.execute(() -> {
                    try {
                        execute(claimed);
                    } finally {
                        executionPermits.release();
                    }
                });
                submitted = true;
            } catch (RejectedExecutionException e) {
                // 不无条件归还任务，避免覆盖其他执行批次；由现有租约回收恢复。
                logger.error("任务提交被拒绝，等待租约回收, taskId:{}",
                        record == null ? null : record.getTaskId(), e);
                return;
            } catch (Exception e) {
                logger.error("拉取任务异常", e);
                return;
            } finally {
                if (!submitted) {
                    executionPermits.release();
                }
            }
        }
    }

    private void execute(TaskRecord record) {
        String taskId = record.getTaskId();
        String businessCode = record.getBusinessCode();
        TaskHandler handler = handlerRegistry.get(businessCode);
        if (handler == null) {
            store.updateStatus(taskId, TaskStatus.FAILED, null, "handler not found: " + businessCode);
            completeAwait(taskId);
            return;
        }
        Map<String, Object> payload = deserializePayload(record.getPayload());
        TaskContext context = new TaskContext(taskId, businessCode, record.getTag(), payload);
        try {
            TaskResult result = handler.handle(context);
            result = result == null ? TaskResult.success() : result;
            store.updateStatus(taskId, TaskStatus.SUCCESS, result, null);
            completeAwait(taskId);
        } catch (Exception e) {
            logger.error("任务执行异常, taskId:{}, businessCode:{}", taskId, businessCode, e);
            boolean retryScheduled = store.scheduleRetry(taskId, properties.getRetry().getMaxRetry());
            if (!retryScheduled) {
                store.updateStatus(taskId, TaskStatus.FAILED, null, e.getMessage());
                completeAwait(taskId);
            } else {
                TaskRecord latest = store.get(taskId);
                if (latest != null && latest.getStatus() == TaskStatus.FAILED) {
                    completeAwait(taskId);
                }
            }
        }
    }

    private void recycle() {
        try {
            int count = store.recycleExpired(properties.getLeaseMillis());
            if (count > 0) {
                logger.warn("回收超时RUNNING任务数:{}", count);
            }
        } catch (Exception e) {
            logger.error("回收超时任务异常", e);
        }
    }

    private void completeAwait(String taskId) {
        CountDownLatch latch = awaitLatchs.remove(taskId);
        if (latch != null) {
            latch.countDown();
        }
    }

    private String serializePayload(Object payload) {
        if (payload == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (IOException e) {
            throw new IllegalArgumentException("payload序列化失败:" + e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> deserializePayload(Object payload) {
        if (payload == null) {
            return new HashMap<>();
        }
        try {
            if (payload instanceof Map) {
                return (Map<String, Object>) payload;
            }
            return (Map<String, Object>) objectMapper.readValue(String.valueOf(payload), Map.class);
        } catch (Exception e) {
            logger.warn("payload反序列化失败，按空参数处理:{}", e.getMessage());
            return new HashMap<>();
        }
    }

    private ThreadFactory namedThreadFactory(String prefix) {
        AtomicInteger counter = new AtomicInteger(0);
        return r -> {
            Thread thread = new Thread(r, prefix + "-" + counter.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        };
    }

    private void shutdownPool(ExecutorService pool) {
        if (pool == null) {
            return;
        }
        pool.shutdown();
        try {
            if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 集群抢占标识，本实例唯一标识
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     */
    protected String lockOwner() {
        return LOCK_OWNER;
    }

    private static final String LOCK_OWNER = (System.getProperty("instance.id") != null
            ? System.getProperty("instance.id") + "@" : "") + runtimeName();

    private static String runtimeName() {
        try {
            return java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        } catch (Exception e) {
            return "unknown";
        }
    }

    @Override
    public void destroy() {
        stop();
    }

    protected boolean isRunning() {
        return running;
    }
}
