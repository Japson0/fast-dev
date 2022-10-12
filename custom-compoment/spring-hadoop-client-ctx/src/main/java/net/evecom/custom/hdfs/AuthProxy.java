package net.evecom.custom.hdfs;

import org.apache.hadoop.security.UserGroupInformation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.security.PrivilegedAction;

/**
 * 身份认证代理类
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月12日
 *
 * @author Japson Huang
 */
public class AuthProxy implements InvocationHandler {


    private HdfsClient client;

    private final UserGroupInformation userGroupInformation;

    public AuthProxy(HdfsClient client, UserGroupInformation userGroupInformation) {
        this.client = client;
        this.userGroupInformation = userGroupInformation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return userGroupInformation.doAs((PrivilegedAction<Object>) () -> {
            try {
                return method.invoke(client, args);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
