
package com.nlecloud.spring.webflux.scaffold.filter;

import com.nlecloud.spring.common.AuthConstants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

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

        UserWrapper userWrapper = resolveUser(headers);
        if (userWrapper != null) {
            return chain.filter(exchange).contextWrite(userWrapper.getContextView());
        }
        return chain.filter(exchange);
    }

    private UserWrapper resolveUser(HttpHeaders headers) {
        String userId = headers.getFirst(AuthConstants.USER_ID_HEADER);
        if (StringUtils.isEmpty(userId)) {
            return null;
        }

        String[] tenantIds = StringUtils.split(
                headers.getFirst(AuthConstants.TENANT_ID_HEADER), ",");
        String tenantId = selectTenant(
                tenantIds,
                headers.getFirst(AuthConstants.CURRENT_TENANT_ID_HEADER));

        Long currentTenantId = StringUtils.isNotEmpty(tenantId)
                ? Long.valueOf(tenantId) : null;
        String schoolId = headers.getFirst(AuthConstants.SCHOOL_ID_HEADER);

        return new UserWrapper(
                Long.valueOf(userId),
                headers.getFirst(AuthConstants.USER_HEADER),
                currentTenantId,
                StringUtils.isNotEmpty(schoolId) ? Long.valueOf(schoolId) : null,
                headers.getFirst(HttpHeaders.AUTHORIZATION));
    }

    private String selectTenant(String[] tenantIds, String currentId) {
        if (ArrayUtils.isEmpty(tenantIds) || tenantIds.length == 1
                || currentId == null) {
            return ArrayUtils.isEmpty(tenantIds) ? null : tenantIds[0];
        }
        for (String tenantId : tenantIds) {
            if (tenantId.equals(currentId)) {
                return tenantId;
            }
        }
        return tenantIds[0];
    }
}
