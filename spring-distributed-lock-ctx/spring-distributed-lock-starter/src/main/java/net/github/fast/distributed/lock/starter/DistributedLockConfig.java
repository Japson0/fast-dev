package net.github.fast.distributed.lock.starter;

import net.github.fast.distributed.lock.starter.aspect.DistributedLockAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <P><B>分布式锁配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年06月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
public class DistributedLockConfig {

    @ConditionalOnBean(DistributedLockHandler.class)
    @Bean
    public DistributedLockAspect distributedLockAspect(DistributedLockHandler distributedLockHandler){
        return new DistributedLockAspect(distributedLockHandler);
    }
}
