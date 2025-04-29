package net.github.fastdev.boot.serio;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import net.github.fastdev.boot.handle.ComEnumDisplayHandle;
import net.github.fastdev.common.model.ComEnum;
import net.github.fastdev.common.serio.DicSerializerFormat;

import java.io.IOException;

/**
 * <P><B>枚举序列化器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年06月22日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class DefaultEnumSerializer extends DicSerializerFormat<ComEnum> {


    private final ComEnumDisplayHandle comEnumDisplayHandle;

    public DefaultEnumSerializer(ComEnumDisplayHandle comEnumDisplayHandle) {
        this.comEnumDisplayHandle = comEnumDisplayHandle;
    }

    @Override
    public void serialize(ComEnum s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        write(jsonGenerator, comEnumDisplayHandle.getDisplay(s), s.getValue());
    }
}
