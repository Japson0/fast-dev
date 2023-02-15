/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.charts.echarts;

import java.io.Serializable;

/**
 * y轴
 *
 * @author Nick Lv
 * @created 2017 /12/21 20:14
 */
public class VyAxis implements Serializable {
    /**
     * 类型
     */
    private String type;
    /**
     * 格式化
     */
    private String formatter;

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets formatter.
     *
     * @return the formatter
     */
    public String getFormatter() {
        return formatter;
    }

    /**
     * Sets formatter.
     *
     * @param formatter the formatter
     */
    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
