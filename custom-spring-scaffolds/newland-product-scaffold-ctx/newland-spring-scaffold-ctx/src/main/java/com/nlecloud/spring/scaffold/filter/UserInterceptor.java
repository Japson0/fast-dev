
package com.nlecloud.spring.scaffold.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.Header;
import com.nlecloud.spring.common.AuthConstants;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserWrapper;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.HeaderUtil;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Enumeration;

/**
 * <P><B>用户拦截器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月04日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class UserInterceptor implements CustomInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(handler instanceof HandlerMethod) {

            String userId = request.getHeader(AuthConstants.USER_ID_HEADER);

            if (StringUtils.isNotEmpty(userId)) {
                String username = request.getHeader(AuthConstants.USER_HEADER);
                String schoolId = request.getHeader(AuthConstants.SCHOOL_ID_HEADER);
                String currentId = request.getHeader(AuthConstants.CURRENT_TENANT_ID_HEADER);
                String roleStr = request.getHeader(AuthConstants.ROLE_HEADER);
                String nickName = request.getHeader(AuthConstants.NICK_NAME_HEADER);


                String[] tenantIds = StringUtils.split(request.getHeader(AuthConstants.TENANT_ID_HEADER), ",");

                String veryCurrentTenantId = null;
                if(!ArrayUtils.isEmpty(tenantIds)){
                    if(tenantIds.length==1||currentId==null){
                        veryCurrentTenantId = tenantIds[0];
                    }else {
                        for (String tenantId : tenantIds) {
                            if(tenantId.equals(currentId)){
                                veryCurrentTenantId=tenantId;
                                break;
                            }
                        }
                    }

                }
                UserContext.setUserInfo(new UserWrapper(Long.valueOf(userId),nickName,
                        username, veryCurrentTenantId == null ? 0L : Long.valueOf(veryCurrentTenantId),
                        StringUtils.isNotEmpty(schoolId) ? Long.valueOf(schoolId) : null,
                        StringUtils.isNotEmpty(roleStr) ? CollectionUtil.newHashSet(StringUtils.split(roleStr, ",")) : Collections.EMPTY_SET,
                        request.getHeader(HttpHeaders.AUTHORIZATION)
                ));
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.clean();
    }

    @Override
    public int order() {
        return Ordered.LOWEST_PRECEDENCE-2;
    }
}
