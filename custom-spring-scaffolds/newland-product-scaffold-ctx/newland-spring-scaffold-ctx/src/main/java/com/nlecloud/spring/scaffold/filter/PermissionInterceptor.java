package com.nlecloud.spring.scaffold.filter;

import com.nlecloud.spring.annotation.ApiGroup;
import com.nlecloud.spring.annotation.ApiName;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.handle.PermissionHandle;
import com.nlecloud.upms.api.permission.PermissionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;

import java.util.Collection;

/**
 * <P><B>权限判断:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class PermissionInterceptor  implements CustomInterceptor {

    private String applicationName;

    private PermissionHandle permissionHandle;

    public PermissionInterceptor(String applicationName, PermissionHandle permissionHandle) {
        this.applicationName = applicationName;
        this.permissionHandle=permissionHandle;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            if(!UserContext.hasUser()) return true;
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 2. 获取方法所属的类（Controller 类）
            Class<?> controllerClass = handlerMethod.getBeanType();
            ApiName apiName = AnnotationUtils.findAnnotation(((HandlerMethod) handler).getMethod(), ApiName.class);
            if(apiName==null){
                return true;
            }
            ApiGroup apiGroup = AnnotationUtils.findAnnotation(controllerClass, ApiGroup.class);
            String name=apiGroup!=null?apiGroup.value()+"_"+apiName.value():apiName.value();
                if(permissionHandle.checkPermissions(UserContext.getRoles(), applicationName+"_"+name)){
                return true;
            }
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }

    @Override
    public int order() {
        return Ordered.LOWEST_PRECEDENCE;
    }



}
