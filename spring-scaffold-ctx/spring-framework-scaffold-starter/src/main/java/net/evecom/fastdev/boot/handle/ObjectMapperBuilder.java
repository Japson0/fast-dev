package net.evecom.fastdev.boot.handle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.evecom.fastdev.boot.serio.DictionarySerializer;
import net.evecom.fastdev.boot.serio.StringDeserializer;
import net.evecom.fastdev.boot.serio.WebTransSecurityDeSerializer;
import net.evecom.fastdev.boot.serio.WebTransSecuritySerializer;
import net.evecom.fastdev.common.annotation.Dictionary;
import net.evecom.fastdev.common.annotation.WebSecuritySerialize;
import net.evecom.fastdev.common.serio.DictionaryDeserializer;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.lang.annotation.Annotation;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ObjectMapperBuilder {


    public static ObjectMapper builder(Jackson2ObjectMapperBuilder builder, JacksonProperties jacksonProperties) {

        builder.annotationIntrospector(new CustomJacksonAnnotationIntrospector());
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        Map<DeserializationFeature, Boolean> deserialization = jacksonProperties.getDeserialization();
        Boolean nullAccept = deserialization.get(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        if (nullAccept == null || nullAccept) {
            // “”字符串转NULL
            builder.featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            SimpleModule module = new SimpleModule();
            module.addDeserializer(String.class, new StringDeserializer());
            builder.modules(module);
        }

        if (deserialization.get(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) == null) {
            builder.failOnUnknownProperties(false);
        }
        builder.defaultViewInclusion(true);
        builder.featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return builder.createXmlMapper(false).build();
    }

    public static class CustomJacksonAnnotationIntrospector extends JacksonAnnotationIntrospector {

        /**
         * 注解序列反序列缓存
         */
        private final static Map<Class<? extends Annotation>, Map.Entry<JsonSerializer<?>, JsonDeserializer<?>>> ANNOTATED_INTROSPECTOR;

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
        public Object findSerializer(Annotated a) {
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
            return super.findSerializer(a);
        }

    }
}
