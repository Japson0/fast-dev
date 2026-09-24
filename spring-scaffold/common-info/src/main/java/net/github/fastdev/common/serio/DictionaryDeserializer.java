package net.github.fastdev.common.serio;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DeserializationContext;

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
    public Object deserialize(JsonParser p, DeserializationContext ctxt) {

        if (p.currentToken() == JsonToken.START_ARRAY) {
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

}
