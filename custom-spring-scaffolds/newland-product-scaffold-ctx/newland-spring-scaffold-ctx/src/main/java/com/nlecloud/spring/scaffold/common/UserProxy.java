package com.nlecloud.spring.scaffold.common;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.RegisteredPayload;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.api.UserInfoService;
import net.github.fastdev.boot.utils.JacksonUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

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

    private final RedisTemplate<String, String> redisTemplate;


    public UserProxy(UserInfoService userinfoService, RedisTemplate redisTemplate) {
        this.userinfoService = userinfoService;
        this.redisTemplate = redisTemplate;
    }

    public UserInfo getUserInfo(Long userId, String jwtToken) {
        String cacheKey = String.format(USER_KEY, userId);
        String userJson = redisTemplate.opsForValue().get(cacheKey);
        JWT jwt = JWTUtil.parseToken(jwtToken);
        long iat =((Number) jwt.getPayload(RegisteredPayload.ISSUED_AT)).longValue();
        long exp =((Number) jwt.getPayload(RegisteredPayload.EXPIRES_AT)).longValue();
        if (StringUtils.hasText(userJson)) {
            CacheUser cacheUser = JacksonUtils.toBean(userJson, CacheUser.class);
            return iat>cacheUser.getIat()? cacheUser(cacheKey, userId, iat, exp) : cacheUser.getUserInfo();
        } else {
            return cacheUser(cacheKey, userId, iat, exp);
        }
    }

    public UserInfo getUserInfo(Long userId) {
        String cacheKey = String.format(USER_KEY, userId);
        String userJson = redisTemplate.opsForValue().get(cacheKey);
        if (userJson != null) {
            return JacksonUtils.toBean(userJson, CacheUser.class).getUserInfo();
        } else {
            UserInfo remoteUserInfo = getRemoteUserInfo(userId);
            redisTemplate.opsForValue().set(cacheKey, JacksonUtils.toJson(new CacheUser(remoteUserInfo)), Duration.ofDays(1));
            return remoteUserInfo;
        }
    }

    private UserInfo cacheUser(String cacheKey, Long userId, Long iat, Long exp) {
        UserInfo userInfo = getRemoteUserInfo(userId);
        CacheUser cacheUser=new CacheUser(userInfo,iat,exp);
        redisTemplate.opsForValue().set(cacheKey, JacksonUtils.toJson(cacheUser), Duration.of(exp - iat, ChronoUnit.SECONDS));
        return cacheUser.getUserInfo();
    }


    private UserInfo getRemoteUserInfo(Long userId) {
        return userinfoService.getUserDetailById(userId.toString());
    }

}
