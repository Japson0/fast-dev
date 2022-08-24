package net.evecom.fastdev.mybatis.sqlparser;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.parser.SqlInfo;
import net.evecom.fastdev.mybatis.encrypt.*;
import net.evecom.fastdev.mybatis.injector.BaseMapperExtend;
import net.evecom.fastdev.mybatis.util.EncryptUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <P><B>数据加密拦截器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年09月28日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class EncryptParamInterceptor implements Interceptor {

    private final static String COUNT_STR = "_mpCount";


    private final EncryptCertificate encryptCertificate;

    public EncryptParamInterceptor(EncryptCertificate encryptCertificate) {
        this.encryptCertificate = encryptCertificate;
    }

    private final static Map<String, EncryptInterface> NULL_ENCRYPT_MAP = new HashMap<>(0);

    private static Map<String, Map<String, EncryptInterface>> encryptParams = new ConcurrentHashMap<>();


    public SqlInfo parser(String methodId, Object parameterObject) throws Exception {
        Map<String, EncryptInterface> argsMap = getArgsMap(methodId);
        if (argsMap != null && argsMap.size() > 0) {
            for (Map.Entry<String, EncryptInterface> encryptInterfaceEntry : argsMap.entrySet()) {
                String paramName = encryptInterfaceEntry.getKey();
                EncryptInterface encryptInterface = encryptInterfaceEntry.getValue();
                Object value = getParamObjectValue(parameterObject, paramName);
                if (value != null) {
                    Class<?> targetValueClass = value.getClass();
                    if (targetValueClass == String.class && !"".equals(value)) {
                        Object encryptValue = encryptInterface.encrypt(value, encryptCertificate);
                        if (parameterObject instanceof Map) {
                            MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) parameterObject;
                            paramMap.put(paramName, encryptValue);
                        } else {
                            parameterObject = encryptValue;
                        }
//                        boundSql.setAdditionalParameter("_parameter."+paramName, encryptInterface.encrypt(value, encryptCertificate));
                    } else {
                        if (targetValueClass == encryptInterface.getSourceClass()) {
                            encryptInterface.encrypt(value, encryptCertificate);
                        } else {
                            EncryptInspector.generator(targetValueClass).encrypt(value, encryptCertificate);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Map<String, EncryptInterface> getArgsMap(String id) {
        int countIndex = id.indexOf(COUNT_STR);
        if (countIndex > 0) {
            id = id.substring(0, countIndex);
        }
        Map<String, EncryptInterface> map = encryptParams.get(id);
        if (map == null) {
            Method method = EncryptUtil.getMethod(id);
            Class genericityClass = null;
            if (method == null || method.getAnnotation(SkinEncrypt.class) != null) {
                encryptParams.put(id, NULL_ENCRYPT_MAP);
                return null;
            }
            Class declaringClass = method.getDeclaringClass();
            //为了适应Mybatis-plus中的方法
            if (declaringClass == BaseMapper.class || declaringClass == BaseMapperExtend.class) {
                Class sourceClass = EncryptUtil.getClass(id);
                if (sourceClass == null) {
                    encryptParams.put(id, NULL_ENCRYPT_MAP);
                    return null;
                }
                ParameterizedType genericInterface = (ParameterizedType) sourceClass.getGenericInterfaces()[0];
                genericityClass = (Class) genericInterface.getActualTypeArguments()[0];
            }
            map = generateEncryptParams(method, genericityClass);
            if (map.size() > 0) {
                encryptParams.put(id, map);
            } else {
                encryptParams.put(id, NULL_ENCRYPT_MAP);
            }
        }
        return map;
    }

    private Object getParamObjectValue(Object parameterObject, String paramName) {

        if (parameterObject instanceof Map) {
            return ((Map) parameterObject).get(paramName);
        }
        return parameterObject;
    }

    protected Map<String, EncryptInterface> generateEncryptParams(Method method, Class genericityClass) {
        Parameter[] parameters = method.getParameters();
        Map<String, EncryptInterface> ansMap = new HashMap<>();
        for (Parameter parameter : parameters) {
            if (parameter.getAnnotation(SkinEncrypt.class) != null) continue;
            Param param = parameter.getAnnotation(Param.class);
            String paramsName = param != null ? param.value() : parameter.getName();
            Class<?> parameterType = parameter.getType();
            if (parameterType == Object.class && genericityClass != null) {
                parameterType = genericityClass;
            }
            Encrypt encrypt = parameter.getAnnotation(Encrypt.class);
            EncryptInterface encryptInterface = null;
            if (encrypt != null) {
                if (parameterType.isArray()) continue;
                if (parameterType == String.class) {
                    encryptInterface = EncryptString.generator(encrypt.value());
                }
            } else if (CryptAble.class.isAssignableFrom(parameterType)) {
                encryptInterface = EncryptInspector.generator(parameterType);
            }
            if (encryptInterface != null) {

                ansMap.put(paramsName, encryptInterface);
            }
        }
        return ansMap;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];
        parser(ms.getId(), parameter);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }
}
