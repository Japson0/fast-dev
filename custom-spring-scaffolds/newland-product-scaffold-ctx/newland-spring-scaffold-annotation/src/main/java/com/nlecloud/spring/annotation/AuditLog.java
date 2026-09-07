package com.nlecloud.spring.annotation;

import java.lang.annotation.*;

/**
 * 自定义审计日志注解
 *
 * @author warrior
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditLog {

    /**
     * 操作信息
     */
    String operation();
}
