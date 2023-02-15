/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.charts.echarts;

import java.io.Serializable;

/**
 * 用于雷达图，设置
 *
 * @author Nick Lv
 * @created 2019 /5/22 18:39
 */
public class VIndicator implements Serializable {
    /**
     * 名字
     */
    private String name;
    /**
     * 最大值
     */
    private Long max;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets max.
     *
     * @return the max
     */
    public Long getMax() {
        return max;
    }

    /**
     * Sets max.
     *
     * @param max the max
     */
    public void setMax(Long max) {
        this.max = max;
    }
}
