package com.nlecloud.spring.scaffold;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.nlecloud.spring.scaffold.filter.UserInterceptor;
import com.nlecloud.spring.scaffold.handle.AutoMetaObjectHandle;
import com.nlecloud.spring.scaffold.handle.TenantHandle;
import com.nlecloud.spring.scaffold.service.DictServiceProxy;
import com.nlecloud.spring.scaffold.service.UserProxy;
import org.bouncycastle.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * <P><B>配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
@EnableConfigurationProperties(NewLandSpringProperty.class)
public class NewLandSpringConfig {


    @Autowired
    private NewLandSpringProperty property;

    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new AutoMetaObjectHandle();
    }

    @Bean
    public UserProxy userProxy(){
        return new UserProxy();
    }

    @Bean
    public DictServiceProxy dictServiceProxy(){
        return new DictServiceProxy();
    }


    @Bean
    public UserInterceptor userInterceptor(UserProxy userProxy){
        return new UserInterceptor(userProxy);
    }


    @Bean
    @ConditionalOnProperty(prefix = "nlecloud.product",name = "tenantEnabled",havingValue = "true",matchIfMissing = true)
    public TenantLineHandler tenantLineHandler(){
        Predicate<String> predicate;
        if(Arrays.isNullOrEmpty(property.getIgnoreTenantTable())){
            Set<String> objects = new HashSet<>(java.util.Arrays.asList(property.getIgnoreTenantTable()));
            predicate=f->objects.contains(f);
        }else{
            predicate=f->false;
        }
        return new TenantHandle(predicate);
    }
}
