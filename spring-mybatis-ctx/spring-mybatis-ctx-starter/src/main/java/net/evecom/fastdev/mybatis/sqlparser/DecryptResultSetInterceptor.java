package net.evecom.fastdev.mybatis.sqlparser;

import net.evecom.fastdev.mybatis.annotation.CryptAble;
import net.evecom.fastdev.mybatis.encrypt.EncryptCertificate;
import net.evecom.fastdev.mybatis.encrypt.EncryptInspector;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.List;

/**
 * <P><B>数据解密拦截器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月14日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class DecryptResultSetInterceptor implements Interceptor {

    /**
     * 密钥信息
     */
    private final EncryptCertificate encryptCertificate;

    public DecryptResultSetInterceptor(EncryptCertificate encryptCertificate) {
        this.encryptCertificate = encryptCertificate;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object proceed = invocation.proceed();
        if (proceed != null) {
            if (proceed instanceof List) {
                List temp = (List) proceed;
                int resultSize = temp.size();
                if (resultSize > 0) {
                    if (temp.get(0) != null) { //有的会出现List[0] 是空的
                        Class tClass = temp.get(0).getClass();
                        if (CryptAble.class.isAssignableFrom(tClass)) {
                            EncryptInspector encryptInspector = EncryptInspector.generator(tClass);
                            if (!encryptInspector.canCrypt()) {
                                return proceed;
                            }
                            for (int i = 0; i < resultSize; i++) {
                                encryptInspector.decrypt(temp.get(i), encryptCertificate);
                            }
                        }
                    }
                }
            }
        }
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }
}
