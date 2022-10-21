package net.evecom.fastdev.ddp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.evecom.fastdev.boot.handle.TraceService;
import net.evecom.fastdev.ddp.filter.UserInterceptor;
import net.evecom.fastdev.ddp.filter.debug.DebugUserInterceptor;
import net.evecom.fastdev.ddp.handle.AutoMetaObjectHandle;
import net.evecom.fastdev.ddp.handle.DataDevTenantHandler;
import net.evecom.fastdev.ddp.handle.LoggerTracesService;
import org.apache.dubbo.config.Constants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月24日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
@EnableConfigurationProperties({DataDevProductProperties.class, DubboProperties.class})
public class DataDevProductConfig implements WebMvcConfigurer {

    /**
     * 配置属性
     */
    private final DataDevProductProperties devProductProperties;

    private final DubboProperties dubboProperties;

    public DataDevProductConfig(DataDevProductProperties superviseProperties, DubboProperties dubboProperties) {
        this.devProductProperties = superviseProperties;
        this.dubboProperties = dubboProperties;
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
    public TenantLineHandler tenantLineHandler() {
        return new DataDevTenantHandler(devProductProperties.getIgnoreTenantTable());
    }

    @ConditionalOnClass(name = "org.apache.skywalking.apm.agent.core.boot.AgentPackagePath")
    @Bean
    public TraceService traceService() {
        return new LoggerTracesService();
    }


    @PostConstruct
    public void initDubbo() {

        if (dubboProperties.getIp() != null) {
            System.setProperty(Constants.DUBBO_IP_TO_REGISTRY, dubboProperties.getIp());
        }
        if (dubboProperties.getPort() != null) {
            System.setProperty(Constants.DUBBO_PORT_TO_REGISTRY, String.valueOf(dubboProperties.getPort()));
        }
    }
}
