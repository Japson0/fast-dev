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
            try {
                UserContext.setUserInfo(userInfo);
                if (contextMap != null) {
                    MDC.setContextMap(contextMap);
                }

                // 将上下文设置到子线程
                runnable.run();
            } finally {
                // 清理避免内存泄漏
                UserContext.clean();
                MDC.clear();
            }
        };
    }
}