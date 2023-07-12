/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.common.serio;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月09日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class DictionaryDeserializer extends DicDeserializerFormat<Object> {

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        if (p.getCurrentToken() == JsonToken.START_ARRAY) {
            List<Object> values = new ArrayList<>();
            while (p.nextToken() != JsonToken.END_ARRAY) {
                Object value = getValue(p);
                values.add(value);
            }
            return values;
        } else {
            return getValue(p);
        }
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return super.deserializeWithType(p, ctxt, typeDeserializer);
    }
}
