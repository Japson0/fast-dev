package com.nlecloud.spring.scaffold;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.nlecloud.spring.annotation.api.UserInfoService;
import com.nlecloud.spring.scaffold.api.user.IUPMSUserApi;
import com.nlecloud.spring.scaffold.common.UserProxy;
import com.nlecloud.spring.scaffold.debug.DebugConfig;
import com.nlecloud.spring.scaffold.filter.PermissionInterceptor;
import com.nlecloud.spring.scaffold.filter.UserInterceptor;
import com.nlecloud.spring.scaffold.handle.*;
import com.nlecloud.spring.scaffold.service.DictServiceProxy;
import net.github.fastdev.boot.CustomSpringBootConfig;
import net.github.fastdev.boot.handle.ComEnumDisplayHandle;
import org.bouncycastle.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
@Import({DebugConfig.class,WebClientConfig.class})
public class NewLandSpringConfig {


    @Autowired
    private NewLandSpringProperty property;

    @Bean
    @ConditionalOnMissingBean(MetaObjectHandler.class)
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
    public UserProxy userProxy(@Lazy IUPMSUserApi iupmsUserApi, @Autowired(required = false)UserInfoService userInfoService, RedisTemplate<String,String> redisTemplate){
        UserInfoService finalService = userInfoService != null
                ? userInfoService
                : (id) -> iupmsUserApi.getUserDetailById(id);
        return new UserProxy(finalService,redisTemplate);
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


    @Bean
    @ConditionalOnProperty(prefix = "nlecloud.product.table-config",name = "tenant-enabled",havingValue = "true")
    public TenantLineHandler tenantLineHandler(){
        Predicate<String> predicate;
        if(Arrays.isNullOrEmpty(property.getTableConfig().getIgnoreTenantTable())){
            Set<String> tables = java.util.Arrays.stream(property.getTableConfig().getIgnoreTenantTable()).map(String::toUpperCase).collect(Collectors.toSet());
            predicate=f->tables.contains(f.toUpperCase());
        }else{
            predicate=f->false;
        }
        return new TenantHandle(predicate,property.getTableConfig().getTenantColumnName());
    }


    /**
     *数据权限注入
     *RevisionTrail:(Date/Author/Description)
     * 2026年05月29日
     *@author Japson Huang
     *
    */
    @Bean
    @ConditionalOnProperty(prefix = "nlecloud.product.table-config",name = "data-permission-enabled",havingValue = "true")
    public InnerInterceptor OrgPermissionDataInterceptor(){
        Predicate<String> predicate;
        if(Arrays.isNullOrEmpty(property.getTableConfig().getIgnoreDataPermissionTable())){
            Set<String> tables = java.util.Arrays.stream(property.getTableConfig().getIgnoreTenantTable()).map(String::toUpperCase).collect(Collectors.toSet());
            predicate=f->tables.contains(f.toUpperCase());
        }else{
            predicate=f->false;
        }
        OrgPermissionDataHandle orgPermissionDataHandle = new OrgPermissionDataHandle(predicate,property.getTableConfig().getOrgColumnName());
        return new DataPermissionInterceptor(orgPermissionDataHandle);
    }

    @Bean
    public CustomApiPermissionPlugin customAnnotationOperationPlugin(){
        return new CustomApiPermissionPlugin();
    }


    @Bean
    @ConditionalOnMissingBean(PermissionHandle.class)
    @ConditionalOnProperty(prefix = "nlecloud.product" ,name = "api-permission-enabled" ,havingValue = "true")
    public PermissionHandle permissionHandle(){
        return new DefaultPermissionHandle();
    }

    @Bean
    @ConditionalOnProperty(prefix = "nlecloud.product" ,name = "api-permission-enabled" ,havingValue = "true")
    @ConditionalOnMissingBean(PermissionInterceptor.class)
    public PermissionInterceptor permissionInterceptor(@Value("${spring.application.name}") String applicationName,PermissionHandle permissionHandle){
        return new PermissionInterceptor(applicationName,permissionHandle);
    }




}
