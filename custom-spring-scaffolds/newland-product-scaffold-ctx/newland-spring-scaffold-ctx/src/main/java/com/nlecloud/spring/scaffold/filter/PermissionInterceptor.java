package com.nlecloud.spring.scaffold.filter;

import com.nlecloud.spring.annotation.ApiName;
import com.nlecloud.spring.scaffold.common.UserContext;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        HandlerExecutionChain chain = requestMappingHandlerMapping.getHandler(request);
        if (chain == null) return true;

        Object currentHandler = chain.getHandler();
        if (!(currentHandler instanceof ServletInvocableHandlerMethod)) return true;

        ServletInvocableHandlerMethod method = (ServletInvocableHandlerMethod) currentHandler;

        // 获取 RequestMappingInfo（Spring 内部保存的映射信息）
        RequestMappingInfo info = requestMappingHandlerMapping.getMappingForMethod(method.getMethod(), method.getBean().getClass());

        if (info != null) {
            info.getPatternsCondition().getPatterns().forEach(pattern -> {
                System.out.println("Controller 中定义的 URL 模式: " + pattern);
            });
        }


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
        ApiName methodAnnotation = handlerMethod.getMethod().getAnnotation(ApiName.class);
        if(methodAnnotation != null) {
            return methodAnnotation.value();
        }
        ApiName beanAnnotation = handlerMethod.getBeanType().getAnnotation(ApiName.class);
        if(beanAnnotation != null) {
            return beanAnnotation.value();
        }
        return null;
    }
}
