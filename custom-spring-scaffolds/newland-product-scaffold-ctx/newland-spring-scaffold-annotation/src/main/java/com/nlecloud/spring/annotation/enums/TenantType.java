package com.nlecloud.spring.annotation.enums;

import net.github.fastdev.common.model.ComEnum;

/**
 * <P><B>租户类型:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年08月14日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public enum TenantType implements ComEnum<Integer> {


    SCHOOL("学校租户", 0),

    ORGANIZATION("组织租户", 1),
    PERSON("个人租户", 2);

    /**
     * 显示值
     */
    private final String display;

    /**
     * 实际值
     */
    private final Integer value;

    TenantType(String display, Integer value) {
        this.display = display;
        this.value = value;
    }

    @Override
    public String getDisplay() {
        return display;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
