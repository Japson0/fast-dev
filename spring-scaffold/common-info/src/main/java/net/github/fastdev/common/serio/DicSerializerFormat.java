

package net.github.fastdev.common.serio;

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.ValueSerializer;

import java.io.IOException;

/**
 * <P><B>字典序列化格式:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月13日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public abstract class DicSerializerFormat<T> extends ValueSerializer<T> {

    /**
     * 显示值
     *
     * @author Japson Huang
     */
    public static final String DISPLAY = "display";

    /**
     * 实际值
     *
     * @author Japson Huang
     */

    public static final String VALUE = "value";

    protected void write(JsonGenerator jsonGenerator, String display, Object value) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringProperty(DISPLAY, display);
        jsonGenerator.writePOJOProperty(VALUE, value);
        jsonGenerator.writeEndObject();
    }

}
