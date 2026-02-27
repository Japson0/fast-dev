package com.nlecloud.spring.scaffold.filter;

import cn.hutool.core.collection.CollectionUtil;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.common.AuthConstants;
import com.nlecloud.spring.scaffold.common.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * <P><B>用户通用拦截器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年05月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        UserInfo userInfo = UserContext.getUserInfo();
        if (userInfo != null) {
            requestTemplate.header(AuthConstants.USER_ID_HEADER, UserContext.getUserId().toString())
                    .header(AuthConstants.USER_HEADER, userInfo.getUsername());
            if (userInfo.getSchoolId() != null) {
                requestTemplate.header(AuthConstants.SCHOOL_ID_HEADER, userInfo.getSchoolId().toString());
            }
            if (userInfo.getTenantId() != null) {
                requestTemplate.header(AuthConstants.TENANT_ID_HEADER, userInfo.getTenantId().toString());
            }
            if(CollectionUtil.isNotEmpty(userInfo.getRoles())){
                requestTemplate.header(AuthConstants.ROLE_HEADER, String.join(",",userInfo.getRoles()));
            }
            if(userInfo.getNickName()!=null){
                requestTemplate.header(AuthConstants.NICK_NAME_HEADER,userInfo.getNickName());
            }
        }

    }
}
