/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.common.serio;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;

import java.io.IOException;

/**
 * <P><B>字典序列化格式:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月13日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public abstract class DicSerializerFormat<T> extends JsonSerializer<T> {

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

    protected void write(JsonGenerator jsonGenerator, String display, String value) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(DISPLAY, display);
        jsonGenerator.writeStringField(VALUE, value);
        jsonGenerator.writeEndObject();
    }

}
