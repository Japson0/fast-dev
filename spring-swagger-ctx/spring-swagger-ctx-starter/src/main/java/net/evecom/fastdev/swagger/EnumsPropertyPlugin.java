/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.swagger;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import net.evecom.fastdev.swagger.annotation.SwaggerDisplayEnum;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnumsPropertyPlugin implements ModelPropertyBuilderPlugin {
    @Override
    public void apply(ModelPropertyContext context) {
        Optional<BeanPropertyDefinition> beanPropertyDefinition = context.getBeanPropertyDefinition();
        if (!beanPropertyDefinition.isPresent()) return;
        final Class<?> rawPrimaryType = beanPropertyDefinition.get().getRawPrimaryType();
        //过滤得到目标类型
        SwaggerDisplayEnum annotation = AnnotationUtils.findAnnotation(rawPrimaryType, SwaggerDisplayEnum.class);
        if (annotation != null && Enum.class.isAssignableFrom(rawPrimaryType)) {
            addDescForEnum(context, rawPrimaryType, annotation);
        }
    }

    private void addDescForEnum(ModelPropertyContext context, Class<?> fieldType, SwaggerDisplayEnum annotation) {
        if (annotation != null) {
            String index = annotation.value();
            String name = annotation.name();

            Object[] enumConstants = fieldType.getEnumConstants();
            if (enumConstants.length == 0) return;
            List<String> displayValues =
                    Arrays.stream(enumConstants)
                            .filter(Objects::nonNull)
                            .map(item -> {
                                Class<?> currentClass = item.getClass();

                                Field indexField = ReflectionUtils.findField(currentClass, index);
                                ReflectionUtils.makeAccessible(indexField);
                                Object value = ReflectionUtils.getField(indexField, item);

                                Field descField = ReflectionUtils.findField(currentClass, name);
                                ReflectionUtils.makeAccessible(descField);
                                Object desc = ReflectionUtils.getField(descField, item);
                                return value + ":" + desc;
                            }).collect(Collectors.toList());
            ModelPropertyBuilder builder = context.getBuilder();
            Field descField = ReflectionUtils.findField(builder.getClass(), "description");
            ReflectionUtils.makeAccessible(descField);
            Object description = ReflectionUtils.getField(descField, builder);
            if (description == null) {
                description = "枚举值";
            } else {
                description += "。枚举值";
            }
//            Object enumConstant = enumConstants[0];
//            Field indexFile = ReflectionUtils.findField(enumConstant.getClass(), index);
            String joinText = description
                    + " (" + String.join("; ", displayValues) + ")";
            builder.description(joinText).type(context.getResolver().resolve(fieldType));
        }
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }
}
