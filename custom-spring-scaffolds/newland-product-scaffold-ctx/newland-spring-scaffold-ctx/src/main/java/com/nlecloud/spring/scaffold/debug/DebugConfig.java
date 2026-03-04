package com.nlecloud.spring.scaffold.debug;

import com.nlecloud.spring.scaffold.NewLandSpringProperty;
import feign.Client;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;
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
public class DebugConfig{


    private final   NewLandSpringProperty newLandSpringProperty;

    public DebugConfig(NewLandSpringProperty newLandSpringProperty) {
        this.newLandSpringProperty = newLandSpringProperty;
    }

    @Bean
    @ConditionalOnProperty(prefix = "nlecloud.product.debug",name="inject-user",havingValue = "true" )
    public UserInjectInterceptor userInjectInterceptor() {
        return new UserInjectInterceptor(newLandSpringProperty.getDebug().getUserInfo());
    }

    @Bean
    @ConditionalOnProperty(prefix ="nlecloud.product.debug", name = "forward-addr")
    public Client feignClient() {
        return new Client.Default(null, null);  // 直接 HTTP 调用，不走 LB
    }

    public static class DebugCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            String debug = context.getEnvironment().getProperty("nlecloud.product.debug.enable");
            return "true".equals(debug);
        }
    }

}
