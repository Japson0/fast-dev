
package com.nlecloud.spring.scaffold.filter;

import com.nlecloud.spring.common.AuthConstants;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserWrapper;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        String userId = request.getHeader(AuthConstants.USER_ID_HEADER);

        if(userId!=null) {
            Set<String> rolesSet=Collections.EMPTY_SET;
            String username = request.getHeader(AuthConstants.USER_HEADER);
            String roles = request.getHeader(AuthConstants.ROLE_HEADER);
            String schoolId = request.getHeader(AuthConstants.SCHOOL_ID_HEADER);
            String tenantId = request.getHeader(AuthConstants.TENANT_ID_HEADER);
            if(tenantId==null){
                //针对以前没租户的，把学校当租户
                tenantId=schoolId;
            }
            if(!StringUtils.hasText(tenantId)){
                //TODO， 有些历史数据没学校，后面改完可以删掉
                tenantId="0";
            }
            if(roles!=null) {
                String[] rolesSplit = roles.split(",");
                rolesSet = new HashSet<>(rolesSplit.length);
                for (String role : rolesSplit) {
                    rolesSet.add(role);
                }
            }
            UserContext.setUserInfo(new UserWrapper(Long.valueOf(userId),username, Long.valueOf(tenantId),rolesSet));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.clean();
    }

}
