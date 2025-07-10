package com.nlecloud.spring.scaffold.filter;

import com.nlecloud.spring.annotation.PreAuthorize;
import com.nlecloud.spring.scaffold.common.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.springframework.core.Ordered;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <P><B>权限判断:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class PermissionInterceptor  implements CustomInterceptor {

    private Map<String,Set<Method>> roleMethodMap = new LinkedHashMap<>();

    private Set<Method> initMethods = new LinkedHashSet<>();

    private Set<Method> ignorePermissionMethods = new LinkedHashSet<>();

    private ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod) {
            if(!containRole((HandlerMethod)handler)) {
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

    private boolean containRole(HandlerMethod handlerMethod){

        if(!initMethods.contains(handlerMethod.getMethod())){
            try {
                reentrantReadWriteLock.writeLock().lock();
                String[] annotation = getAnnotation(handlerMethod);
                if (annotation != null) {
                    for (String role : annotation) {
                        Set<Method> methods = roleMethodMap.computeIfAbsent(role, a -> new LinkedHashSet<>());
                        methods.add(handlerMethod.getMethod());
                    }
                } else {
                    ignorePermissionMethods.add(handlerMethod.getMethod());
                }
                initMethods.add(handlerMethod.getMethod());

            }finally {
                reentrantReadWriteLock.writeLock().unlock();
            }
        }
        try {
         //在忽略权限的方法中时，true
            //否则根据角色判断是否存在对应的角色
            reentrantReadWriteLock.readLock().lock();
            if (!ignorePermissionMethods.contains(handlerMethod.getMethod())) {
                for (String role : UserContext.getRoles()) {
                    if (roleMethodMap.get(role).contains(handlerMethod.getMethod())) {
                        return true;
                    }
                }
                return false;
            } else {
                return true;
            }
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }

    }

    private String[] getAnnotation(HandlerMethod handlerMethod) {
        PreAuthorize methodAnnotation = handlerMethod.getMethod().getAnnotation(PreAuthorize.class);
        if(methodAnnotation != null) {
            return methodAnnotation.value();
        }
        PreAuthorize beanAnnotation = handlerMethod.getBeanType().getAnnotation(PreAuthorize.class);
        if(beanAnnotation != null) {
            return beanAnnotation.value();
        }
        return null;
    }
}
