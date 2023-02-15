package net.evecom.fastdev.ddp.enums;

import net.evecom.fastdev.common.model.ComEnum;

/**
 * <P><B>启用状态:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年11月18日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public enum Enable implements ComEnum<Integer> {

    /**
     * 是否启用
     */
    ENABLE("启用", 1), UN_ENABLE("禁用", 0);
    /**
     * 显示值
     */
    private final String display;

    /**
     * 实际值
     */
    private final Integer value;

    Enable(String display, Integer value) {
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
