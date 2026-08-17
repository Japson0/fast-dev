package com.nlecloud.spring.scaffold.common;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.RegisteredPayload;
import com.nlecloud.spring.annotation.api.UserInfoDetail;
import com.nlecloud.spring.annotation.api.UserInfoService;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import com.nlecloud.spring.common.utils.KeyCacheUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(UserProxy.class);
    private final UserInfoService userinfoService;


    private static final Schema<CacheUser> CACHE_USER_SCHEMA = RuntimeSchema.getSchema(CacheUser.class);

    private final RedisTemplate<String, byte[]> redisTemplate;

    public UserProxy(UserInfoService userinfoService, RedisTemplate<String, byte[]> redisTemplate) {
        this.userinfoService = userinfoService;
        this.redisTemplate = redisTemplate;
    }

    public UserInfoDetail getUserInfo(Long userId, String jwtToken) {
        String cacheKey = KeyCacheUtils.userKey(userId);
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

    public UserInfoDetail getUserInfo(Long userId) {
        String cacheKey = KeyCacheUtils.userKey(userId);
        CacheUser cacheUser = getCachedUser(cacheKey);
        return cacheUser != null
                ? cacheUser.getUserInfo()
                : cacheUser(cacheKey, userId, null, null);
    }

    private UserInfoDetail cacheUser(String cacheKey, Long userId, Long iat, Long exp) {
        UserInfoDetail userInfo = getRemoteUserInfo(userId);
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
            log.error("用户上下文序列化错误",exception);
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

    private UserInfoDetail getRemoteUserInfo(Long userId) {
        UserInfoDetail userInfo = userinfoService.getUserDetailById(userId.toString());
        Assert.notNull(userInfo,"用户信息不存在");
        return userInfo;
    }
}
