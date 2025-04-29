package com.nlecloud.spring.scaffold.debug;

import com.necloud.spring.common.handle.UserWrapper;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.scaffold.common.UserContext;
import net.github.fastdev.boot.handle.CustomInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

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

        UserContext.setUserInfo(new UserWrapper(userInfo.getUserId(),userInfo.getUsername(),userInfo.getTenantId(),userInfo.getRoles()));

        return CustomInterceptor.super.preHandle(request, response, handler);
    }
}
