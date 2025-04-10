
package com.nlecloud.spring.scaffold.filter;

import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserWrapper;
import com.nlecloud.spring.scaffold.service.UserProxy;
import net.github.fastdev.boot.handle.CustomInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <P><B>用户拦截器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月04日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class UserInterceptor implements CustomInterceptor {

    private final UserProxy userProxy;


    public UserInterceptor(UserProxy userProxy) {
        this.userProxy = userProxy;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId = request.getHeader("x-userid-header");
        String username = request.getHeader("x-user-header");
        if(userId!=null && username!=null) {
            UserContext.setUserInfo(new UserWrapper(Long.valueOf(userId), username));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.clean();
    }

}
