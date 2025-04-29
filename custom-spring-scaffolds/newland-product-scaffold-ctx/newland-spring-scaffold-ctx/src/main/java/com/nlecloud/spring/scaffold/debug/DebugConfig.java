package com.nlecloud.spring.scaffold.debug;

import com.nlecloud.spring.scaffold.NewLandSpringProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <P><B>debug配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
public class DebugConfig {

    private NewLandSpringProperty newLandSpringProperty;


    @Bean
    @ConditionalOnProperty(prefix = "nlecloud.product.debug",name="logger",havingValue = "true" )
    public FullRequestLoggingInterceptor fullRequestLoggingInterceptor() {
        return new FullRequestLoggingInterceptor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "nlecloud.product.debug",name="injectUser",havingValue = "true" )
    public UserInjectInterceptor userInjectInterceptor() {
        return new UserInjectInterceptor(newLandSpringProperty.getDebug().getUserInfo());
    }

}
