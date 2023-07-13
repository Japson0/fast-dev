package net.evecom.fastdev.common.model;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.evecom.fastdev.common.serio.EnumDeserializer;
import net.evecom.fastdev.common.serio.EnumSerializer;
import net.evecom.fastdev.swagger.annotation.SwaggerDisplayEnum;

import java.io.Serializable;

/**
 * <P><B>通用枚举:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年08月18日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@JsonSerialize(using = EnumSerializer.class)
@JsonDeserialize(using = EnumDeserializer.class)
@SwaggerDisplayEnum(value = "value", name = "display")
public interface ComEnum<T extends Serializable> extends IEnum<T> {

    /**
     * 序列化显示的值
     * RevisionTrail:(Date/Author/Description)
     * 2020年08月18日
     *
     * @author Japson Huang
     */
    String getDisplay();

    @Override
    T getValue();

    static <T extends Serializable, E extends ComEnum<T>> E getEnum(T t, Class<E> clazz) {
        E[] enumConstants = clazz.getEnumConstants();
        for (E enumConstant : enumConstants) {
            if (enumConstant.getValue().equals(t)) {
                return enumConstant;
            }
        }
        return null;
    }
}
