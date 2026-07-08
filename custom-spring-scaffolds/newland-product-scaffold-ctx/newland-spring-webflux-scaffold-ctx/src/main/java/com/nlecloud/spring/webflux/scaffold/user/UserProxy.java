package com.nlecloud.spring.webflux.scaffold.user;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.RegisteredPayload;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.UserInfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserProxy {

    private final UserInfoService userinfoService;

    private static final String USER_KEY = "USER_INFO_KEY:%d";

    private final ReactiveRedisTemplate<String, Object> redisTemplate;


    public UserProxy(UserInfoService userinfoService,ReactiveRedisTemplate<String, Object> redisTemplate) {
        this.userinfoService = userinfoService;
        this.redisTemplate = redisTemplate;
    }



    public Mono<UserInfoImpl> getUserInfo(Long userId, String jwtToken) {
        if (redisTemplate == null) {
            return getRemoteUserInfo(userId);
        }
        String cacheKey = String.format(USER_KEY, userId);
        JWT jwt = JWTUtil.parseToken(jwtToken);
        long iat =((Number) jwt.getPayload(RegisteredPayload.ISSUED_AT)).longValue();
        long exp =((Number) jwt.getPayload(RegisteredPayload.EXPIRES_AT)).longValue();
        return redisTemplate.opsForValue().get(cacheKey)
                .cast(String.class)
                .filter(StringUtils::hasText)
                .map(userJson -> JacksonUtils.toObj(userJson, CacheUser.class))
                .flatMap(cacheUser -> iat > cacheUser.getIat()
                        ? cacheUser(cacheKey, userId, iat, exp)
                        : Mono.just(cacheUser.getUserInfo()))
                .switchIfEmpty(cacheUser(cacheKey, userId, iat, exp));
    }

    public Mono<UserInfoImpl> getUserInfo(Long userId) {
        if (redisTemplate == null) {
            return getRemoteUserInfo(userId);
        }
        String cacheKey = String.format(USER_KEY, userId);
        return redisTemplate.opsForValue().get(cacheKey)
                .cast(String.class)
                .filter(StringUtils::hasText)
                .map(userJson -> JacksonUtils.toObj(userJson, CacheUser.class).getUserInfo())
                .switchIfEmpty(cacheUser(cacheKey, userId, null, null));
    }

    private Mono<UserInfoImpl> cacheUser(String cacheKey, Long userId, Long iat, Long exp) {
        return getRemoteUserInfo(userId)
                .flatMap(userInfo -> {
                    if (redisTemplate == null) {
                        return Mono.just(userInfo);
                    }
                    CacheUser cacheUser = iat == null || exp == null ? new CacheUser(userInfo) : new CacheUser(userInfo, iat, exp);
                    Duration timeout = iat == null || exp == null ? Duration.ofDays(1) : Duration.of(exp - iat, ChronoUnit.SECONDS);
                    return redisTemplate.opsForValue()
                            .set(cacheKey, JacksonUtils.toJson(cacheUser), timeout)
                            .thenReturn(cacheUser.getUserInfo());
                });
    }


    private Mono<UserInfoImpl> getRemoteUserInfo(Long userId) {
        return userinfoService.getUserDetailById(userId);
    }

}
