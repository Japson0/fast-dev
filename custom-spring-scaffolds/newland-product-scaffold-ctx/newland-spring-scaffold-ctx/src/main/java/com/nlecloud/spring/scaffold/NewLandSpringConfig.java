package com.nlecloud.spring.scaffold;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.nlecloud.spring.scaffold.debug.DebugConfig;
import com.nlecloud.spring.scaffold.filter.PermissionInterceptor;
import com.nlecloud.spring.scaffold.filter.UserInterceptor;
import com.nlecloud.spring.scaffold.handle.AutoMetaObjectHandle;
import com.nlecloud.spring.scaffold.handle.I18n4EnumHandle;
import com.nlecloud.spring.scaffold.handle.TenantHandle;
import com.nlecloud.spring.scaffold.handle.TraceServiceHandle;
import com.nlecloud.spring.scaffold.service.DictServiceProxy;
import net.github.fastdev.boot.CustomSpringBootConfig;
import net.github.fastdev.boot.handle.ComEnumDisplayHandle;
import org.bouncycastle.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
@AutoConfigureBefore({CustomSpringBootConfig.class})
@Import(DebugConfig.class)
public class NewLandSpringConfig {


    @Autowired
    private NewLandSpringProperty property;

    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new AutoMetaObjectHandle();
    }

    /**
     *枚举国际化
     *RevisionTrail:(Date/Author/Description)
     * 2025年04月29日
     *@author Japson Huang
     *
    */
    @Bean
    public ComEnumDisplayHandle i18n4EnumHandle(){
        return new I18n4EnumHandle();
    }

    /**
     *用户拦截器
     *RevisionTrail:(Date/Author/Description)
     * 2025年04月29日
     *@author Japson Huang
     *
    */
    @Bean
    public UserInterceptor userInterceptor(){
        return new UserInterceptor();
    }

    /**
     *字典转换服务
     *RevisionTrail:(Date/Author/Description)
     * 2025年04月29日
     *@author Japson Huang
     *
    */
    @Bean
    public DictServiceProxy dictServiceProxy(){
        return new DictServiceProxy();
    }


    /**
     *追溯ID
     *RevisionTrail:(Date/Author/Description)
     * 2025年04月29日
     *@author Japson Huang
     *
    */
    @Bean
    public TraceServiceHandle traceServiceHandle(){
        return new TraceServiceHandle();
    }

    @Bean
    public PermissionInterceptor permissionInterceptor(){
        return new PermissionInterceptor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "nlecloud.product",name = "tenantEnabled",havingValue = "true")
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
