package net.github.fastdev.asynctask;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <P><B>异步任务配置:</B></P>
 * 配置前缀fastdev.task
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@ConfigurationProperties(prefix = "fastdev.task")
public class AsyncTaskProperties {


    /**
     * 任务队列名，redis实现作为key前缀，jdbc实现作为服务隔离名。
     * 未配置时默认用 spring.application.name 做服务级隔离，避免多服务共用存储互相抢任务
     */
    private String queueName;

    /**
     * 解析后的命名空间，装配期写入：queueName显式配置值 或 服务名 或 随机名
     */
    private transient String resolvedNamespace;

    /**
     * 解析命名空间，多服务共用redis/DB时按服务隔离
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月20日
     *
     * @author Japson Huang
     */
    public String getNamespace() {
        if (resolvedNamespace != null && !resolvedNamespace.trim().isEmpty()) {
            return resolvedNamespace;
        }
        if (queueName != null && !queueName.trim().isEmpty()) {
            return queueName;
        }
        return "async-task";
    }

    /**
     * 装配期注入解析后的命名空间
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月20日
     *
     * @author Japson Huang
     */
    public void setNamespace(String namespace) {
        this.resolvedNamespace = namespace;
    }

    /**
     * 本实例最大在途任务数（拉取中及执行中），通过许可限制虚拟线程任务并发
     */
    private int workers = 4;

    /**
     * 空闲轮询间隔（毫秒），JDBC和Redis实现均使用非阻塞拉取
     */
    private long pollIntervalMillis = 3000;

    /**
     * RUNNING状态超时回收阈值（毫秒）
     */
    private long leaseMillis = 600000;

    /**
     * 轮询调度并发数，与任务执行并发独立；不包含单独的回收扫描线程
     */
    private int pollers = 2;

    /**
     * 重试配置
     */
    private Retry retry = new Retry();

    /**
     * jdbc实现：表不存在时自动建表，默认false
     */
    private boolean autoCreateTable = false;

    public boolean isAutoCreateTable() {
        return autoCreateTable;
    }

    public void setAutoCreateTable(boolean autoCreateTable) {
        this.autoCreateTable = autoCreateTable;
    }

    @ConfigurationProperties(prefix = "fastdev.task.retry")
    public static class Retry {

        /**
         * 默认最大重试次数
         */
        private int maxRetry = 3;

        /**
         * 重试退避时间（毫秒）
         */
        private long backoffMillis = 30000;

        public int getMaxRetry() {
            return maxRetry;
        }

        public void setMaxRetry(int maxRetry) {
            this.maxRetry = maxRetry;
        }

        public long getBackoffMillis() {
            return backoffMillis;
        }

        public void setBackoffMillis(long backoffMillis) {
            this.backoffMillis = backoffMillis;
        }
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }

    public long getPollIntervalMillis() {
        return pollIntervalMillis;
    }

    public void setPollIntervalMillis(long pollIntervalMillis) {
        this.pollIntervalMillis = pollIntervalMillis;
    }

    public long getLeaseMillis() {
        return leaseMillis;
    }

    public void setLeaseMillis(long leaseMillis) {
        this.leaseMillis = leaseMillis;
    }

    public int getPollers() {
        return pollers;
    }

    public void setPollers(int pollers) {
        this.pollers = pollers;
    }

    public Retry getRetry() {
        return retry;
    }

    public void setRetry(Retry retry) {
        this.retry = retry;
    }
}
