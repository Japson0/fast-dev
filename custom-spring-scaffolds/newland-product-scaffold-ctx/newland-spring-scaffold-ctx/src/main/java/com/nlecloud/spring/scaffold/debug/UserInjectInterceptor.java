package com.nlecloud.spring.scaffold.debug;

import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserWrapper;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <P><B>用户注入:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserInjectInterceptor  implements CustomInterceptor {


    private final UserInfo userInfo;

    public UserInjectInterceptor(UserInfo userInfo) {
        this.userInfo = userInfo;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        UserContext.setUserInfo(new UserWrapper(userInfo.getUserId(),userInfo.getUsername(),userInfo.getTenantId()));

        return CustomInterceptor.super.preHandle(request, response, handler);
    }
}
