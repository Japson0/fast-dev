package net.evecom.fastdev.boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.evecom.fastdev.boot.controller.CacheController;
import net.evecom.fastdev.boot.controller.EnumController;
import net.evecom.fastdev.boot.handle.*;
import net.evecom.fastdev.cache.redis.CacheHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
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
@EnableConfigurationProperties(EvecomSpringBootProperies.class)
@ComponentScan(basePackages = {"cn.hutool.extra.spring"})
public class EvecomSpringBootConfig {

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
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder, JacksonProperties jacksonProperties) {
        return ObjectMapperBuilder.builder(builder, jacksonProperties);
    }

    @Bean
    public GlobalExceptionHandle globalExceptionConfig(@Autowired(required = false) TraceService traceService) {
        return new GlobalExceptionHandle(traceService == null ? () -> null : traceService);
    }

    @Bean
    public WebMvcConfigurer mvcConfigurer(EvecomSpringBootProperies evecomSpringBootProperies,
                                          WebTransSecurityServer webTransSecurityServer,
                                          List<ResourceClean> resourceCleans) {
        return new EvecomSpringBootMvcSpringConfig(evecomSpringBootProperies, webTransSecurityServer, resourceCleans);
    }

    @Bean
    public EnumController enumController(EvecomSpringBootProperies evecomSpringBootProperies) {
        EvecomSpringBootProperies.EnumsProperties enums = evecomSpringBootProperies.getEnums();
        String prefix = null;
        if (enums != null) {
            prefix = enums.getPrefixPackage();
        }
        return new EnumController(prefix);
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
