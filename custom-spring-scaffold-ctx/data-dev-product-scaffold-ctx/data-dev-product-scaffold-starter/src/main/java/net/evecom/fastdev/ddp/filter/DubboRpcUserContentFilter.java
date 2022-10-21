package net.evecom.fastdev.ddp.filter;

import net.evecom.fastdev.ddp.UserContext;
import net.evecom.fastdev.ddp.UserInfo;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月30日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
public class DubboRpcUserContentFilter implements Filter {

    /**
     * dubbo 用户KEY
     */
    private final static String KEY = "DUBBO_USER_INFO";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        if (RpcContext.getContext().isConsumerSide()) {
            RpcContext.getContext().setAttachment(KEY, UserContext.getUserInfo(false));
            pushUser();
        } else {
            UserContext.setUserInfo((UserInfo) RpcContext.getContext().getObjectAttachment(KEY));
            popUser();
        }
        return invoker.invoke(invocation);
    }

    private void pushUser() {
        UserInfo userInfo = UserContext.getUserInfo();
        if (RpcContext.getContext().get("username") != null) {
            RpcContext.getContext().setAttachment("username", userInfo.getUsername());
        }
        if (RpcContext.getContext().get("tenantId") != null) {
            RpcContext.getContext().setAttachment("tenantId", userInfo.getTenantId());
        }
        if (RpcContext.getContext().get("accessKey") != null) {
            RpcContext.getContext().setAttachment("accessKey", userInfo.getTenantAccessKey());
            RpcContext.getContext().setAttachment("secretKey", userInfo.getTenantSecretKey());
        }
        if (RpcContext.getContext().get("userId") != null) {
            RpcContext.getContext().setAttachment("userId", userInfo.getUserId());
        }
        if (RpcContext.getContext().get("roles") != null) {
            RpcContext.getContext().setAttachment("roles", userInfo.getRoles());
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
        }

        String clientId = RpcContext.getContext().getAttachment("tenantId");
        if (clientId != null) {
            userInfo.setClientId(clientId);
        }
        UserContext.setUserInfo(userInfo);
    }
}
