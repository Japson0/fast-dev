package net.evecom.fastdev.common.serio;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import net.evecom.fastdev.common.model.ComEnum;

import java.io.IOException;

/**
 * <P><B>枚举序列化器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年06月22日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class EnumSerializer extends DicSerializerFormat<ComEnum> {


    public EnumSerializer() {
    }

    @Override
    public void serialize(ComEnum s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        write(jsonGenerator, s.getDisplay(), s.getValue());
    }
}
