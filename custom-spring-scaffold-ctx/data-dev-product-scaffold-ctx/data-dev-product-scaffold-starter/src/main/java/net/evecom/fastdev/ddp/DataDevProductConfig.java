package net.evecom.fastdev.ddp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.evecom.fastdev.boot.handle.TraceService;
import net.evecom.fastdev.ddp.filter.UserInterceptor;
import net.evecom.fastdev.ddp.filter.debug.DebugUserInterceptor;
import net.evecom.fastdev.ddp.handle.AutoMetaObjectHandle;
import net.evecom.fastdev.ddp.handle.DataDevTenantHandler;
import net.evecom.fastdev.ddp.handle.LoggerTracesService;
import net.evecom.fastdev.ddp.handle.ModuleBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <P><B>研发二部配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月24日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(DataDevProductProperties.class)
public class DataDevProductConfig implements WebMvcConfigurer {

    /**
     * 配置属性
     */
    private final DataDevProductProperties devProductProperties;


    public DataDevProductConfig(DataDevProductProperties superviseProperties) {
        this.devProductProperties = superviseProperties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        boolean debugger = false;
        if (devProductProperties.getDebug() != null) {
            DataDevProductProperties.Debug debug = devProductProperties.getDebug();
            if (debug.isEnable()) {
                registry.addInterceptor(new DebugUserInterceptor(debug.getUser())).addPathPatterns("/**");
                debugger = true;
            }
        }
        if (!debugger) {
            registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**");
        }
    }

    @Bean
    @ConditionalOnMissingBean(MetaObjectHandler.class)
    public MetaObjectHandler metaObjectHandler() {
        return new AutoMetaObjectHandle();
    }


    @Bean
    @ConditionalOnProperty(prefix = "evecom.product",name = "enable-tenant",havingValue = "true",matchIfMissing = true)
    public TenantLineHandler tenantLineHandler() {
        return new DataDevTenantHandler(devProductProperties.getIgnoreTenantTable());
    }

    @ConditionalOnClass(name = "org.apache.skywalking.apm.agent.core.boot.AgentPackagePath")
    @Bean
    public TraceService traceService() {
        return new LoggerTracesService();
    }

    /**
     * 模块注入处理类
     * RevisionTrail:(Date/Author/Description)
     * 2023年07月03日
     *
     * @author Japson Huang
     */
    @ConditionalOnProperty(prefix = "evecom.product", name = "enable-module", havingValue = "true")
    @Bean
    public ModuleBeanPostProcessor moduleBeanPostProcessor(ApplicationContext applicationContext) {
        return new ModuleBeanPostProcessor(applicationContext);
    }
}
