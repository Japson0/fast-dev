package net.github.fast.distributed.lock.redis;

import net.github.fast.distributed.lock.starter.DistributedLockHandler;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * <P><B>分布式redis配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年06月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
public class DistributeRedisConfig {


    @Bean
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonProvider redissonProvider(RedisConnectionFactory redisConnectionFactory){
        return new RedissonProvider(redisConnectionFactory);
    }

    @Bean
    public DistributedLockHandler distributedLockHandler(RedissonClient redissonClient) {
        return new RedisDistributeLockHandler(redissonClient);
    }


}
