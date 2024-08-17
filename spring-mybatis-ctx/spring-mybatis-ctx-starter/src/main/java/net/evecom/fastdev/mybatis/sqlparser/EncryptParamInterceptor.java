package net.evecom.fastdev.mybatis.sqlparser;

import net.evecom.fastdev.mybatis.annotation.CryptAble;
import net.evecom.fastdev.mybatis.encrypt.*;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

/**
 * <P><B>数据加密拦截器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年09月28日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class EncryptParamInterceptor implements Interceptor {


    private final Predicate<String > ignore;

    private final EncryptCertificate encryptCertificate;

    public EncryptParamInterceptor(EncryptCertificate encryptCertificate) {
        this(encryptCertificate,f->false);
    }

    public EncryptParamInterceptor(EncryptCertificate encryptCertificate, Predicate<String > ignore) {
        this.encryptCertificate = encryptCertificate;
        this.ignore=ignore;
    }


    public void encryptObject(Object object){
        if(object instanceof CryptAble){
            EncryptInspector.generator(object.getClass())
                    .encrypt(object,encryptCertificate);
        }else if(object instanceof Map){
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) object).entrySet()) {
                encryptObject(entry.getValue());
            }
        }else if(object instanceof Collection){
            ((Collection<?>) object).forEach(this::encryptObject);
        }
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        if(!ignore.test(ms.getId())){
            encryptObject(args[1]);
        }
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
