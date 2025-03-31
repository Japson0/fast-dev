/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.github.fastdev.boot;

import net.github.fastdev.boot.filter.WebTransSecurityInterceptor;
import net.github.fastdev.boot.handle.CustomInterceptor;
import net.github.fastdev.boot.handle.ResourceClean;
import net.github.fastdev.boot.handle.ResourceCleanInterceptor;
import net.github.fastdev.boot.handle.WebTransSecurityServer;
import net.github.fastdev.boot.serio.EnumConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
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
public class CustomSpringBootMvcSpringConfig implements WebMvcConfigurer {

    private final CustomSpringBootProperies customSpringBootProperies;

    private final WebTransSecurityServer webTransSecurityServer;

    private final List<ResourceClean> resourceCleans;

    private final List<CustomInterceptor> customInterceptors;

    public CustomSpringBootMvcSpringConfig(CustomSpringBootProperies customSpringBootProperies,
                                           WebTransSecurityServer webTransSecurityServer,
                                           List<ResourceClean> resourceCleans, List<CustomInterceptor> customInterceptors) {
        this.customSpringBootProperies = customSpringBootProperies;
        this.webTransSecurityServer = webTransSecurityServer;
        this.resourceCleans = resourceCleans;
        this.customInterceptors = customInterceptors;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        CustomSpringBootProperies.WebTransSecurityFilter webTransSecurityFilter = customSpringBootProperies.getWebTransSecurityFilter();
        if (webTransSecurityFilter != null) {
            if (webTransSecurityFilter.isEnable()) {
                registry.addInterceptor(new WebTransSecurityInterceptor(webTransSecurityServer)).addPathPatterns(webTransSecurityFilter.getPath());
            }
        }
        if(!CollectionUtils.isEmpty(customInterceptors)){
            for (CustomInterceptor customInterceptor : customInterceptors) {
                InterceptorRegistration interceptorRegistration = registry.addInterceptor(customInterceptor);
                if(!CollectionUtils.isEmpty(customInterceptor.excludePathPatterns())){
                    interceptorRegistration.excludePathPatterns(customInterceptor.excludePathPatterns());
                }
            }
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
