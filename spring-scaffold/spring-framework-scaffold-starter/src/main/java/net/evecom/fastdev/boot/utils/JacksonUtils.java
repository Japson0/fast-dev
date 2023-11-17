/*
 * Copyright (c) 2005, 2020, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.fastdev.boot.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.evecom.fastdev.boot.serio.StringDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Supplier;

/**
 * <P><B>Description: </B> json 工具类  </P>
 * Revision Trail: (Date/Author/Description)
 * 2020/6/16 Cory Chen CREATE
 *
 * @author Cory Chen
 * @version 1.0
 */
public class JacksonUtils {

    /**
     * 日志器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtils.class);

    /**
     * Jackson Mapper 对象
     */
    private static ObjectMapper objectMapper;

    /**
     * 设置一些通用的属性
     */

    private JacksonUtils() {
    }

    /**
     *如果在Spring启动前调用则自定义配置
     *RevisionTrail:(Date/Author/Description)
     * 2021年07月23日
     *@author Japson Huang
     *
     */
    static {
        try {
            objectMapper = SpringUtil.getBean(ObjectMapper.class);
        } catch (Exception e) {
            objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
            SimpleModule module = new SimpleModule();
            module.addDeserializer(String.class, new StringDeserializer());
            objectMapper.registerModule(module);
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        }
    }

    public static void main(String[] args) {
        List<String> id = new ArrayList<>();
        id.add("1");
        id.add("sad");
        String join = String.join(",", id);
        System.out.println(join);
        JacksonUtils.toJson(id);
        System.out.println(id);
    }

    /**
     * 获取实例
     * Revision Trail: (Date/Author/Description)
     * 2020/8/29 Timer He CREATE
     *
     * @return
     * @author Timer He
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * 对象序列化为JSON字符串
     * Revision Trail: (Date/Author/Description)
     * 2020/8/29 Timer He CREATE
     *
     * @param obj
     * @return
     * @author Timer He
     */
    public static String toJson(Object obj) {
        try {
            StringWriter writer = new StringWriter();
            objectMapper.writeValue(writer, obj);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("序列化对象【" + obj + "】时出错", e);
        }
    }

    /**
     * 按指定View视图输出
     * Revision Trail: (Date/Author/Description)
     * 2020/9/17 Timer He CREATE
     *
     * @param obj
     * @param viewClass
     * @return
     * @author Timer He
     */
    public static String toJsonWithView(Object obj, Class<?> viewClass) {
        try {
            StringWriter writer = new StringWriter();
            objectMapper.writerWithView(viewClass).writeValue(writer, obj);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("序列化对象【" + obj + "】时出错", e);
        }
    }

    /**
     * JSON反序列化为对象
     * Revision Trail: (Date/Author/Description)
     * 2020/8/29 Timer He CREATE
     *
     * @param entityClass
     * @param jsonString
     * @param <T>
     * @return
     * @author Timer He
     */
    public static <T> T toBean(String jsonString, Class<T> entityClass) {
        try {
            if (ObjectUtil.isEmpty(jsonString)) {
                return null;
            }
            return objectMapper.readValue(jsonString, entityClass);
        } catch (Exception e) {
            throw new RuntimeException(String.format("JSON【%s】转对象时出错", jsonString), e);
        }
    }

    /**
     * 将JSON格式字符串解析为JsonNode对象
     * Revision Trail: (Date/Author/Description)
     * 2020/7/29 Timer He CREATE
     *
     * @param content
     * @return
     * @throws IOException
     * @author Timer He
     */
    public static JsonNode readTree(String content) throws IOException {
        return ObjectUtil.isNull(content) ? null : objectMapper.readTree(content);
    }

    /**
     * JSON字符串反序列为Map
     * Revision Trail: (Date/Author/Description)
     * 2020/8/29 Timer He CREATE
     *
     * @param jsonString
     * @param <T>
     * @return
     * @throws Exception
     * @author Timer He
     */
    public static <T> Map<String, T> toMap(String jsonString) throws Exception {
        return objectMapper.readValue(jsonString, Map.class);
    }

