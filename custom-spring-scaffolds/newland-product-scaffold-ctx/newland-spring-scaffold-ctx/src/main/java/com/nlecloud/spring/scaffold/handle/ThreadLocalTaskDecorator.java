package com.nlecloud.spring.scaffold.handle;

import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.scaffold.common.UserContext;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

public class ThreadLocalTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        // 获取父线程的 ThreadLocal 值
        UserInfo userInfo = UserContext.getUserInfo();
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            Runnable task = () -> {
                if (contextMap != null) {
                    MDC.setContextMap(contextMap);
                }
                runnable.run();
            };
            try {
                if (userInfo == null) {
                    task.run();
                } else {
                    UserContext.runWith(userInfo, task);
                }
            } finally {
                MDC.clear();
            }
        };
    }
}
