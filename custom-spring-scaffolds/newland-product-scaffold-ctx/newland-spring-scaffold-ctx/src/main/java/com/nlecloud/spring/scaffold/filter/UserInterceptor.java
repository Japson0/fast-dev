
package com.nlecloud.spring.scaffold.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.URLUtil;
import com.nlecloud.spring.common.AuthConstants;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;

import java.util.*;

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
                        veryCurrentTenantId=veryCurrentTenantId==null?tenantIds[0]:veryCurrentTenantId;

                    }
                }
                UserContext.setUserInfo(new UserWrapper(Long.valueOf(userId),
                        username, veryCurrentTenantId == null ? 0L : Long.valueOf(veryCurrentTenantId),
                        StringUtils.isNotEmpty(schoolId) ? Long.valueOf(schoolId) : null,
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
