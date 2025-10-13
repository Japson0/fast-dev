
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

        if( userId!=null) {
            String username = headers.getFirst(AuthConstants.USER_HEADER);
            String currentId = headers.getFirst(AuthConstants.CURRENT_TENANT_ID_HEADER);
            String roleStr = headers.getFirst(AuthConstants.ROLE_HEADER);

            String[] tenantIds = org.apache.commons.lang3.StringUtils.split(headers.getFirst(AuthConstants.TENANT_ID_HEADER), ",");

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
            return chain.filter(exchange).contextWrite(new UserWrapper(Long.valueOf(userId),username,Long.valueOf(veryCurrentTenantId),
                    StringUtils.isNotEmpty(roleStr) ? CollectionUtil.newHashSet(StringUtils.split(roleStr,",")) :Collections.EMPTY_SET
            ).getContextView());
        }
        return chain.filter(exchange);
    }

}
