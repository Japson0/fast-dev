package com.nlecloud.spring.scaffold.debug;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FullRequestLoggingInterceptor implements CustomInterceptor {

    private static final Logger log = LoggerFactory.getLogger(FullRequestLoggingInterceptor.class);
    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) throws UnsupportedEncodingException {
        // 仅在发生异常时记录请求信息

        if (ex != null) {
            log.error("Request failed - URL: {}, Method: {}, Headers: {}, Params: {}, Body: {}",
                    request.getRequestURI(),
                    request.getMethod(),
                    getHeaders(request),
                    getParams(request),
                    getBody(request)
            );
            log.error("Exception: ", ex); // 打印异常堆栈
        }
    }

    private String getHeaders(HttpServletRequest request) {
        // 获取所有请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder headers = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.append(headerName).append("=").append(request.getHeader(headerName));
            if (headerNames.hasMoreElements()) {
                headers.append(", ");
            }
        }
        return headers.toString();
    }

    private String getParams(HttpServletRequest request) {
        // 获取 Query 和 Form 参数
        Map<String, String[]> params = request.getParameterMap();
        return params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + Arrays.toString(entry.getValue()))
                .collect(Collectors.joining(", "));
    }

    private String getBody(HttpServletRequest request) throws UnsupportedEncodingException {
        // 获取 Body（仅适用于 ContentCachingRequestWrapper）
        if (request instanceof ContentCachingRequestWrapper) {
            byte[] bodyBytes = ((ContentCachingRequestWrapper) request).getContentAsByteArray();
            if (bodyBytes.length > 0) {
                return new String(bodyBytes, request.getCharacterEncoding());
            }
        }
        return "[empty or non-repeatable body]";
    }
}