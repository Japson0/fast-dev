/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.ddp;

import cn.hutool.core.lang.Assert;
import net.evecom.fastdev.boot.utils.JacksonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
    private final static ThreadLocal<UserInfo> USER_INFO_LOCAL = new ThreadLocal<>();

    /**
     * 无用户信息错误
     */
    private static final IllegalAccessError ILLEGAL_ACCESS_ERROR = new IllegalAccessError("该接口无用户信息");


    public static void setUserInfo(Long account, Long tenantId) {
        UserInfo userInfo = new UserInfo(account, tenantId);
        USER_INFO_LOCAL.set(userInfo);
    }


    public static void clean() {
        USER_INFO_LOCAL.remove();
    }

    public static Long getUserId() {
        UserInfo userInfo = USER_INFO_LOCAL.get();
        return userInfo != null ? userInfo.getUserId() : null;
    }

    public static Long getTenantId() {
        UserInfo userInfo = USER_INFO_LOCAL.get();
        return userInfo != null ? userInfo.getTenantId() : null;
    }

    public static String getUserName() {
        return getUserInfo().getUsername();
    }

    public static UserInfo getUserInfo() {
        UserInfo userInfo = USER_INFO_LOCAL.get();
        if (userInfo != null && !userInfo.isInit()) {
            initUserInfo(userInfo);
        }
        Assert.notNull(userInfo, "---------------用户信息为空--------------");
        return userInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        USER_INFO_LOCAL.set(userInfo);
    }


    private static void initUserInfo(UserInfo userInfo) {
        if (userInfo.getUserId() == null) {
            throw ILLEGAL_ACCESS_ERROR;
        }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            String jwt = requestAttributes.getRequest().getHeader("jwt_payload");
            if (StringUtils.isEmpty(jwt)) {
                throw ILLEGAL_ACCESS_ERROR;
            }
            USER_INFO_LOCAL.get().init(JacksonUtils.toBean(jwt, JwtClaims.class));
        }
    }
}
