package net.evecom.fastdev.cache.redis;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.ProxyCachingConfiguration;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <P><B>Redis默认配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月02日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
@ConditionalOnBean(ProxyCachingConfiguration.class)
public class RedisCacheConfig extends CacheProperties {

    /**
     * 分隔符
     */
    private static final String SPLIT = ":";

    private RedisCacheConfiguration cacheConfiguration() {

        Redis redis = this.getRedis();
        Duration timeToLive = redis.getTimeToLive();
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        if (timeToLive == null) {
            timeToLive = Duration.ofHours(12);
        }
        if (this.getRedis().isUseKeyPrefix() && !StringUtils.isEmpty(getRedis().getKeyPrefix())) {
            cacheConfig = cacheConfig.computePrefixWith(name ->
                    getRedis().getKeyPrefix() + SPLIT + name + SPLIT
            );
        }

        return cacheConfig.serializeValuesWith(RedisSerializationContext.
                SerializationPair.fromSerializer(RedisSerializer.java()))
                .entryTtl(timeToLive);
    }


    @Bean("simplateKeyGenerator")
    @ConditionalOnMissingBean(KeyGenerator.class)
    public KeyGenerator keyGenerator() {
        return new SimplateKeyGenerator();
    }

    @Bean
    public RedisCacheManager cacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisCacheManager.RedisCacheManagerBuilder redisCacheManagerBuilder = RedisCacheManager.builder(lettuceConnectionFactory)
                .transactionAware();
        Redis redis = this.getRedis();
        List<String> cacheNames = this.getCacheNames();
        Duration defaultDuration = redis.getTimeToLive();
        Map<String, RedisCacheConfiguration> configurationMap;
        if (cacheNames != null) {
            configurationMap = new LinkedHashMap<>(cacheNames.size() + 1);
            for (String cache : cacheNames) {
                String[] cacheAndTime = cache.split("#");
                Duration duration = cacheAndTime.length < 2 ? defaultDuration : Duration.ofSeconds(Long.parseLong(cacheAndTime[1]));
                RedisCacheConfiguration redisCacheConfiguration = this.cacheConfiguration().entryTtl(duration);

                configurationMap.put(cacheAndTime[0], redisCacheConfiguration);
            }
        } else {
            configurationMap = new LinkedHashMap<>(1);
        }
        configurationMap.put(RedisTime.DICTIONARY, RedisCacheConfiguration.defaultCacheConfig()
                .disableKeyPrefix().entryTtl(Duration.ofDays(7))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(ProtoBufSerializationRedisSerializer.getInstance())));
        redisCacheManagerBuilder.withInitialCacheConfigurations(configurationMap);
        return redisCacheManagerBuilder.build();
    }


    @Bean
    @Primary
    public RedisTemplate<Object, Object> redisTemplate(
            LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.java());
//        template.setValueSerializer(ProtoBufSerializationRedisSerializer.getInstance());
        template.setConnectionFactory(lettuceConnectionFactory);
        return template;
    }

    @Bean
    public CacheHandle cacheHandle(CacheManager cacheManager) {
        return new CacheHandle(cacheManager);
    }
}
