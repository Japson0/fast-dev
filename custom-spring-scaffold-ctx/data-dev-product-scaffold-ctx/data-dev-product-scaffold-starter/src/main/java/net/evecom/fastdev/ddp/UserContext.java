/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.ddp;

import cn.hutool.core.lang.Assert;
import cn.hutool.extra.spring.SpringUtil;
import net.evecom.fastdev.boot.utils.JacksonUtils;
import net.evecom.fastdev.common.exception.NoUserInfoException;
import net.evecom.fastdev.common.exception.ResourceException;
import net.evecom.fastdev.ddp.handle.DataDevTenantHandler;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.Objects;
import java.util.function.Function;

/**
 * <P><B>用户工具类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年10月29日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class UserContext {

    /**
     * 用户信息
     */
    private final static ThreadLocal<UserInfo> USER_INFO_LOCAL;

    /**
     * 无用户信息错误
     */
    private static final IllegalAccessError ILLEGAL_ACCESS_ERROR = new IllegalAccessError("该接口无用户信息");

    /**
     * redis
     */
    private static final Function<String, UserResource> USER_RESOURCE_FUNCTION;

    private UserContext() {
    }

    static {
        DataDevProductProperties properties = null;
        try {
            properties = SpringUtil.getBean(DataDevProductProperties.class);
        } catch (Exception ignore) {
        }
        if (properties != null && properties.getDebug() != null && properties.getDebug().isEnable()) {
            DataDevProductProperties finalProperties = properties;
            DataDevProductProperties.DebuggerUser user = finalProperties.getDebug().getUser();
            user.setInit(true);
//            USER_INFO_LOCAL = ThreadLocal.withInitial(() -> user);
            USER_INFO_LOCAL = new ThreadLocal<UserInfo>() {
                @Override
                protected UserInfo initialValue() {
                    return user;
                }

                @Override
                public void set(UserInfo value) {
                }
            };
        } else {
            USER_INFO_LOCAL = new ThreadLocal<>();
        }
        RedisTemplate<String, String> temp;
        try {
            LettuceConnectionFactory lettuceConnectionFactory = SpringUtil.getBean(LettuceConnectionFactory.class);
            temp = new RedisTemplate<>();
            temp.setKeySerializer(RedisSerializer.string());
            temp.setValueSerializer(RedisSerializer.string());
            temp.setConnectionFactory(lettuceConnectionFactory);
            temp.afterPropertiesSet();
        } catch (Exception ignore) {
            temp = null;
        }
        RedisTemplate<String, String> redisTemplate = temp;
        USER_RESOURCE_FUNCTION = (key) -> {
            if (redisTemplate == null) {
                throw new ResourceException("未初始化用户资源接口");
            }
            String userJson = redisTemplate.opsForValue().get(key);
            if (userJson == null) {
                throw NoUserInfoException.getInstance();
            }
            return JacksonUtils.toBean(userJson, UserResource.class);
        };

    }

    public static void setUserInfo(String jwtClaims) {
        if (jwtClaims != null) {
            UserInfo userInfo = new UserInfo(Objects.requireNonNull(JacksonUtils.toBean(jwtClaims, JwtClaims.class)));
            USER_INFO_LOCAL.set(userInfo);
        }
    }


    public static void clean() {
        USER_INFO_LOCAL.remove();
        DataDevTenantHandler.IGNORE_LOCAL.remove();
    }

    public static void ignoreTenant() {
        DataDevTenantHandler.IGNORE_LOCAL.set(true);
    }

    public static Long getUserId() {
        UserInfo userInfo = USER_INFO_LOCAL.get();
        return userInfo != null ? userInfo.getUserId() : null;
    }

    public static String getUserName() {
        UserInfo userInfo = USER_INFO_LOCAL.get();
        return userInfo != null ? userInfo.getUsername() : null;
    }

    public static Long getTenantId() {
        UserInfo userInfo = USER_INFO_LOCAL.get();
        return userInfo != null ? userInfo.getTenantId() : null;
    }


    public static boolean isAdmin() {
        return getUserInfo().isAdmin();
    }


    public static UserInfo getUserInfo() {
        UserInfo userInfo = getUserInfo(true);
        Assert.notNull(userInfo, "---------------用户信息为空--------------");
        return userInfo;
    }

    public static UserInfo getUserInfo(boolean init) {
        UserInfo userInfo = USER_INFO_LOCAL.get();
        if (init && userInfo != null && !userInfo.isInit()) {
            initUserInfo(userInfo);
        }
        return userInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        USER_INFO_LOCAL.set(userInfo);
    }


    private static void initUserInfo(UserInfo userInfo) {
        if (userInfo.getUserId() == null && userInfo.getClientId() == null) {
            throw ILLEGAL_ACCESS_ERROR;
        }
        UserResource userResource = USER_RESOURCE_FUNCTION.apply(userInfo.getClientId() + "_" + userInfo.getUsername());
        if (userResource == null) {
            throw NoUserInfoException.getInstance();
        }
        USER_INFO_LOCAL.get().init(userResource);
    }

    /**
     * 是否包含角色
     * RevisionTrail:(Date/Author/Description)
     * 2022年10月27日
     *
     * @author Japson Huang
     */
    public static boolean hasRole(String role) {
        return getUserInfo().getRoles().contains(role);
    }
}
