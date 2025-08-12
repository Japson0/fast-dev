package com.nlecloud.spring.scaffold.filter;

import cn.hutool.core.io.IoUtil;
import com.nlecloud.spring.annotation.ApiGroup;
import com.nlecloud.spring.annotation.ApiName;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserProxy;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <P><B>权限判断:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class PermissionInterceptor  implements CustomInterceptor {

    private final UserProxy userProxy;

    @Value("spring.application.name")
    private String applicationName;

    public PermissionInterceptor(UserProxy userProxy) {
        this.userProxy = userProxy;
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

            if(userProxy.hasApiPermission(UserContext.getRoles(), applicationName+"_"+name)){
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
