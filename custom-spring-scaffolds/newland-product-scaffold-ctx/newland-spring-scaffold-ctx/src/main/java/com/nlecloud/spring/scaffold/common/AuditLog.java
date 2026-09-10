package com.nlecloud.spring.scaffold.common;

import com.nlecloud.spring.annotation.enums.OperatorType;
import lombok.Getter;
import lombok.Setter;

/**
 * 审计日志封装对象
 *
 * @author warrior
 */
@Setter
@Getter
public class AuditLog {
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

    /**
     * 操作类型
     */
    private OperatorType operatorType;

    /**
     * 业务编码
     */
    private String businessCode;

    /**
     * 字段变更信息
     */
    private String changes;

    /**
     * 目标租户ID
     */
    private Long targetTenantId;
}
