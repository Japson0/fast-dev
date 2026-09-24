

package net.github.fastdev.common.serio;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.ValueDeserializer;

import java.io.IOException;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月09日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public abstract class DicDeserializerFormat<T> extends ValueDeserializer<T> {

    protected Object getValue(JsonParser parser) {
        JsonToken currentToken = parser.currentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_STRING) {
            return parser.getText();
        } else {
            while (!parser.isClosed()) {
                if (DicSerializerFormat.VALUE.equals(parser.nextName())) {
                    parser.nextValue();
                    Object value = parser.getText();
                    while (parser.currentToken() != JsonToken.END_OBJECT) {
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
