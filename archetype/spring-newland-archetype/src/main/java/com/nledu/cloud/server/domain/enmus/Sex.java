package com.nledu.cloud.server.domain.enmus;

import net.github.fastdev.common.model.ComEnum;

/**
 * <P><B>性别枚举:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月02日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public enum Sex implements ComEnum<Integer> {

    MAIL("男性",0),FE_MALI("女性",1);
    /**
     * 显示值
     */
    private final String display;

    /**
     * 实际值
     */
    private final Integer value;

    Sex(String display, Integer value) {
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
