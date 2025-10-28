package net.github.fast.distributed.lock.zk;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <P><B>zk配置类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年06月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@ConfigurationProperties(prefix = "zookeeper")
public class ZookeeperProperty {

    /**
     * 服务器地址
     */
    private String connectString;


    private int sessionTimeoutMs=3000;

    /**
     * 连接超时时间
     */
    private int connectionTimeoutMs=60000;

    /**
     * 最大重试次数
     */
    private int maxRetries=3;

    /**
     * 初始休眠时间
     */
    private int baseSleepTimeMs=1000;

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public int getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public int getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }
}
