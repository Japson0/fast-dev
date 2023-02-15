package net.evecom.fastdev.ddp.enums;

import net.evecom.fastdev.common.model.ComEnum;

/**
 * <P><B>是否必填:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年11月24日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public enum Require implements ComEnum<Integer> {

    /**
     * 是否必填
     */
    YES("必填", 1), NO("非必填", 0);
    /**
     * 显示值
     */
    private final String display;

    /**
     * 实际值
     */
    private final Integer value;

    Require(String display, Integer value) {
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
