package com.nlecloud.spring.webflux.scaffold.user;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.RegisteredPayload;
import com.nlecloud.spring.annotation.UserInfoImpl;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

/**
 * <P><B>用户代理服务:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserProxy {

    private final UserInfoService userinfoService;

    private static final String USER_KEY = "USER_INFO_KEY:v1:%d";

    private static final Schema<CacheUser> CACHE_USER_SCHEMA = RuntimeSchema.getSchema(CacheUser.class);

    private final ReactiveRedisTemplate<String, byte[]> redisTemplate;

    public UserProxy(UserInfoService userinfoService, ReactiveRedisTemplate<String, byte[]> redisTemplate) {
        this.userinfoService = userinfoService;
        this.redisTemplate = redisTemplate;
    }

    public Mono<UserInfoImpl> getUserInfo(Long userId, String jwtToken) {
        if (redisTemplate == null) {
            return getRemoteUserInfo(userId);
        }
        String cacheKey = String.format(USER_KEY, userId);
        JWT jwt = JWTUtil.parseToken(jwtToken);
        long iat = ((Number) jwt.getPayload(RegisteredPayload.ISSUED_AT)).longValue();
        long exp = ((Number) jwt.getPayload(RegisteredPayload.EXPIRES_AT)).longValue();
        return getCachedUser(cacheKey)
                .flatMap(cacheUser -> iat > cacheUser.getIat()
                        ? cacheUser(cacheKey, userId, iat, exp)
                        : Mono.just(cacheUser.getUserInfo()))
                .switchIfEmpty(Mono.defer(() -> cacheUser(cacheKey, userId, iat, exp)));
    }

    public Mono<UserInfoImpl> getUserInfo(Long userId) {
        if (redisTemplate == null) {
            return getRemoteUserInfo(userId);
        }
        String cacheKey = String.format(USER_KEY, userId);
        return getCachedUser(cacheKey)
                .map(CacheUser::getUserInfo)
                .switchIfEmpty(Mono.defer(() -> cacheUser(cacheKey, userId, null, null)));
    }

    private Mono<UserInfoImpl> cacheUser(String cacheKey, Long userId, Long iat, Long exp) {
        return getRemoteUserInfo(userId)
                .flatMap(userInfo -> {
                    if (redisTemplate == null) {
                        return Mono.just(userInfo);
                    }
                    CacheUser cacheUser = iat == null || exp == null
                            ? new CacheUser(userInfo)
                            : new CacheUser(userInfo, iat, exp);
                    Duration timeout = iat == null || exp == null
                            ? Duration.ofDays(1)
                            : Duration.ofSeconds(exp - Instant.now().getEpochSecond());
                    if (timeout.isNegative() || timeout.isZero()) {
                        return Mono.just(cacheUser.getUserInfo());
                    }
                    return redisTemplate.opsForValue()
                            .set(cacheKey, serialize(cacheUser), timeout)
                            .thenReturn(cacheUser.getUserInfo());
                });
    }

    private Mono<CacheUser> getCachedUser(String cacheKey) {
        return redisTemplate.opsForValue().get(cacheKey)
                .flatMap(bytes -> {
                    try {
                        CacheUser cacheUser = deserialize(bytes);
                        return cacheUser.getUserInfo() == null
                                ? deleteInvalidCache(cacheKey)
                                : Mono.just(cacheUser);
                    } catch (RuntimeException exception) {
                        return deleteInvalidCache(cacheKey);
                    }
                });
    }

    private Mono<CacheUser> deleteInvalidCache(String cacheKey) {
        return redisTemplate.delete(cacheKey).then(Mono.empty());
    }

    private CacheUser deserialize(byte[] bytes) {
        CacheUser cacheUser = CACHE_USER_SCHEMA.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, cacheUser, CACHE_USER_SCHEMA);
        return cacheUser;
    }

    private byte[] serialize(CacheUser cacheUser) {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return ProtostuffIOUtil.toByteArray(cacheUser, CACHE_USER_SCHEMA, buffer);
        } finally {
            buffer.clear();
        }
    }

    private Mono<UserInfoImpl> getRemoteUserInfo(Long userId) {
        return userinfoService.getUserDetailById(userId);
    }
}
