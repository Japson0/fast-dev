package com.nlecloud.spring.scaffold.debug;

import com.nlecloud.spring.scaffold.property.NewLandSpringProperty;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.springframework.core.Ordered;


/**
 * <P><B>用户注入:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserInjectInterceptor  implements CustomInterceptor {


    private final NewLandSpringProperty.DebugUserInfo userInfo;

    public UserInjectInterceptor(NewLandSpringProperty.DebugUserInfo userInfo) {
        this.userInfo = userInfo;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        UserContext.setUserInfo(new UserWrapper(userInfo.getUserId(),userInfo.getUsername(),userInfo.getTenantId(), userInfo.getSchoolId()));

        return CustomInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public int order() {
        return Ordered.HIGHEST_PRECEDENCE-1;
    }
}
