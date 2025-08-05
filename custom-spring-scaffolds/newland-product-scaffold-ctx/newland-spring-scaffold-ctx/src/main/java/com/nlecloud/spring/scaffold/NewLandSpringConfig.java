package com.nlecloud.spring.scaffold;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.nlecloud.spring.scaffold.api.user.IUPMSUserApi;
import com.nlecloud.spring.scaffold.common.UserProxy;
import com.nlecloud.spring.scaffold.debug.DebugConfig;
import com.nlecloud.spring.scaffold.filter.UserFeignInterceptor;
import com.nlecloud.spring.scaffold.filter.UserInterceptor;
import com.nlecloud.spring.scaffold.handle.*;
import com.nlecloud.spring.scaffold.service.DictServiceProxy;
import net.github.fastdev.boot.CustomSpringBootConfig;
import net.github.fastdev.boot.handle.ComEnumDisplayHandle;
import org.bouncycastle.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

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
@EnableFeignClients(clients = {IUPMSUserApi.class})
public class NewLandSpringConfig {


    @Autowired
    private NewLandSpringProperty property;

    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new AutoMetaObjectHandle();
    }


    /**
     *用户代理
     *RevisionTrail:(Date/Author/Description)
     * 2025年05月12日
     *@author Japson Huang
     *
    */
    @Bean
    public UserProxy userProxy(IUPMSUserApi iupmsUserApi){

        return new UserProxy(iupmsUserApi,null);
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

    @Bean
//    @ConditionalOnBean(FeignClientFactoryBean.class)
    public UserFeignInterceptor userFeignInterceptor(){
        return new UserFeignInterceptor();
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


    @Bean
    public InjectRobotAspectHandle injectRobotAspectHandle(){
        return new InjectRobotAspectHandle();
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

//    @Bean
//    public PermissionInterceptor permissionInterceptor(){
//        return new PermissionInterceptor();
//    }

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

    @Bean
    @Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
    public CustomAnnotationOperationPlugin customAnnotationOperationPlugin(){
        return new CustomAnnotationOperationPlugin();
    }

}
