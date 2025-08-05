package com.nlecloud.spring.scaffold.filter;

import com.nlecloud.spring.annotation.ApiGroup;
import com.nlecloud.spring.annotation.ApiName;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserProxy;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <P><B>权限判断:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class PermissionInterceptor  implements CustomInterceptor {


    private UserProxy userProxy;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 2. 获取方法所属的类（Controller 类）
            Class<?> controllerClass = handlerMethod.getBeanType();
            ApiName apiName = AnnotationUtils.findAnnotation(((HandlerMethod) handler).getMethod(), ApiName.class);
            if(apiName==null){
                return true;
            }
            ApiGroup apiGroup = AnnotationUtils.findAnnotation(controllerClass, ApiGroup.class);
            String name=apiGroup==null?controllerClass.getSimpleName():apiGroup.tag()+"_"+apiName.value();

            return userProxy.hasApiPermission(UserContext.getUserId(),name);
        }
        return true;
    }

    @Override
    public int order() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
