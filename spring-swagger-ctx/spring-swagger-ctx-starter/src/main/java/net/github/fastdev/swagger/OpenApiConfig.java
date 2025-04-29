//package net.github.fastdev.swagger;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import org.springdoc.core.GroupedOpenApi;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//
///**
// * OpenAPI 3 configuration, access address: http://localhost:8080/swagger-ui.html
// * or http://localhost:8080/doc.html (if knife4j is enabled)
// *
// * @author Dante Zheng
// * @version 1.0
// * @created 2019/4/16 11:53:16
// */
//@Configuration
//@ConditionalOnProperty(prefix = "custom.swagger", name = "enable", havingValue = "true", matchIfMissing = true)
//@EnableConfigurationProperties(SwaggerProperties.class)
//public class OpenApiConfig {
//
//    @Autowired
//    private SwaggerProperties swaggerProperties;
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        OpenAPI openAPI = new OpenAPI();
//        openAPI
//                .info(apiInfo())
//                // Add any global components or security schemes here
//                .addExtension("x-openapi", new Knife4jExtendSetting());
//        return openAPI;
//    }
//
//    private Info apiInfo() {
//        Info version = new Info()
//                .title(swaggerProperties.getTitle())
//                .description(swaggerProperties.getDescription())
//                .version(swaggerProperties.getVersion());
//        if(swaggerProperties.getContact()!=null){
//           version .contact(new Contact()
//                    .name(swaggerProperties.getContact().getName())
//                    .url(swaggerProperties.getContact().getUrl())
//                    .email(swaggerProperties.getContact().getEmail()));
//        }
//        return version;
//
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public GroupedOpenApi defaultApi(ApplicationContext applicationContext) {
//        // 获取主应用类
//        Class<?> mainApplicationClass = deduceMainApplicationClass(applicationContext);
//
//        return GroupedOpenApi.builder()
//                .group("default")
//                .packagesToScan(getActualBasePack(mainApplicationClass))
//                .pathsToMatch("/**")
//                .build();
//    }
//
//    public String getActualBasePack(Class<?> mainApplicationClass) {
//        return StringUtils.hasText(this.swaggerProperties.getBasePack())
//                ? this.swaggerProperties.getBasePack()
//                : mainApplicationClass.getPackage().getName();
//    }
//
//    private Class<?> deduceMainApplicationClass(ApplicationContext applicationContext) {
//        try {
//            StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
//            for (StackTraceElement stackTraceElement : stackTrace) {
//                if ("main".equals(stackTraceElement.getMethodName())) {
//                    return Class.forName(stackTraceElement.getClassName());
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            // 忽略异常，返回默认值
//        }
//        return OpenApiConfig.class; // 默认返回配置类所在的包
//    }
//
//    @Bean
//    @ConditionalOnClass(name = "com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition")
//    public EnumsPropertyCustomizer enumsPropertyCustomizer() {
//        return new EnumsPropertyCustomizer();
//    }
//
//    // Knife4j extension settings
//    private static class Knife4jExtendSetting {
//        private boolean enableFooter = false;
//        private boolean enableFooterCustom = true;
//
//        // Getters and setters
//        public boolean isEnableFooter() {
//            return enableFooter;
//        }
//
//        public void setEnableFooter(boolean enableFooter) {
//            this.enableFooter = enableFooter;
//        }
//
//        public boolean isEnableFooterCustom() {
//            return enableFooterCustom;
//        }
//
//        public void setEnableFooterCustom(boolean enableFooterCustom) {
//            this.enableFooterCustom = enableFooterCustom;
//        }
//    }
//}