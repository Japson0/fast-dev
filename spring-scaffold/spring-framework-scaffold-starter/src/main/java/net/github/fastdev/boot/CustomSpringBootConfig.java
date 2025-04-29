package net.github.fastdev.boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.github.fastdev.boot.controller.CacheController;
import net.github.fastdev.boot.controller.EnumController;
import net.github.fastdev.boot.handle.*;
import net.github.fastdev.cache.redis.CacheHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <P><B>:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月23日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(CustomSpringBootProperies.class)
@ComponentScan(basePackages = {"cn.hutool.extra.spring"})
@AutoConfigureBefore({WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, MessageSourceAutoConfiguration.class})
public class CustomSpringBootConfig {

    /**
     * ym文件不支持DEFAULT_VIEW_INCLUSION属性配置，通过代码形式配置
     * 为true时候没有被JSONView修饰的属性也会默认输出，用于控制VO或DTO中属性的序列化
     * Revision Trail: (Date/Author/Description)
     * 2019/5/11 Timer He CREATE
     *
     * @author Timer He
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(Jackson2ObjectMapperBuilder.class)
    public ObjectMapper jacksonObjectMapper(ComEnumDisplayHandle comEnumDisplayHandle,Jackson2ObjectMapperBuilder builder, JacksonProperties jacksonProperties) {
        return new ObjectMapperBuilder(comEnumDisplayHandle,builder,jacksonProperties).builder();
    }


    @Bean
    @ConditionalOnMissingBean(ComEnumDisplayHandle.class)
    public ComEnumDisplayHandle comEnumDisplayHandle(){
        return e->e.getDisplay();
    }

    @Bean
    public GlobalExceptionHandle globalExceptionConfig(@Autowired(required = false) TraceService traceService) {
        return new GlobalExceptionHandle(traceService == null ? () -> null : traceService);
    }

    @Bean
    public WebMvcConfigurer mvcConfigurer(CustomSpringBootProperies customSpringBootProperies,
                                          WebTransSecurityServer webTransSecurityServer,
                                          List<ResourceClean> resourceCleans,
                                          List<CustomInterceptor> interceptors) {
        return new CustomSpringBootMvcSpringConfig(customSpringBootProperies, webTransSecurityServer, resourceCleans,interceptors);
    }

    @Bean
    public EnumController enumController(CustomSpringBootProperies customSpringBootProperies) {
        CustomSpringBootProperies.EnumsProperties enums = customSpringBootProperies.getEnums();
        String prefix = null;
        if (enums != null) {
            prefix = enums.getPrefixPackage();
        }
        return new EnumController(prefix);
    }

    @Bean(DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME)
    public CustomAcceptHeaderLocaleResolver customAcceptHeaderLocaleResolver() {
        return new CustomAcceptHeaderLocaleResolver();
    }

    @Bean
    @ConditionalOnBean(CacheHandle.class)
    public CacheController cacheController(CacheHandle cacheHandle) {
        return new CacheController(cacheHandle);
    }

    /**
     * 脱敏服务
     * RevisionTrail:(Date/Author/Description)
     * 2020年12月17日
     *
     * @author Japson Huang
     */
    @Bean
    @ConditionalOnMissingBean(WebTransSecurityServer.class)
    public DefaultWebTransSecurityService defaultDesensitizationService() {
        return new DefaultWebTransSecurityService();
    }


}
