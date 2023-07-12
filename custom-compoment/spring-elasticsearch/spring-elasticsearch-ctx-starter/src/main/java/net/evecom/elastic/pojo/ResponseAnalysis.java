//package net.evecom.elastic.pojo;
//
//import net.evecom.elastic.annotations.HigLight;
//import org.springframework.cglib.beans.BeanMap;
//
//import java.beans.BeanInfo;
//import java.beans.Introspector;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.Map;
//
///**
// * <P><B>返回值解析:</B></P>
// * RevisionTrail:(Date/Author/Description)
// * 2023年01月05日 CREATE
// *
// * @author Japson Huang
// * @version 1.0
// */
//public class ResponseHighLightAnalysis<T> {
//
//    private Class<T> tClass;
//
//    private Map<String, Method> writeMethod;
//
//    public ResponseHighLightAnalysis(Class<T> tClass) {
//        this.tClass = tClass;
//    }
//
//    private void init(Class<T> tClass) {
//        BeanMap.create()
//        Field[] declaredFields = tClass.getDeclaredFields();
//        String fieldName;
//        for (Field field : declaredFields) {
//            HigLight annotation = field.getAnnotation(HigLight.class);
//            if (annotation != null) {
//                field.get()
//                fieldName = annotation.value();
//            } else {
//                fieldName = field.getName();
//            }
//
//            BeanInfo beanInfo = Introspector.getBeanInfo(tClass);
//            beanInfo.getPropertyDescriptors()[0].getReadMethod()
//            writeMethod.put(annotation.value(), new PropertyDescriptor(field, fieldName))
//
//        }
//    }
//}
