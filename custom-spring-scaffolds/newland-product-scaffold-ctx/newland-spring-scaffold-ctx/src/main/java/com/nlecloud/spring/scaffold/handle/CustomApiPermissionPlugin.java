package com.nlecloud.spring.scaffold.handle;


import com.nlecloud.spring.annotation.ApiGroup;
import com.nlecloud.spring.annotation.ApiName;
import io.swagger.v3.oas.models.Operation;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.web.method.HandlerMethod;


public class CustomApiPermissionPlugin implements OperationCustomizer {

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        // 获取方法上的 @ApiName 注解
        ApiName apiName = handlerMethod.getMethodAnnotation(ApiName.class);
        if (apiName != null) {
            // 获取类上的 @ApiGroup 注解
            ApiGroup apiGroup = handlerMethod.getBeanType().getAnnotation(ApiGroup.class);
            String apiNameStr = apiGroup != null ? apiGroup.tag() + "_" + apiName.value() : apiName.value();

            // 设置 description（相当于 Springfox 的 notes）
            String existingDesc = operation.getDescription();
            operation.setDescription((existingDesc != null ? existingDesc + "\n" : "")
                    + "api接口权限名称为:" + apiNameStr);

            // 添加自定义扩展属性 x-apiName
            operation.addExtension("x-apiName", apiNameStr);
        }
        return operation;
    }
}