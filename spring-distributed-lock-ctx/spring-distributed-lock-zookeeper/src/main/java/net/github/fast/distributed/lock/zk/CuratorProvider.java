package net.github.fast.distributed.lock.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class CuratorProvider implements FactoryBean<CuratorFramework> {

    private final ZookeeperProperty zookeeperProperty;

    public CuratorProvider(ZookeeperProperty zookeeperProperty) {
        this.zookeeperProperty = zookeeperProperty;
    }


    @Override
    public CuratorFramework getObject() throws Exception {
        return CuratorFrameworkFactory.builder()
                .connectString(zookeeperProperty.getConnectString())
                .sessionTimeoutMs(zookeeperProperty.getSessionTimeoutMs())
                .connectionTimeoutMs(zookeeperProperty.getConnectionTimeoutMs())
                .retryPolicy(new ExponentialBackoffRetry(zookeeperProperty.getBaseSleepTimeMs(), zookeeperProperty.getMaxRetries()))
                .build();
    }

    @Override
    public Class<?> getObjectType() {
        return CuratorFramework.class;
    }
}