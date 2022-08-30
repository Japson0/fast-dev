/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.ddp.filter;

import net.evecom.fastdev.ddp.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月04日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserInterceptor implements HandlerInterceptor {

    /**
     * jwt载荷
     */
    public static final String JWT_PAYLOAD = "jwt_payload";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String jwt = request.getHeader(JWT_PAYLOAD);
        UserContext.setUserInfo(jwt);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserContext.clean();
    }
}
