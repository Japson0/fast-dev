package com.nlecloud.spring.scaffold.debug;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年08月14日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class FeignDebugForward implements RequestInterceptor {


    private final String forwardAddr;

    public FeignDebugForward(String forwardAddr) {
        this.forwardAddr = forwardAddr;
    }

    @Override
    public void apply(RequestTemplate template) {
        String url = template.feignTarget().url();

        // 只处理 http://service-name/path 形式的请求
        if (url.startsWith("http://")) {

            // 提取服务名：http://user-service/api/list → user-service
            String serviceName = extractServiceName(url);

            if (serviceName != null && forwardAddr != null && !forwardAddr.trim().isEmpty()) {
                // 确保 targetIp 有协议
                String normalizedTarget = forwardAddr.startsWith("http") ? forwardAddr : "http://" + forwardAddr;

                // 构造新 URL：http://tarip/service-name/xxx
                String newPath = url.substring(("http://" + serviceName).length());
                String newUrl = normalizedTarget + "/" + serviceName + newPath;

                template.target(newUrl);
            }
        }
    }

    // 提取服务名
    private String extractServiceName(String url) {
        String hostPart = url.substring("http://".length());
        int slashIndex = hostPart.indexOf('/');
        if (slashIndex > 0) {
            return hostPart.substring(0, slashIndex);
        } else {
            return hostPart; // 没有路径的情况
        }
    }
}
