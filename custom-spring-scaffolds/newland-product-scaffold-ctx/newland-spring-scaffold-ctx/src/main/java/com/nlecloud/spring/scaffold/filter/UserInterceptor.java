package com.nlecloud.spring.scaffold.filter;

import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.common.AuthConstants;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Request user context filter.
 *
 * ScopedValue must surround the complete servlet chain so that structured
 * child tasks created while handling the request inherit the user context.
 */
@Order(-90)
public class UserInterceptor extends OncePerRequestFilter {

    public static final String DEBUG_USER_ATTRIBUTE =
            UserInterceptor.class.getName() + ".DEBUG_USER";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        UserInfo userInfo = (UserInfo) request.getAttribute(DEBUG_USER_ATTRIBUTE);
        if (userInfo == null) {
            userInfo = resolveUser(request);
        }

        if (userInfo == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            UserContext.runWith(userInfo, () -> {
                try {
                    filterChain.doFilter(request, response);
                } catch (IOException | ServletException e) {
                    throw new UserContextFilterException(e);
                }
            });
        } catch (UserContextFilterException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException ioException) {
                throw ioException;
            }
            if (cause instanceof ServletException servletException) {
                throw servletException;
            }
            throw e;
        }
    }

    private UserInfo resolveUser(HttpServletRequest request) {
        String userId = request.getHeader(AuthConstants.USER_ID_HEADER);
        if (StringUtils.isEmpty(userId)) {
            return null;
        }

        String currentId = request.getHeader(AuthConstants.CURRENT_TENANT_ID_HEADER);
        String[] tenantIds = StringUtils.split(
                request.getHeader(AuthConstants.TENANT_ID_HEADER), ",");
        String tenantId = selectTenant(tenantIds, currentId);
        String schoolId = request.getHeader(AuthConstants.SCHOOL_ID_HEADER);

        return new UserWrapper(
                Long.valueOf(userId),
                request.getHeader(AuthConstants.USER_HEADER),
                tenantId == null ? 0L : Long.valueOf(tenantId),
                StringUtils.isNotEmpty(schoolId) ? Long.valueOf(schoolId) : null,
                request.getHeader(HttpHeaders.AUTHORIZATION));
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

    private static final class UserContextFilterException extends RuntimeException {
        private UserContextFilterException(Throwable cause) {
            super(cause);
        }
    }
}
