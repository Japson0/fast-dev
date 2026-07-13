package com.nlecloud.spring.scaffold.common;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.RegisteredPayload;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.UserInfoImpl;
import com.nlecloud.spring.annotation.api.UserInfoService;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

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

    private final RedisTemplate<String, byte[]> redisTemplate;

    public UserProxy(UserInfoService userinfoService, RedisTemplate<String, byte[]> redisTemplate) {
        this.userinfoService = userinfoService;
        this.redisTemplate = redisTemplate;
    }

    public UserInfo getUserInfo(Long userId, String jwtToken) {
        String cacheKey = String.format(USER_KEY, userId);
        JWT jwt = JWTUtil.parseToken(jwtToken);
        long iat = ((Number) jwt.getPayload(RegisteredPayload.ISSUED_AT)).longValue();
        long exp = ((Number) jwt.getPayload(RegisteredPayload.EXPIRES_AT)).longValue();
        CacheUser cacheUser = getCachedUser(cacheKey);
        if (cacheUser != null) {
            return iat > cacheUser.getIat()
                    ? cacheUser(cacheKey, userId, iat, exp)
                    : cacheUser.getUserInfo();
        }
        return cacheUser(cacheKey, userId, iat, exp);
    }

    public UserInfo getUserInfo(Long userId) {
        String cacheKey = String.format(USER_KEY, userId);
        CacheUser cacheUser = getCachedUser(cacheKey);
        return cacheUser != null
                ? cacheUser.getUserInfo()
                : cacheUser(cacheKey, userId, null, null);
    }

    private UserInfo cacheUser(String cacheKey, Long userId, Long iat, Long exp) {
        UserInfoImpl userInfo = getRemoteUserInfo(userId);
        CacheUser cacheUser = iat == null || exp == null
                ? new CacheUser(userInfo)
                : new CacheUser(userInfo, iat, exp);
        Duration timeout = iat == null || exp == null
                ? Duration.ofDays(1)
                : Duration.ofSeconds(exp - Instant.now().getEpochSecond());
        if (!timeout.isNegative() && !timeout.isZero()) {
            redisTemplate.opsForValue().set(cacheKey, serialize(cacheUser), timeout);
        }
        return cacheUser.getUserInfo();
    }

    private CacheUser getCachedUser(String cacheKey) {
        byte[] bytes = redisTemplate.opsForValue().get(cacheKey);
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            CacheUser cacheUser = CACHE_USER_SCHEMA.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes, cacheUser, CACHE_USER_SCHEMA);
            if (cacheUser.getUserInfo() != null) {
                return cacheUser;
            }
        } catch (RuntimeException exception) {
            // Values written by the previous JSON serializer are discarded on first read.
        }
        redisTemplate.delete(cacheKey);
        return null;
    }

    private byte[] serialize(CacheUser cacheUser) {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return ProtostuffIOUtil.toByteArray(cacheUser, CACHE_USER_SCHEMA, buffer);
        } finally {
            buffer.clear();
        }
    }

    private UserInfoImpl getRemoteUserInfo(Long userId) {
        UserInfo userInfo = userinfoService.getUserDetailById(userId.toString());
        Assert.notNull(userInfo,"用户信息不存在");
        if (!(userInfo instanceof UserInfoImpl)) {
            String type = userInfo == null ? "null" : userInfo.getClass().getName();
            throw new IllegalStateException("Unsupported UserInfo implementation: " + type);
        }
        return (UserInfoImpl) userInfo;
    }
}
