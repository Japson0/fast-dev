package com.nlecloud.spring.scaffold.filter;

import com.nlecloud.spring.scaffold.annotation.PreAuthorize;
import com.nlecloud.spring.scaffold.common.UserContext;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.springframework.core.Ordered;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class PermissionInterceptor implements CustomInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod) {
            PreAuthorize annotation = ((HandlerMethod) handler).getMethod().getAnnotation(PreAuthorize.class);
            if(annotation != null) {
                String[] value = annotation.value();
                Set<String> roles = UserContext.getUserInfo().getRoles();
                for (String role : value) {
                    if(roles.contains(role)){
                        return true;
                    }
                }
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }
        return true;
    }

    @Override
    public int order() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
