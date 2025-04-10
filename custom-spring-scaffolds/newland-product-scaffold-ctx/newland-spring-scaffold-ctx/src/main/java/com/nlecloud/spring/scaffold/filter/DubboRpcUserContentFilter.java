
package com.nlecloud.spring.scaffold.filter;

import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserWrapper;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * <P><B>dubbo用户信息过滤器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月30日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
public class DubboRpcUserContentFilter implements Filter {

    /**
     * dubbo 用户KEY
     */
    private final static String USER_ID = "DUBBO_USER_ID";

    private final static String USER_NAME="DUBBO_USER_NAME";

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
        String userName = UserContext.getUserName();
        if(userName!=null){
            RpcContext.getContext().setAttachment(USER_NAME, userName);
        }
        Long userId = UserContext.getUserId();
        if(userName!=null){
            RpcContext.getContext().setAttachment(USER_ID, userId);
        }
    }

    private void popUser() {
        String userName = RpcContext.getContext().getAttachment(USER_NAME);
        String userId = RpcContext.getContext().getAttachment(USER_ID);
        if(userName!=null&&userId!=null){
            UserContext.setUserInfo(new UserWrapper(Long.valueOf(userId),userName));
        }
    }

}
