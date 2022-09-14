/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.ddp;

import cn.hutool.core.lang.Assert;
import cn.hutool.extra.spring.SpringUtil;
import net.evecom.fastdev.boot.utils.JacksonUtils;
import net.evecom.fastdev.common.exception.NoUserInfoException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

/**
 * <P><B>用户工具类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年10月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
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
    private final static RedisTemplate<String, UserResource> REDIS_TEMPLATE;

    private UserContext() {
    }

    static {
        DataDevProductProperties properties = null;
        try {
            properties = SpringUtil.getBean(DataDevProductProperties.class);
        } catch (Exception ignore) {
        }
        if (properties != null && properties.getDebug().isEnable()) {
            DataDevProductProperties finalProperties = properties;
            USER_INFO_LOCAL = ThreadLocal.withInitial(() -> finalProperties.getDebug().getUser());
        } else {
            USER_INFO_LOCAL = new ThreadLocal<>();
        }
        RedisTemplate<String, UserResource> temp;
        try {
            temp = SpringUtil.getBean(RedisTemplate.class);
        } catch (Exception ignore) {
            temp = null;
        }
        REDIS_TEMPLATE = temp;
    }

    public static void setUserInfo(String jwtClaims) {
        if (jwtClaims != null) {
            UserInfo userInfo = new UserInfo(Objects.requireNonNull(JacksonUtils.toBean(jwtClaims, JwtClaims.class)));
            USER_INFO_LOCAL.set(userInfo);
        }
    }


    public static void clean() {
        USER_INFO_LOCAL.remove();
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
        UserResource userResource = REDIS_TEMPLATE.opsForValue().get(userInfo.getClientId() + "_" + userInfo.getUserId());
        if (userResource == null) {
            throw NoUserInfoException.getInstance();
        }
        USER_INFO_LOCAL.get().init(userResource);
    }
}
