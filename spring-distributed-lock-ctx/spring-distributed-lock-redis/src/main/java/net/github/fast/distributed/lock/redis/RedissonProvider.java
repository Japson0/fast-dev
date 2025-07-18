package net.github.fast.distributed.lock.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RedissonProvider implements FactoryBean<RedissonClient> {
    
    private final RedisConnectionFactory redisConnectionFactory;

    public RedissonProvider(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Override
    public RedissonClient getObject() throws Exception {
        Config config = new Config();

        if (redisConnectionFactory instanceof LettuceConnectionFactory) {
            LettuceConnectionFactory factory = (LettuceConnectionFactory) redisConnectionFactory;
            if (factory.getClusterConfiguration() != null) {
                // Lettuce集群模式
                config.useClusterServers()
                        .addNodeAddress(factory.getClusterConfiguration().getClusterNodes()
                                .stream()
                                .map(node -> "redis://" + node.getHost() + ":" + node.getPort())
                                .toArray(String[]::new));
            } else {
                // Lettuce单机模式
                config.useSingleServer()
                        .setAddress("redis://" + factory.getHostName() + ":" + factory.getPort());
            }

            if (factory.getPassword() != null) {
                String password = new String(factory.getPassword());
                config.useSingleServer().setPassword(password);
                // 或者对于集群: config.useClusterServers().setPassword(password);
            }
        }

        return Redisson.create(config);
    }

    @Override
    public Class<?> getObjectType() {
        return RedissonClient.class;
    }
}