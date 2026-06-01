package com.nlecloud.spring.webflux.scaffold;

import com.nlecloud.spring.webflux.scaffold.filter.UserFilter;
import com.nlecloud.spring.webflux.scaffold.user.UserInfoService;
import com.nlecloud.spring.webflux.scaffold.user.UserProxy;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.i18n.LocaleContextResolver;

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
@AutoConfigureBefore(WebFluxAutoConfiguration.class)
public class NewLandWebFluxConfig {

    @Bean
    public UserFilter userFilter(){
        return new UserFilter();
    }

    @Bean
    public GlobalExceptionHandle globalExceptionHandle(){
        return new GlobalExceptionHandle();
    }

    @Bean
    @ConditionalOnMissingBean(UserInfoService.class)
    public UserInfoService userInfoService(WebClient.Builder webClientBuilder){
        return new UserInfoService(webClientBuilder);
    }

    @Bean
    public UserProxy userProxy(UserInfoService userInfoService, ReactiveRedisTemplate<String, Object> redisTemplate){
        return new UserProxy(userInfoService, redisTemplate);
    }

    /**
     * i18n配置
     */
    @Bean
    public LocaleContextResolver localeContextResolver() {
        return new CustomAcceptHeaderLocaleResolver();
    }


    @Bean
    @ConditionalOnBean(ReactiveRedisConnectionFactory.class)
    public RedisConfig redisConfig(){
        return new RedisConfig();
    }
}