    /**
     * JSON对象反序列化Map<String, T>
     * Revision Trail: (Date/Author/Description)
     * 2020/8/29 Timer He CREATE
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     * @author Timer He
     */
    public static <T> Map<String, T> toMap(String jsonString, Class<T> clazz) throws Exception {
        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Map<String, Object>>>() {
        });
        Map<String, T> result = new HashMap<String, T>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), mapToPojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * 深度反序列化Map
     * Revision Trail: (Date/Author/Description)
     * 2020/8/29 Timer He CREATE
     *
     * @param jsonString
     * @return
     * @throws Exception
     * @author Timer He
     */
    public static Map<String, Object> toMapDeeply(String jsonString) throws Exception {
        return toMapRecursion(jsonString, objectMapper);
    }

    /**
     * JSON反序列化为List
     *
     * @param jsonString
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String jsonString, Class<T> tClass) {
        return StrUtil.isNotEmpty(jsonString) ? toList(jsonString, tClass, () -> null) : null;
    }

    /**
     * JSON反序列化为List
     * Revision Trail: (Date/Author/Description)
     * 2020/8/29 Timer He CREATE
     *
     * @param value
     * @param tClass
     * @param defaultSupplier
     * @param <T>
     * @return
     * @author Timer He
     */
    public static <T> List<T> toList(String value, Class<T> tClass, Supplier<List<T>> defaultSupplier) {
        try {
            if (StringUtils.isBlank(value)) {
                return defaultSupplier.get();
            }
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, tClass);
            return objectMapper.readValue(value, javaType);
        } catch (Throwable e) {
            LOGGER.error(String.format("toJavaObjectList exception \n%s\n%s", value, tClass), e);
        }
        return defaultSupplier.get();
    }

    /**
     * JSON反序列化ListMap
     * Revision Trail: (Date/Author/Description)
     * 2020/8/29 Timer He CREATE
     *
     * @param jsonString
     * @return
     * @throws IOException
     * @author Timer He
     */
    public static List<Map<String, Object>> toListMap(String jsonString) throws IOException {
        return objectMapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {
        });

    }

    /**
     * Map转换为Bean
     * Revision Trail: (Date/Author/Description)
     * 2020/8/29 Timer He CREATE
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     * @author Timer He
     */
    private static <T> T mapToPojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * 把 JSON 解析成 Map，如果 Map 内部的 Value 存在 jsonString，继续解析
     * Revision Trail: (Date/Author/Description)
     * 2020/8/29 Timer He CREATE
     *
     * @param jsonString
     * @param mapper
     * @return
     * @throws Exception
     * @author Timer He
     */
    private static Map<String, Object> toMapRecursion(String jsonString, ObjectMapper mapper) throws Exception {
        if (jsonString == null) {
            return null;
        }
        Map<String, Object> map = mapper.readValue(jsonString, Map.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object obj = entry.getValue();
            if (obj != null && obj instanceof String) {
                String str = ((String) obj);

                if (str.startsWith("[")) {
                    List<?> list = toListRecursion(str, mapper);
                    map.put(entry.getKey(), list);
                } else if (str.startsWith("{")) {
                    Map<String, Object> mapRecursion = toMapRecursion(str, mapper);
                    map.put(entry.getKey(), mapRecursion);
                }
            }
        }

        return map;
    }

    /**
     * 把 JSON 解析成 List，如果 List 内部的元素存在 jsonString，继续解析
     * Revision Trail: (Date/Author/Description)
     * 2020/8/29 Timer He CREATE
     *
     * @param jsonString
     * @param mapper
     * @return
     * @throws Exception
     * @author Timer He
     */
    private static List<Object> toListRecursion(String jsonString, ObjectMapper mapper) throws Exception {
        if (jsonString == null) {
            return null;
        }

        List<Object> list = mapper.readValue(jsonString, List.class);

        for (Object obj : list) {
            if (obj != null && obj instanceof String) {
                String str = (String) obj;
                if (str.startsWith("[")) {
                    obj = toListRecursion(str, mapper);
                } else if (obj.toString().startsWith("{")) {
                    obj = toMapRecursion(str, mapper);
                }
            }
        }

        return list;
    }

    /**
     * 将对象转换成json node
     * Revision Trail: (Date/Author/Description)
     * 2021/6/22 Timer He CREATE
     *
     * @param obj
     * @return
     * @author Timer He
     */
    public static JsonNode toJsonNode(Object obj) {
        return objectMapper.convertValue(obj, JsonNode.class);
    }
}
