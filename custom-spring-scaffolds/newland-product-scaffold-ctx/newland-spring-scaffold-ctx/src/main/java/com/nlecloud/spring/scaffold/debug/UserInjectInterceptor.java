package com.nlecloud.spring.scaffold.debug;

import com.nlecloud.spring.scaffold.property.NewLandSpringProperty;
import com.nlecloud.spring.scaffold.common.UserWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/**
 * <P><B>用户注入:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Order(-100)
public class UserInjectInterceptor extends OncePerRequestFilter {


    private final NewLandSpringProperty.DebugUserInfo userInfo;

    public UserInjectInterceptor(NewLandSpringProperty.DebugUserInfo userInfo) {
        this.userInfo = userInfo;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        request.setAttribute(
                com.nlecloud.spring.scaffold.filter.UserInterceptor.DEBUG_USER_ATTRIBUTE,
                new UserWrapper(userInfo.getUserId(), userInfo.getUsername(),
                        userInfo.getTenantId(), userInfo.getSchoolId()));
        filterChain.doFilter(request, response);
    }
}
