
package com.nlecloud.spring.webflux.scaffold.filter;

import cn.hutool.core.collection.CollectionUtil;
import com.nlecloud.spring.common.AuthConstants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * <P><B>用户过滤器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2024年12月11日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();

        String userId = headers.getFirst(AuthConstants.USER_ID_HEADER);

        if( StringUtils.isNotEmpty(userId)) {
            String username = headers.getFirst(AuthConstants.USER_HEADER);
            String currentId = headers.getFirst(AuthConstants.CURRENT_TENANT_ID_HEADER);
            String roleStr = headers.getFirst(AuthConstants.ROLE_HEADER);
            String schoolId = headers.getFirst(AuthConstants.SCHOOL_ID_HEADER);

            String[] tenantIds = StringUtils.split(headers.getFirst(AuthConstants.TENANT_ID_HEADER), ",");

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
            Long currentTenantId = StringUtils.isNotEmpty(veryCurrentTenantId) ? Long.valueOf(veryCurrentTenantId) : null;
            Long currentSchoolId = StringUtils.isNotEmpty(schoolId) ? Long.valueOf(schoolId) : null;
            java.util.Collection<String> roles = StringUtils.isNotEmpty(roleStr) ? CollectionUtil.newHashSet(StringUtils.split(roleStr,",")) : Collections.EMPTY_SET;
            String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
            Long currentUserId = Long.valueOf(userId);
            return chain.filter(exchange).contextWrite(
                    new UserWrapper(currentUserId, username, currentTenantId, currentSchoolId, roles, token).getContextView()
            );
        }
        return chain.filter(exchange);
    }
}
