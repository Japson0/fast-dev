package net.evecom.fastdev.ddp.filter;

import net.evecom.fastdev.ddp.UserContext;
import net.evecom.fastdev.ddp.UserInfo;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * <P><B>dubbo用户信息过滤器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月30日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
public class DubboRpcUserContentFilter implements Filter {

//    /**
//     * dubbo 用户KEY
//     */
//    private final static String KEY = "DUBBO_USER_INFO";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        if (RpcContext.getContext().isConsumerSide()) {
            pushUser();
        } else {
            popUser();
        }
        return invoker.invoke(invocation);
    }

    private void pushUser() {
        Long userId = UserContext.getUserId();
        if (userId != null) {
            UserInfo userInfo = UserContext.getUserInfo();
            RpcContext.getContext().setAttachment("username", userInfo.getUsername());
            RpcContext.getContext().setAttachment("tenantId", userInfo.getTenantId());
            RpcContext.getContext().setAttachment("accessKey", userInfo.getTenantAccessKey());
            RpcContext.getContext().setAttachment("secretKey", userInfo.getTenantSecretKey());
            RpcContext.getContext().setAttachment("userId", userInfo.getUserId());
            RpcContext.getContext().setAttachment("roles", userInfo.getRoles());
            RpcContext.getContext().setAttachment("clientId", userInfo.getTenantSecretKey());
        }
    }

    private void popUser() {
        UserInfo userInfo = new UserInfo();
        String userId = RpcContext.getContext().getAttachment("userId");
        if (userId == null) {
            return; //说明是新系统，这里代码主要是兼容旧系统
        } else {
            userInfo.setUserId(Long.valueOf(userId));
        }

        String username = RpcContext.getContext().getAttachment("username");
        if (username != null) {
            userInfo.setUsername(username);
        }

        String tenantId = RpcContext.getContext().getAttachment("tenantId");
        if (tenantId != null) {
            userInfo.setTenantId(Long.valueOf(tenantId));
            userInfo.setTenantAccessKey(RpcContext.getContext().getAttachment("accessKey"));
            userInfo.setTenantSecretKey(RpcContext.getContext().getAttachment("secretKey"));
        }
        String clientId = RpcContext.getContext().getAttachment("clientId");
        if (clientId != null) {
            userInfo.setClientId(clientId);
        }
        UserContext.setUserInfo(userInfo);
    }
}
