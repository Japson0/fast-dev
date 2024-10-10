/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot;

import net.evecom.fastdev.boot.filter.WebTransSecurityInterceptor;
import net.evecom.fastdev.boot.handle.CustomInterceptor;
import net.evecom.fastdev.boot.handle.ResourceClean;
import net.evecom.fastdev.boot.handle.ResourceCleanInterceptor;
import net.evecom.fastdev.boot.handle.WebTransSecurityServer;
import net.evecom.fastdev.boot.serio.EnumConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <P><B>MVC配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月16日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class EvecomSpringBootMvcSpringConfig implements WebMvcConfigurer {

    private final EvecomSpringBootProperies evecomSpringBootProperies;

    private final WebTransSecurityServer webTransSecurityServer;

    private final List<ResourceClean> resourceCleans;

    private final List<CustomInterceptor> customInterceptors;

    public EvecomSpringBootMvcSpringConfig(EvecomSpringBootProperies evecomSpringBootProperies,
                                           WebTransSecurityServer webTransSecurityServer,
                                           List<ResourceClean> resourceCleans, List<CustomInterceptor> customInterceptors) {
        this.evecomSpringBootProperies = evecomSpringBootProperies;
        this.webTransSecurityServer = webTransSecurityServer;
        this.resourceCleans = resourceCleans;
        this.customInterceptors = customInterceptors;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        EvecomSpringBootProperies.WebTransSecurityFilter webTransSecurityFilter = evecomSpringBootProperies.getWebTransSecurityFilter();
        if (webTransSecurityFilter != null) {
            if (webTransSecurityFilter.isEnable()) {
                registry.addInterceptor(new WebTransSecurityInterceptor(webTransSecurityServer)).addPathPatterns(webTransSecurityFilter.getPath());
            }
        }
        if(!CollectionUtils.isEmpty(customInterceptors)){
           customInterceptors.forEach(registry::addInterceptor);
        }
        if (!CollectionUtils.isEmpty(resourceCleans)) {
            registry.addInterceptor(new ResourceCleanInterceptor(resourceCleans)).addPathPatterns("/**");
        }
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new EnumConverterFactory());
    }

}
