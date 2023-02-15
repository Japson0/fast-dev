/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.charts.echarts;

import java.io.Serializable;
import java.util.List;

/**
 * X轴
 *
 * @author Nick Lv
 * @created 2017 /12/21 20:09
 */
public class VxAxis implements Serializable {
    /**
     * 类型
     */
    private String type;
    /**
     * 数据
     */
    private List<String> data;

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
     * Gets data.
     *
     * @return the data
     */
    public List<String> getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(List<String> data) {
        this.data = data;
    }
}
