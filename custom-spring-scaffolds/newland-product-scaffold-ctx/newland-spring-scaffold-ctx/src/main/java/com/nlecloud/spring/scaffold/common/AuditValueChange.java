package com.nlecloud.spring.scaffold.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 审计字段变更信息
 *
 * @author warrior
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditValueChange {

    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 修改前的值
     */
    private String source;

    /**
     * 修改后的值
     */
    private String target;
}
