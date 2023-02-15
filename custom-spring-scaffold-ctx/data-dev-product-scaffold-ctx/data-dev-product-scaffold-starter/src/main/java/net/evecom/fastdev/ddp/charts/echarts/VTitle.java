/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.charts.echarts;

import java.io.Serializable;

/**
 * description
 *
 * @author Nick Lv
 * @created 2019 /5/22 16:28
 */
public class VTitle implements Serializable {
    /**
     * 标题
     */
    private String text;
    /**
     * 标题位置
     */
    private String textAlign = "center";

    /**
     * X
     */
    private String x;

    /**
     * Gets x.
     *
     * @return the x
     */
    public String getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets text align.
     *
     * @return the text align
     */
    public String getTextAlign() {
        return textAlign;
    }

    /**
     * Sets text align.
     *
     * @param textAlign the text align
     */
    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }
}
