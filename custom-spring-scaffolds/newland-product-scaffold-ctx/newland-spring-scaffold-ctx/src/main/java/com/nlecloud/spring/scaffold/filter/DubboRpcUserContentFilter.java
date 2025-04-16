
package com.nlecloud.spring.scaffold.filter;

import com.necloud.spring.common.AuthConstants;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.necloud.spring.common.handle.UserWrapper;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
            RpcContext.getContext().setAttachment(AuthConstants.USER_HEADER, userName);
        }
        Long userId = UserContext.getUserId();
        if(userName!=null){
            RpcContext.getContext().setAttachment(AuthConstants.USER_ID_HEADER, userId);
        }
        Set<String> roles = UserContext.getRoles();
        if(!CollectionUtils.isEmpty(roles)){
            RpcContext.getContext().setAttachment(AuthConstants.ROLE_HEADER, String.join(",", roles));

        }
    }

    private void popUser() {
        String userName = RpcContext.getContext().getAttachment(AuthConstants.USER_HEADER);
        String userId = RpcContext.getContext().getAttachment(AuthConstants.USER_ID_HEADER);
        if(userName!=null&&userId!=null){
            String roles = RpcContext.getContext().getAttachment(AuthConstants.ROLE_HEADER);
            Set<String> rolesSet= Collections.EMPTY_SET;
            if(roles!=null) {
                String[] rolesSplit = roles.split(",");
                rolesSet = new HashSet<>(rolesSplit.length);
                for (String role : rolesSplit) {
                    rolesSet.add(role);
                }
            }
            UserContext.setUserInfo(new UserWrapper(Long.valueOf(userId),userName,rolesSet));
        }
    }

}
