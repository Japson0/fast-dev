package com.nlecloud.spring.annotation;

import com.nlecloud.spring.annotation.enums.TenantType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年08月14日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@SuperBuilder
@Getter
@Jacksonized
public class TenantInfo {

    /**
     * 租户ID
     */
    private Long id;

    /**
     * 是否管理员
     */
    private boolean admin;

    /**
     * 0-学校，1-机构，2-个人
     */
    private TenantType type;
}
