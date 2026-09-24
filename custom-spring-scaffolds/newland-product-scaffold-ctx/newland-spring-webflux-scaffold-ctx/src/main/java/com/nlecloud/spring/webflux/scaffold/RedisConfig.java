package com.nlecloud.spring.webflux.scaffold;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.data.redis.autoconfigure.DataRedisAutoConfiguration;
import org.springframework.boot.data.redis.autoconfigure.DataRedisReactiveAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@AutoConfiguration(
        after = DataRedisAutoConfiguration.class,
        before = DataRedisReactiveAutoConfiguration.class
)

public class RedisConfig {

    @Bean
    @ConditionalOnBean(ReactiveRedisConnectionFactory.class)
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {
        RedisSerializer<Object> jsonSerializer = GenericJacksonJsonRedisSerializer.builder().build();
        RedisSerializationContext<String, Object> context =
                RedisSerializationContext.<String, Object>newSerializationContext()
                        .key(RedisSerializer.string())
                        .value(jsonSerializer)
                        .hashKey(RedisSerializer.string())
                        .hashValue(jsonSerializer)
                        .build();

        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }

    @Bean("userCacheReactiveRedisTemplate")
    @ConditionalOnBean(ReactiveRedisConnectionFactory.class)
    public ReactiveRedisTemplate<String, byte[]> userCacheReactiveRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {
        RedisSerializationContext<String, byte[]> context =
                RedisSerializationContext.<String, byte[]>newSerializationContext()
                        .key(RedisSerializer.string())
                        .value(RedisSerializer.byteArray())
                        .hashKey(RedisSerializer.string())
                        .hashValue(RedisSerializer.byteArray())
                        .build();

        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }
}
