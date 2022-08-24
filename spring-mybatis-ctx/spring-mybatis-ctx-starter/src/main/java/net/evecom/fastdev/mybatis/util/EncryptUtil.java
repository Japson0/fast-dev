package net.evecom.fastdev.mybatis.util;

import net.evecom.fastdev.mybatis.encrypt.CryptAble;
import net.evecom.fastdev.mybatis.encrypt.EncryptCertificate;
import net.evecom.fastdev.mybatis.encrypt.EncryptInspector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;

/**
 * <P><B>加密工具类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月13日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class EncryptUtil implements ApplicationContextAware {

    private static EncryptCertificate encryptCertificate;

    public static Method getMethod(String id) {
        int i = id.lastIndexOf('.');
        String methodName = id.substring(i + 1);
        Class aClass = getClass(id);
        if (aClass == null) return null;
        Method[] methods = aClass.getMethods();
        for (Method m : methods) {
            if (methodName.equals(m.getName())) {
                return m;
            }
        }
        return null;
    }

    public static Class getClass(String id) {
        int i = id.lastIndexOf('.');
        String className = id.substring(0, i);
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Object encryptObject(Object object) {
        if (object != null && encryptCertificate != null) {
            if (object instanceof CryptAble) {
                EncryptInspector generator = EncryptInspector.generator(object.getClass());
                return generator.encrypt(object, encryptCertificate);
            }
        }
        return object;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        encryptCertificate = applicationContext.getBean(EncryptCertificate.class);
    }
}
