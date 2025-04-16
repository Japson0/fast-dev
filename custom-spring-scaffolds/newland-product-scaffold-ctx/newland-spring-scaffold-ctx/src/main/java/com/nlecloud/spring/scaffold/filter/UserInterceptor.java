
package com.nlecloud.spring.scaffold.filter;

import com.necloud.spring.common.AuthConstants;
import com.necloud.spring.common.handle.UserProxy;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.necloud.spring.common.handle.UserWrapper;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <P><B>用户拦截器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月04日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class UserInterceptor implements CustomInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId = request.getHeader(AuthConstants.USER_ID_HEADER);
        String username = request.getHeader(AuthConstants.USER_HEADER);
        String roles = request.getHeader(AuthConstants.ROLE_HEADER);

        if(userId!=null && username!=null) {
            Set<String> rolesSet=Collections.EMPTY_SET;
            if(roles!=null) {
                String[] rolesSplit = roles.split(",");
                rolesSet = new HashSet<>(rolesSplit.length);
                for (String role : rolesSplit) {
                    rolesSet.add(role);
                }
            }
            UserContext.setUserInfo(new UserWrapper(Long.valueOf(userId),username,rolesSet));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.clean();
    }

}
