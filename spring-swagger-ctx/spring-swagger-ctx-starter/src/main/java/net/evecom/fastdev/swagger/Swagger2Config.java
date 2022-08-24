/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.swagger;


import com.github.xiaoymin.knife4j.core.extend.OpenApiExtendSetting;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtension;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiSettingExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * swagger2配置，访问地址  http://localhost:8080/doc.html
 *
 * @author Dante Zheng
 * @version 1.0
 * @created 2019/4/16 11:53:16
 */
@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(prefix = "evecom.swagger", name = "enable", havingValue = "true")
@EnableConfigurationProperties(SwaggerProperties.class)
public class Swagger2Config {

    /**
     * swagger配置
     */
    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .host(swaggerProperties.getHost())
                .extensions(extendSetting())
                .select()
                .apis(swaggerProperties.getBasePack() == null ? RequestHandlerSelectors.any() :
                        RequestHandlerSelectors.basePackage(swaggerProperties.getBasePack()))
                .paths((api) -> !PathSelectors.regex("/error.*").test(api))
                .paths(PathSelectors.regex("/.*"))
                .build()
                //将时间戳转换为字符串
                .directModelSubstitute(Timestamp.class, Long.class);
    }

    public List<VendorExtension> extendSetting() {
        OpenApiExtendSetting openApiExtendSetting = new OpenApiExtendSetting();
        openApiExtendSetting.setEnableFooter(false);
        openApiExtendSetting.setEnableFooterCustom(true);
        openApiExtendSetting.setFooterCustomContent("Swagger Apis | Copyright (c) EVECOM");
        OpenApiExtension openApiExtension = new OpenApiExtension("x-openapi");
        openApiExtension.addProperty(new OpenApiSettingExtension(openApiExtendSetting));
        List<VendorExtension> vendorExtensions = new ArrayList();
        vendorExtensions.add(openApiExtension);
        return vendorExtensions;
    }

    /**
     * 构建 api文档的详细信息函数
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title(swaggerProperties.getTitle())
                //创建人
                .contact(swaggerProperties.getContact())
                //版本号
                .version(swaggerProperties.getVersion())                //描述
                .description(swaggerProperties.getDescription())
                .build();
    }

    @Bean
    @ConditionalOnClass(name = "com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition")
    public EnumsPropertyPlugin enumsPropertyPlugin() {
        return new EnumsPropertyPlugin();
    }
}
