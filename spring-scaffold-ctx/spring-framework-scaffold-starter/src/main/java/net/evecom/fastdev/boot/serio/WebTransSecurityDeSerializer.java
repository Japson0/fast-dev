/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.boot.serio;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SM4;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import net.evecom.fastdev.boot.utils.WebSecuritySerializeContext;
import net.evecom.fastdev.common.annotation.WebSecuritySerialize;
import net.evecom.fastdev.common.model.CryptoType;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年05月26日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class WebTransSecurityDeSerializer extends JsonDeserializer<String> implements ContextualDeserializer {

    private static StringDeserializer stringDeserializer = new StringDeserializer();

    private static final Map<CryptoType, WebTransSecurityDeSerializer> CACHE = new HashMap<>();

    private CryptoType cryptoType;

    public WebTransSecurityDeSerializer() {
    }

    public WebTransSecurityDeSerializer(CryptoType cryptoType) {
        this.cryptoType = cryptoType;
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String value = p.getValueAsString();
        if (StringUtils.isNotEmpty(value)) {
            byte[] cryptKey = WebSecuritySerializeContext.getCryptKey();
            if (cryptKey != null) {
                return StrUtil.str(deCrypto(cryptKey, value), CharsetUtil.UTF_8);
            }
        }
        return null;
    }

    public byte[] deCrypto(byte[] key, String value) {
        switch (this.cryptoType) {
            case SM4:
                return new SM4(key).decrypt(value);
            case AES:
                return new AES(key).decrypt(value);
            default:
                return null;
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        if (property != null) {
            WebSecuritySerialize webSecuritySerialize = property.getAnnotation(WebSecuritySerialize.class);
            if (webSecuritySerialize.crypto() != CryptoType.NONE) {
                return CACHE.computeIfAbsent(webSecuritySerialize.crypto(),
                        key -> new WebTransSecurityDeSerializer(webSecuritySerialize.crypto()));
            }
        }
        return stringDeserializer;
    }
}
