package com.nlecloud.spring.webflux.scaffold;

import com.nlecloud.spring.webflux.scaffold.filter.UserFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <P><B>配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月16日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */

@Configuration
@ComponentScan(basePackages = {"cn.hutool.extra.spring"})
public class NewLandWebFluxConfig {

    @Bean
    public UserFilter userFilter(){
        return new UserFilter();
    }

    @Bean
    public GlobalExceptionHandle globalExceptionHandle(){
        return new GlobalExceptionHandle();
    }
}
