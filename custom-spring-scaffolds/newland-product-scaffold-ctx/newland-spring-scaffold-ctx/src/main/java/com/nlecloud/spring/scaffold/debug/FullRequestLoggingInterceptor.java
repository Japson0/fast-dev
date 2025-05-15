package com.nlecloud.spring.scaffold.debug;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.github.fastdev.boot.handle.CustomInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class FullRequestLoggingInterceptor implements CustomInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(FullRequestLoggingInterceptor.class);

    private boolean shouldLogBody(HttpServletRequest request) {
        return "POST".equalsIgnoreCase(request.getMethod())
            || "PUT".equalsIgnoreCase(request.getMethod());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws IOException {
        // 包装请求以支持多次读取body
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);

        Map<String, Object> requestDetails = new LinkedHashMap<>();

        // 基础信息
        requestDetails.put("method", wrappedRequest.getMethod());
        requestDetails.put("uri", wrappedRequest.getRequestURI());
        requestDetails.put("query", wrappedRequest.getQueryString());

        // 请求头
        Map<String, String> headers = new LinkedHashMap<>();
        Enumeration<String> headerNames = wrappedRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            headers.put(header, wrappedRequest.getHeader(header));
        }
        requestDetails.put("headers", headers);

        // 请求参数
        requestDetails.put("parameters", wrappedRequest.getParameterMap());

        // 请求体（仅对需要body的方法处理）
        if (shouldLogBody(wrappedRequest)) {
            requestDetails.put("body", readRequestBody(wrappedRequest));
        }

        logger.info("Full request details:\n{}",
                new ObjectMapper().writerWithDefaultPrettyPrinter()
                        .writeValueAsString(requestDetails));

        logger.info("Full request details:\n{}",
                new ObjectMapper().writerWithDefaultPrettyPrinter()
                        .writeValueAsString(requestDetails));
    }

    private String readRequestBody(ContentCachingRequestWrapper requestWrapper) throws IOException {
        int contentLength = requestWrapper.getContentLength();
        if (contentLength <= 0) return "";

        byte[] buf = requestWrapper.getContentAsByteArray();
        if (buf.length > 0) {
            return new String(buf, 0, buf.length, StandardCharsets.UTF_8);
        }
        return "";
    }
}