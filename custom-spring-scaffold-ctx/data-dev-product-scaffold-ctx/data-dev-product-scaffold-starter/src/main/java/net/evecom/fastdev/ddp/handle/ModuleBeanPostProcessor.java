package net.evecom.fastdev.ddp.handle;

import net.evecom.fastdev.ddp.annotation.ModuleAutowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <P><B>模块服务注入配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月26日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ModuleBeanPostProcessor implements BeanPostProcessor {

    /**
     * 日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(ModuleBeanPostProcessor.class);

    /**
     * spring上下文
     */
    private ApplicationContext applicationContext;

    public ModuleBeanPostProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        do {
            for (final Field field : clazz.getDeclaredFields()) {
                final ModuleAutowired annotation = AnnotationUtils.findAnnotation(field, ModuleAutowired.class);
                if (annotation != null) {
                    ReflectionUtils.makeAccessible(field);
                    ReflectionUtils.setField(field, bean, processInjectionPoint(field.getType()));
                }
            }
            for (final Method method : clazz.getDeclaredMethods()) {
                final ModuleAutowired annotation = AnnotationUtils.findAnnotation(method, ModuleAutowired.class);
                if (annotation != null) {
                    final Class<?>[] paramTypes = method.getParameterTypes();
                    if (paramTypes.length != 1) {
                        throw new BeanDefinitionStoreException(
                                "Method " + method + " doesn't have exactly one parameter.");
                    }
                    ReflectionUtils.makeAccessible(method);
                    ReflectionUtils.invokeMethod(method, bean,
                            processInjectionPoint(paramTypes[0]));
                }
            }
            clazz = clazz.getSuperclass();
        } while (clazz != null);
        return bean;
    }

    // 创建代理类，在具体方法执行前后输出一个日志
    public <T> T processInjectionPoint(final Class<T> injectionType) {

        ObjectProvider<T> beanProvider = applicationContext.getBeanProvider(injectionType);
        T bean = beanProvider.getIfAvailable();
        if (bean != null) return bean;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(injectionType);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            LOGGER.info("{} 模块接口未注入，执行为空", obj.getClass().getName());
            return null;
        });
        return (T) enhancer.create();
    }


}
