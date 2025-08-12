package com.nlecloud.spring.webflux.scaffold;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {  // 注入 Spring Boot 默认的 ObjectMapper

        // 1. 使用 Jackson 序列化 Value
        RedisSerializer jacksonSerializer = new GenericJackson2JsonRedisSerializer();

        // 2. 构建 RedisSerializationContext
        RedisSerializationContext<String, Object> context =
                RedisSerializationContext.<String, Object>newSerializationContext()
                        .key(new StringRedisSerializer())    // Key 用 String 序列化
                        .value(jacksonSerializer)            // Value 用 Jackson
                        .hashKey(new StringRedisSerializer())
                        .hashValue(jacksonSerializer)
                        .build();

        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }
}