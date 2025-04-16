/*
 * Copyright (c) 2005, 2024, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.nlecloud.spring.webflux.scaffold.filter;

import com.necloud.spring.common.AuthConstants;
import org.springframework.http.HttpHeaders;
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
        String username = headers.getFirst(AuthConstants.USER_HEADER);
        String roles = headers.getFirst(AuthConstants.ROLE_HEADER);

        if(userId!=null && username!=null) {
            Set<String> rolesSet= Collections.EMPTY_SET;
            if(roles!=null) {
                String[] rolesSplit = roles.split(",");
                rolesSet = new HashSet<>(rolesSplit.length);
                for (String role : rolesSplit) {
                    rolesSet.add(role);
                }
            }
            return chain.filter(exchange).contextWrite(new UserWrapper(Long.valueOf(userId),username,rolesSet).getContextView());
        }
        return chain.filter(exchange);
    }

}
