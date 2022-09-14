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
        } else {
            UserContext.setUserInfo((UserInfo) RpcContext.getContext().getObjectAttachment(KEY));
        }
        return invoker.invoke(invocation);
    }
}
