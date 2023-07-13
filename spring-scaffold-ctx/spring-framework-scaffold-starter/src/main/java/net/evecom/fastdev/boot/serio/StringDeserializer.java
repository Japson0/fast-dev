/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.boot.serio;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * <P><B>JackSon反序列解释器:例如（参数用@RequestBody时会调用一下序列化方法，将参数里面空字符串替换成NULL）</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年07月25日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class StringDeserializer extends StdDeserializer<String> {

    private static final JsonDeserializer<String> des = com.fasterxml.jackson.databind.deser.std.StringDeserializer.instance;

    /**
     * 构造
     * RevisionTrail:(Date/Author/Description)
     * 2019年07月25日
     *
     * @author Japson Huang
     */
    public StringDeserializer() {
        this(null);
    }

    /**
     * 构造
     * RevisionTrail:(Date/Author/Description)
     * 2019年07月25日
     *
     * @author Japson Huang
     */
    public StringDeserializer(Class<?> clazz) {
        super(clazz);
    }

    /**
     * 转换描述，反序列化时会调用下面方法
     * RevisionTrail:(Date/Author/Description)
     * 2019年07月25日
     *
     * @author Japson Huang
     */
    @Override
    public String deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        String result = des.deserialize(jp, ctxt);
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        return result;
    }
}
