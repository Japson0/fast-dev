/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.common.serio;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月09日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public abstract class DicDeserializerFormat<T> extends JsonDeserializer<T> {

    protected Object getValue(JsonParser parser) throws IOException {
        JsonToken currentToken = parser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_STRING) {
            return parser.getText();
        } else {
            while (!parser.isClosed()) {
                if (DicSerializerFormat.VALUE.equals(parser.nextFieldName())) {
                    parser.nextValue();
                    Object value = parser.getText();
                    while (parser.getCurrentToken() != JsonToken.END_OBJECT) {
                        //这里是为了让当前token到达这个子Json的末端”}“
                        parser.nextToken();
                    }
                    return value;
                }
                parser.nextToken();
            }
            return null;
        }
    }
}
