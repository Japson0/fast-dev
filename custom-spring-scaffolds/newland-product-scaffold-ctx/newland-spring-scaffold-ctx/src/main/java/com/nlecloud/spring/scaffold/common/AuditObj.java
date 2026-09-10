package com.nlecloud.spring.scaffold.common;

import com.nlecloud.spring.annotation.enums.OperatorType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;


/**
 * <P><B>审计对象:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年09月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Builder
@Getter
public final class AuditObj<T,R>{


    private String operatorValue;


    @Builder.Default
    private OperatorType operatorType = OperatorType.UPDATE;

    private T source;

    private T target;


    /**
     * 目标租户ID
     */
    @NotNull
    private Long targetTenantId;


    private R result;
}
