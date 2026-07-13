package com.nlecloud.spring.webflux.scaffold;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
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
