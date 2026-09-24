
package net.github.fastdev.boot.serio;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.std.StdDeserializer;
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

    private static final ValueDeserializer<String> des = tools.jackson.databind.deser.jdk.StringDeserializer.instance;

    /**
     * 构造
     * RevisionTrail:(Date/Author/Description)
     * 2019年07月25日
     *
     * @author Japson Huang
     */
    public StringDeserializer() {
        this(String.class);
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
    {
        String result = des.deserialize(jp, ctxt);
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        return result;
    }
}
