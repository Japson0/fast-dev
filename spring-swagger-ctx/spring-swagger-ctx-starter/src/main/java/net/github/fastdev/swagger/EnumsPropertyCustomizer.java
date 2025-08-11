package net.github.fastdev.swagger;

import com.fasterxml.jackson.databind.JavaType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;
import net.github.fastdev.swagger.annotation.SwaggerDisplayEnum;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EnumsPropertyCustomizer implements ModelConverter {

    @Override
    public Schema resolve(AnnotatedType annotatedType, ModelConverterContext context, Iterator<ModelConverter> chain) {
        JavaType type = Json.mapper().constructType(annotatedType.getType());
        if (type == null || !type.isEnumType()) {
            return chain.hasNext() ? chain.next().resolve(annotatedType, context, chain) : null;
        }

        Class<?> rawClass = type.getRawClass();
        SwaggerDisplayEnum annotation = AnnotationUtils.findAnnotation(rawClass,SwaggerDisplayEnum.class);
        if (annotation == null) {
            return chain.hasNext() ? chain.next().resolve(annotatedType, context, chain) : null;
        }

        Schema schema = chain.hasNext() ? chain.next().resolve(annotatedType, context, chain) : null;
        if (schema == null) {
            schema = new Schema();
            schema.setType(getTypeForClass(rawClass));
        }

        String indexField = annotation.value();
        String nameField = annotation.name();

        Object[] enumConstants = rawClass.getEnumConstants();
        if (enumConstants.length == 0) return schema;

        List<String> displayValues = Arrays.stream(enumConstants)
                .filter(Objects::nonNull)
                .map(item -> {
                    Field index = ReflectionUtils.findField(item.getClass(), indexField);
                    Field name = ReflectionUtils.findField(item.getClass(), nameField);
                    if (index == null || name == null) return null;
                    ReflectionUtils.makeAccessible(index);
                    ReflectionUtils.makeAccessible(name);
                    Object val = ReflectionUtils.getField(index, item);
                    Object desc = ReflectionUtils.getField(name, item);
                    return val + ":" + desc;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        String enumDesc = schema.getDescription();
        if (enumDesc == null) {
            enumDesc = "枚举值";
        } else {
            enumDesc += "；枚举值";
        }
        enumDesc += " (" + String.join("; ", displayValues) + ")";
        schema.setDescription(enumDesc);

        return schema;
    }

    private String getTypeForClass(Class<?> clazz) {
        if (Number.class.isAssignableFrom(clazz) || clazz.isPrimitive() ||
                clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Double.class)) {
            return "number";
        } else {
            return "string";
        }
    }
}