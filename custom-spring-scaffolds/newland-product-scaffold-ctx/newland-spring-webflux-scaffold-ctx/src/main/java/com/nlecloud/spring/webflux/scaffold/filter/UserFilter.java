
package com.nlecloud.spring.webflux.scaffold.filter;

import com.necloud.spring.common.AuthConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
            Set<String> rolesSet= Collections.EMPTY_SET;
            String username = headers.getFirst(AuthConstants.USER_HEADER);
            String roles = headers.getFirst(AuthConstants.ROLE_HEADER);
            String schoolId = headers.getFirst(AuthConstants.SCHOOL_ID_HEADER);
            String tenantId = headers.getFirst(AuthConstants.TENANT_ID_HEADER);
            if(tenantId==null){
                //针对以前没有租户的，把学校当租户
                tenantId=schoolId;
            }
            if(!StringUtils.hasText(tenantId)){
                tenantId="0";  //TODO， 有些历史数据没学校，后面改完可以删掉
            }
            if(roles!=null) {
                String[] rolesSplit = roles.split(",");
                rolesSet = new HashSet<>(rolesSplit.length);
                for (String role : rolesSplit) {
                    rolesSet.add(role);
                }
            }
            return chain.filter(exchange).contextWrite(new UserWrapper(Long.valueOf(userId),username,Long.valueOf(tenantId),rolesSet).getContextView());
        }
        return chain.filter(exchange);
    }

}
