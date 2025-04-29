//package net.github.fastdev.swagger;
//
//import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
//import net.github.fastdev.swagger.annotation.SwaggerDisplayEnum;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.util.ReflectionUtils;
//import io.swagger.v3.oas.models.media.Schema;
//import org.springdoc.core.customizers.PropertyCustomizer;
//import org.springframework.context.annotation.Configuration;
//import io.swagger.v3.core.converter.AnnotatedType;
//
//import java.lang.reflect.Field;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//public class EnumsPropertyCustomizer implements PropertyCustomizer {
//
//    @Override
//    public Schema customize(Schema property, AnnotatedType type) {
//        // 从AnnotatedType中获取原始Java类型
//        Class<?> rawPrimaryType = type.getType() instanceof Class ? (Class<?>) type.getType() : null;
//
//        if (rawPrimaryType == null) {
//            return property;
//        }
//
//        SwaggerDisplayEnum annotation = AnnotationUtils.findAnnotation(rawPrimaryType, SwaggerDisplayEnum.class);
//
//        if (annotation != null && Enum.class.isAssignableFrom(rawPrimaryType)) {
//            addDescForEnum(property, rawPrimaryType, annotation);
//        }
//
//        return property;
//    }
//
//    private void addDescForEnum(Schema property, Class<?> fieldType, SwaggerDisplayEnum annotation) {
//        if (annotation != null) {
//            String index = annotation.value();
//            String name = annotation.name();
//
//            Object[] enumConstants = fieldType.getEnumConstants();
//            if (enumConstants.length == 0) return;
//
//            List<String> displayValues =
//                    Arrays.stream(enumConstants)
//                            .filter(Objects::nonNull)
//                            .map(item -> {
//                                Class<?> currentClass = item.getClass();
//
//                                Field indexField = ReflectionUtils.findField(currentClass, index);
//                                ReflectionUtils.makeAccessible(indexField);
//                                Object value = ReflectionUtils.getField(indexField, item);
//
//                                Field descField = ReflectionUtils.findField(currentClass, name);
//                                ReflectionUtils.makeAccessible(descField);
//                                Object desc = ReflectionUtils.getField(descField, item);
//                                return value + ":" + desc;
//                            }).collect(Collectors.toList());
//
//            String currentDescription = property.getDescription();
//            if (currentDescription == null) {
//                currentDescription = "枚举值";
//            } else {
//                currentDescription += "。枚举值";
//            }
//
//            String joinText = currentDescription
//                    + " (" + String.join("; ", displayValues) + ")";
//
//            property.setDescription(joinText);
//
//            // 如果需要，也可以设置枚举值
//            // property.setEnum(Arrays.stream(enumConstants).map(Object::toString).collect(Collectors.toList()));
//        }
//    }
//}