package com.nlecloud.spring.scaffold.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 审计日志封装对象
 *
 * @author warrior
 */
@Setter
@Getter
public class AuditLog {


    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 租户
     */
    private Long tenantId;
    /**
     * 操作信息
     */
    private String operation;
}
