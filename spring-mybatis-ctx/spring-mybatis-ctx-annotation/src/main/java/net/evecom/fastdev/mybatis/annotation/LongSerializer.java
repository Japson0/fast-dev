/*
 * Copyright (c) 2005, 2024, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package net.evecom.fastdev.mybatis.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class LongSerializer extends StdSerializer<Long> {
    public LongSerializer() {
        super(Long.class);
    }

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeNumber(value);
    }
}