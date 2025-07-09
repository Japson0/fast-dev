
package com.nlecloud.spring.scaffold.filter;

import com.nlecloud.spring.common.AuthConstants;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserWrapper;
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
        Long userId = UserContext.getUserId();
        if(userId!=null) {
            RpcContext.getContext().setAttachment(AuthConstants.USER_HEADER, UserContext.getUserName());
            RpcContext.getContext().setAttachment(AuthConstants.USER_ID_HEADER, userId);
            RpcContext.getContext().setAttachment(AuthConstants.TENANT_ID_HEADER, UserContext.getTenantId());

            Set<String> roles = UserContext.getRoles();
            if (!CollectionUtils.isEmpty(roles)) {
                RpcContext.getContext().setAttachment(AuthConstants.ROLE_HEADER, String.join(",", roles));
            }
        }
    }

    private void popUser() {
        String userId = RpcContext.getContext().getAttachment(AuthConstants.USER_ID_HEADER);
        if(userId!=null){
            String tenantId = RpcContext.getContext().getAttachment(AuthConstants.TENANT_ID_HEADER);
            String roles = RpcContext.getContext().getAttachment(AuthConstants.ROLE_HEADER);
            String username =RpcContext.getContext().getAttachment(AuthConstants.USER_HEADER);
            Set<String> rolesSet= Collections.EMPTY_SET;
            if(roles!=null) {
                String[] rolesSplit = roles.split(",");
                rolesSet = new HashSet<>(rolesSplit.length);
                for (String role : rolesSplit) {
                    rolesSet.add(role);
                }
            }
            UserContext.setUserInfo(new UserWrapper(Long.valueOf(userId),username,Long.valueOf(tenantId),rolesSet));
        }
    }

}
