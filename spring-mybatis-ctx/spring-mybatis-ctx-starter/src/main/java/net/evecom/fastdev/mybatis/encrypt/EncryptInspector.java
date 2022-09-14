package net.evecom.fastdev.mybatis.encrypt;


import net.evecom.fastdev.mybatis.annotation.CryptAble;
import net.evecom.fastdev.mybatis.annotation.Encrypt;
import net.evecom.fastdev.mybatis.annotation.EncryptType;
import net.evecom.fastdev.mybatis.util.Sm4Util;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <P><B>加密类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月12日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class EncryptInspector implements EncryptInterface {


    /**
     * 对于构造失败的类，可能是没有默认构造方法
     */
    private static final EncryptInspector NULL_ENCRYPT = new EncryptInspector(Object.class, new HashMap<>(), new HashMap<>());

    /**
     * 缓存map,免得每次相同的类还要重新解析
     */
    private static Map<Class, EncryptInspector> cacheEncryptInspector = new ConcurrentHashMap<>();

    /**
     * 参数Map
     */
    private Map<PropertyDescriptor, EncryptType> argsMap;

    /**
     * 成员变量未实体类的时候的解析
     */
    private final Map<PropertyDescriptor, EncryptInspector> numberObject;


    private Class sourceClass;

    private EncryptInspector(Class sourceClass, Map<PropertyDescriptor, EncryptType> argsMap, Map<PropertyDescriptor, EncryptInspector> numberObject) {
        this.sourceClass = sourceClass;
        this.argsMap = argsMap.size() > 0 ? argsMap : null;
        this.numberObject = numberObject.size() > 0 ? numberObject : null;
    }

    public static EncryptInspector generator(final Class aclass) {
        EncryptInspector encryptInspector = cacheEncryptInspector.get(aclass);
        if (encryptInspector == null) {
            Class targetClass = aclass;
            BeanWrapper beanWrapper;
            try {
                beanWrapper = new BeanWrapperImpl(aclass);
            } catch (Exception ignore) {
                return NULL_ENCRYPT;
            }
            Map<PropertyDescriptor, EncryptType> argsMap = new HashMap<>();
            Map<PropertyDescriptor, Class> numberClasses = new HashMap<>();
            while (targetClass != Object.class) {
                Field[] declaredFields = targetClass.getDeclaredFields();
                for (Field field : declaredFields) {
                    Encrypt encrypt = field.getAnnotation(Encrypt.class);
                    Class<?> fileClass = getFiledType(field);
                    if (fileClass == null) continue;
                    if (CryptAble.class.isAssignableFrom(fileClass)) {
                        PropertyDescriptor propertyDescriptor = beanWrapper.getPropertyDescriptor(field.getName());
                        if (checkPropertyDescriptor(propertyDescriptor, fileClass)) {
                            numberClasses.put(propertyDescriptor, fileClass);
                        }
                    } else if (encrypt != null) {
                        if (fileClass == String.class) {
                            PropertyDescriptor propertyDescriptor = beanWrapper.getPropertyDescriptor(field.getName());
                            if (checkPropertyDescriptor(propertyDescriptor, String.class)) {
                                argsMap.put(propertyDescriptor, encrypt.value());
                            }
                        }
                    }
                }

                targetClass = targetClass.getSuperclass();
            }
            Map<PropertyDescriptor, EncryptInspector> numberInspectorMap = new HashMap<>(numberClasses.size());
            for (Map.Entry<PropertyDescriptor, Class> numberClassEntry : numberClasses.entrySet()) {
                EncryptInspector numberEncrypt = generator(numberClassEntry.getValue());
                if (numberEncrypt.canCrypt()) {
                    numberInspectorMap.put(numberClassEntry.getKey(), numberEncrypt);
                }
            }
            encryptInspector = new EncryptInspector(aclass, argsMap, numberInspectorMap);
            cacheEncryptInspector.put(aclass, encryptInspector);
        }
        return encryptInspector;
    }


    private static Class getFiledType(Field field) {
        Class<?> type = field.getType();
        if (List.class.isAssignableFrom(type)) {
            Type genericType = field.getGenericType();
            if (genericType == null) return null;
            // 如果是泛型参数的类型
            if (genericType instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) genericType;
                //得到泛型里的class类型对象
                return (Class<?>) pt.getActualTypeArguments()[0];
            }
        }
        return type;
    }

    private static boolean checkPropertyDescriptor(PropertyDescriptor propertyDescriptor, Class clazz) {
        Method readMethod = propertyDescriptor.getReadMethod();
        Method writeMethod = propertyDescriptor.getWriteMethod();
        return readMethod != null && writeMethod != null;
    }

    @Override
    public Object encrypt(Object object, EncryptCertificate encryptCertificate) {
        return encryptOrDecrypt(object, encryptCertificate, true);
    }

    @Override
    public Object decrypt(Object object, EncryptCertificate encryptCertificate) {
        return encryptOrDecrypt(object, encryptCertificate, false);
    }

    @Override
    public Class getSourceClass() {
        return sourceClass;
    }

    public boolean canCrypt() {
        return this.argsMap != null || this.numberObject != null;
    }

    public Object encryptOrDecrypt(Object object, EncryptCertificate encryptCertificate, boolean isEncrypt) {
        if (object != null) {
            if (argsMap != null) {
                for (Map.Entry<PropertyDescriptor, EncryptType> encryptTypeEntry : argsMap.entrySet()) {
                    PropertyDescriptor propertyDescriptor = encryptTypeEntry.getKey();
                    try {
                        Object value = propertyDescriptor.getReadMethod().invoke(object);
                        if (value != null) {
                            if (encryptTypeEntry.getValue() == EncryptType.SM4) {
                                if (isEncrypt) {
                                    value = Sm4Util.encryptEcb(encryptCertificate.getSm4Key(), (String) value);
                                } else {
                                    if (value instanceof List) {
                                        List tempVs = (List) value;
                                        for (int i = 0; i < tempVs.size(); i++) {
                                            Object tempV = tempVs.get(i);
                                            if (tempV.getClass() == String.class) {
                                                tempVs.set(i, tempV);
                                            }
                                        }
                                    } else {
                                        value = Sm4Util.decryptEcb(encryptCertificate.getSm4Key(), (String) value);
                                    }
                                }
                            }
                            propertyDescriptor.getWriteMethod().invoke(object, value);
                        }
                    } catch (Exception ignore) {
                    }
                }
            }
            if (!isEncrypt) {
                decryptNumber(object, encryptCertificate);
            }
        }
        return object;
    }

    private Object decryptNumber(Object object, EncryptCertificate encryptCertificate) {
        if (numberObject != null) {
            for (Map.Entry<PropertyDescriptor, EncryptInspector> numEncry : numberObject.entrySet()) {
                PropertyDescriptor propertyDescriptor = numEncry.getKey();
                try {
                    Object numberValue = propertyDescriptor.getReadMethod().invoke(object);
                    if (numberValue == null) continue;
                    EncryptInspector encryptInspector = numEncry.getValue();
                    if (numberValue instanceof List) {
                        List tempValue = (List) numberValue;
                        int size = tempValue.size();
                        if (size == 0) continue;
                        for (int i = 0; i < size; i++) {
                            encryptInspector.decrypt(tempValue.get(i), encryptCertificate);
                        }
                    } else {
                        encryptInspector.decrypt(numberValue, encryptCertificate);
                    }
                } catch (Exception ignore) {
                }
            }
        }
        return object;
    }


}
