package com.nlecloud.spring.scaffold.debug;

import com.nlecloud.spring.scaffold.NewLandSpringProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * <P><B>debug配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
@Conditional(DebugConfig.DebugCondition.class)
@Import(RequestWrapperFilter.class)
public class DebugConfig{


    private final   NewLandSpringProperty newLandSpringProperty;

    public DebugConfig(NewLandSpringProperty newLandSpringProperty) {
        this.newLandSpringProperty = newLandSpringProperty;
    }
    @Bean
    @ConditionalOnProperty(prefix = "nlecloud.product.debug",name="logger",havingValue = "true")
    public FilterRegistrationBean<RequestWrapperFilter> contentCachingFilter() {
        FilterRegistrationBean<RequestWrapperFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestWrapperFilter());
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE); // 确保过滤器最先执行
        return registrationBean;
    }

    @Bean
    @ConditionalOnProperty(prefix = "nlecloud.product.debug",name="logger",havingValue = "true")
    public FullRequestLoggingInterceptor fullRequestLoggingInterceptor() {
        return new FullRequestLoggingInterceptor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "nlecloud.product.debug",name="inject-user",havingValue = "true" )
    public UserInjectInterceptor userInjectInterceptor() {
        return new UserInjectInterceptor(newLandSpringProperty.getDebug().getUserInfo());
    }

    public static class DebugCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            String debug = context.getEnvironment().getProperty("nlecloud.product.debug.enable");
            return "true".equals(debug);
        }
    }

}
