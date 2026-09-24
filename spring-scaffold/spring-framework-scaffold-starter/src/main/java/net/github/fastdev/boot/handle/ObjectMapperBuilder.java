package net.github.fastdev.boot.handle;

import com.fasterxml.jackson.annotation.JsonInclude;
import tools.jackson.databind.*;
import tools.jackson.databind.introspect.Annotated;
import tools.jackson.databind.introspect.JacksonAnnotationIntrospector;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.databind.ser.std.ToStringSerializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.cfg.DateTimeFeature;
import net.github.fastdev.boot.serio.*;
import net.github.fastdev.common.annotation.Dictionary;
import net.github.fastdev.common.annotation.WebSecuritySerialize;
import net.github.fastdev.common.model.ComEnum;
import net.github.fastdev.common.serio.DictionaryDeserializer;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;

import java.lang.annotation.Annotation;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月23日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class ObjectMapperBuilder {

    private final ComEnumDisplayHandle comEnumDisplayHandle;


    private final JsonMapper.Builder builder;

    private final JacksonProperties jacksonProperties;

    public ObjectMapperBuilder(ComEnumDisplayHandle comEnumDisplayHandle, JsonMapper.Builder builder, JacksonProperties jacksonProperties) {
        this.comEnumDisplayHandle = comEnumDisplayHandle;
        this.builder = builder;
        this.jacksonProperties = jacksonProperties;
    }

    public JsonMapper builder() {

        builder.annotationIntrospector(new CustomJacksonAnnotationIntrospector());
        builder.changeDefaultPropertyInclusion(inclusion -> inclusion.withValueInclusion(
                Optional.ofNullable(jacksonProperties.getDefaultPropertyInclusion()).orElse(JsonInclude.Include.NON_NULL)));
        Map<DeserializationFeature, Boolean> deserialization = jacksonProperties.getDeserialization();
        SimpleModule module = new SimpleModule();
        module.addSerializer(ComEnum.class,new DefaultEnumSerializer(comEnumDisplayHandle));
        module.addSerializer(Long.class, ToStringSerializer.instance);
        builder.addModule(module);

        if (deserialization.get(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) == null) {
            builder.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
        builder.enable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        builder.enable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS);
        return builder.build();
    }

    public static class CustomJacksonAnnotationIntrospector extends JacksonAnnotationIntrospector {

        /**
         * 注解序列反序列缓存
         */
        private final static Map<Class<? extends Annotation>, Map.Entry<ValueSerializer<?>, ValueDeserializer<?>>> ANNOTATED_INTROSPECTOR;

        /**
         * 注解信息
         */
        private final static Class<? extends Annotation>[] ANNOTATIONS;

        static {
            ANNOTATED_INTROSPECTOR = new HashMap<>();
            ANNOTATED_INTROSPECTOR.put(Dictionary.class,
                    new AbstractMap.SimpleEntry<>(new DictionarySerializer(), new DictionaryDeserializer()));
            ANNOTATED_INTROSPECTOR.put(WebSecuritySerialize.class,
                    new AbstractMap.SimpleEntry<>(new WebTransSecuritySerializer(), new WebTransSecurityDeSerializer()));
            ANNOTATIONS = ANNOTATED_INTROSPECTOR.keySet().toArray(new Class[0]);
        }


        @Override
        public Object findSerializer(tools.jackson.databind.cfg.MapperConfig<?> config, Annotated a) {
            // 如果是字典注解
            Annotation annotation = null;
            for (Class<? extends Annotation> at : ANNOTATIONS) {
                annotation = _findAnnotation(a, at);
                if (annotation != null) {
                    break;
                }
            }
            if (annotation != null) {
                return ANNOTATED_INTROSPECTOR.get(annotation.annotationType()).getKey();
            }
            return super.findSerializer(config, a);
        }

    }
}
