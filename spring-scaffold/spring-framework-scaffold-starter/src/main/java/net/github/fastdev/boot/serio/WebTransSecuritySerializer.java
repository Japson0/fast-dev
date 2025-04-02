package net.github.fastdev.boot.serio;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SM4;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import net.github.fastdev.boot.utils.WebSecuritySerializeContext;
import net.github.fastdev.common.annotation.WebSecuritySerialize;
import net.github.fastdev.common.model.CryptoType;
import net.github.fastdev.common.model.DistortionType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <P><B>网络传输安全序列化器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年06月22日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class WebTransSecuritySerializer extends JsonSerializer<String> implements ContextualSerializer {

    private static final Map<String, WebTransSecuritySerializer> CACHE = new HashMap<>();
    /**
     * 脱敏规则
     */
    private DistortionType distortionType;

    /**
     * 加密类型
     */
    private CryptoType cryptoType;

    /**
     * 是否需要加密
     */
    private boolean isCry;

    /**
     * 是否需要脱敏
     */
    private boolean isDis;

    public WebTransSecuritySerializer() {
    }

    public WebTransSecuritySerializer(DistortionType distortionType, CryptoType cryptoType) {
        this.distortionType = distortionType;
        this.cryptoType = cryptoType;
        this.isCry = cryptoType != CryptoType.NONE;
        this.isDis = distortionType != DistortionType.NONE;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if ("".equals(s)) {
            jsonGenerator.writeString("");
        }
        if (isDis && WebSecuritySerializeContext.isFuzzy()) {
            jsonGenerator.writeString(this.fuzzy(s));
            return;
        }
        if (isCry) {
            byte[] cryptKey = WebSecuritySerializeContext.getCryptKey();
            if (cryptKey != null) {
                jsonGenerator.writeString(HexUtil.encodeHexStr(this.enCrypto(cryptKey, s)));
                return;
            }
        }
        jsonGenerator.writeString(s);
    }

    public byte[] enCrypto(byte[] key, String value) {
        switch (this.cryptoType) {
            case SM4:
                return new SM4(key).encrypt(value);
            case AES:
                return new AES(key).encrypt(value);
            default:
                return null;
        }
    }

    public String fuzzy(String value) {
        switch (this.distortionType) {
            case CHINESE_NAME:
                return DesensitizedUtil.chineseName(value);
            case EMAIL:
                return DesensitizedUtil.email(value);
            case ADDRESS:
                return DesensitizedUtil.address(value, 4);
            case ID_CARD:
                return DesensitizedUtil.idCardNum(value,3,10);
            case BANK_CARD:
                return DesensitizedUtil.bankCard(value);
            case FIXED_PHONE:
                return DesensitizedUtil.fixedPhone(value);
            case MOBILE_PHONE:
                return DesensitizedUtil.mobilePhone(value);
            default:
                return value;
        }
    }


    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            WebSecuritySerialize webSecuritySerialize = beanProperty.getAnnotation(WebSecuritySerialize.class);
            String annotationStr = webSecuritySerialize.toString();
            return CACHE.computeIfAbsent(annotationStr, a -> new WebTransSecuritySerializer(webSecuritySerialize.distortion(),
                    webSecuritySerialize.crypto()));
        }
        return serializerProvider.findNullValueSerializer(null);
    }


}
