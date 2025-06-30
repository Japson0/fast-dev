package net.github.fast.distributed.lock.zk;

import net.github.fast.distributed.lock.starter.DistributedLockHandler;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年06月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
@EnableConfigurationProperties(ZookeeperProperty.class)
public class DistributeZookeeperConfig {

    @ConditionalOnMissingBean(CuratorFramework.class)
    @Bean
    public CuratorProvider curatorProvider(ZookeeperProperty zookeeperProperty){
        return new CuratorProvider(zookeeperProperty);
    }

    @Bean
    public DistributedLockHandler distributedLockHandler(CuratorFramework curatorFramework){
        return new ZookeeperDistributeLockHandler(curatorFramework);
    }
}
