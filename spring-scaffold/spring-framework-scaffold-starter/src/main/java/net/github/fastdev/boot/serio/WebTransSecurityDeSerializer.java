
package net.github.fastdev.boot.serio;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SM4;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import net.github.fastdev.boot.utils.WebSecuritySerializeContext;
import net.github.fastdev.common.annotation.WebSecuritySerialize;
import net.github.fastdev.common.model.CryptoType;
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
 * @version 1.0
 */
public class WebTransSecurityDeSerializer extends ValueDeserializer<String> {

    private static StringDeserializer stringDeserializer = new StringDeserializer();

    private static final Map<CryptoType, WebTransSecurityDeSerializer> CACHE = new HashMap<>();

    private CryptoType cryptoType;

    public WebTransSecurityDeSerializer() {
    }

    public WebTransSecurityDeSerializer(CryptoType cryptoType) {
        this.cryptoType = cryptoType;
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) {
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
    public ValueDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
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
