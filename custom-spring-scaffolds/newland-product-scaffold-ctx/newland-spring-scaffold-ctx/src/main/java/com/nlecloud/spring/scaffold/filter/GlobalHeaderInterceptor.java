package com.nlecloud.spring.scaffold.filter;

import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.common.AuthConstants;
import com.nlecloud.spring.scaffold.common.UserContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * <P><B>用户通用拦截器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年05月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class GlobalHeaderInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        UserInfo userInfo = UserContext.getUserInfo();
        if (userInfo != null) {
            HttpHeaders headers = request.getHeaders();
            headers.set(AuthConstants.USER_ID_HEADER, userInfo.getUserId().toString());
            headers.set(AuthConstants.ROLE_HEADER, String.join(",", userInfo.getRoles()));
            headers.set(AuthConstants.USER_HEADER, userInfo.getUsername());
            if (userInfo.getSchoolId() != null) {
                headers.set(AuthConstants.SCHOOL_ID_HEADER, userInfo.getSchoolId().toString());
            }
            if (userInfo.getTenantId() != null) {
                headers.set(AuthConstants.TENANT_ID_HEADER, userInfo.getTenantId().toString());
            }
        }
        return execution.execute(request, body);
    }
}
