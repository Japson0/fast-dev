package com.nlecloud.spring.scaffold.handle;


import com.nlecloud.spring.annotation.ApiGroup;
import com.nlecloud.spring.annotation.ApiName;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.Collections;
import java.util.Optional;


public class CustomApiPermissionPlugin implements OperationBuilderPlugin {

    @Override
    public void apply(OperationContext context) {
        Optional<ApiName> apiName = context.findAnnotation(ApiName.class);
        if (apiName.isPresent()) {
            Optional<ApiGroup> controllerAnnotation = context.findControllerAnnotation(ApiGroup.class);
            String apiNameStr = controllerAnnotation.isPresent() ? controllerAnnotation.get().tag() + "_" + apiName.get().value() : apiName.get().value();
            context.operationBuilder()
                    .notes("api接口权限名称为:" + apiNameStr)
                    .extensions(Collections.singletonList(new StringVendorExtension("apiName",
                            apiNameStr)));

        }

        // 获取方法
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}