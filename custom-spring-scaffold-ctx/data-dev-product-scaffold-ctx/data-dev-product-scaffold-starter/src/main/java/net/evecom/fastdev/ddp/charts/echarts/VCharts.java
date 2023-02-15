/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.charts.echarts;

import java.io.Serializable;
import java.util.List;

/**
 * 图
 *
 * @author Nick Lv
 * @created 2017 /12/21 20:02
 */
public class VCharts implements Serializable {
    /**
     * 标题
     */
    private List<VTitle> title;
    /**
     * 图例
     */
    private List<String> legend;
    /**
     * x轴信息
     */
    private VxAxis xAxis;
    /**
     * y轴信息
     */
    private VyAxis yAxis;
    /**
     * 数据
     */
    private List<VSeries> series;
    /**
     * 数据库查询出来的数据
     */
    private Object data;
    /**
     * 用于雷达图中设置指标
     */
    private List<VIndicator> indicator;


    /**
     * Gets indicator.
     *
     * @return the indicator
     */
    public List<VIndicator> getIndicator() {
        return indicator;
    }

    /**
     * Sets indicator.
     *
     * @param indicator the indicator
     */
    public void setIndicator(List<VIndicator> indicator) {
        this.indicator = indicator;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public List<VTitle> getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(List<VTitle> title) {
        this.title = title;
    }

    /**
     * Gets legend.
     *
     * @return the legend
     */
    public List<String> getLegend() {
        return legend;
    }

    /**
     * Sets legend.
     *
     * @param legend the legend
     */
    public void setLegend(List<String> legend) {
        this.legend = legend;
    }

    /**
     * Gets axis.
     *
     * @return the axis
     */
    public VxAxis getxAxis() {
        return xAxis;
    }

    /**
     * Sets axis.
     *
     * @param xAxis the x axis
     */
    public void setxAxis(VxAxis xAxis) {
        this.xAxis = xAxis;
    }

    /**
     * Gets axis.
     *
     * @return the axis
     */
    public VyAxis getyAxis() {
        return yAxis;
    }

    /**
     * Sets axis.
     *
     * @param yAxis the y axis
     */
    public void setyAxis(VyAxis yAxis) {
        this.yAxis = yAxis;
    }

    /**
     * Gets series.
     *
     * @return the series
     */
    public List<VSeries> getSeries() {
        return series;
    }

    /**
     * Sets series.
     *
     * @param series the series
     */
    public void setSeries(List<VSeries> series) {
        this.series = series;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Object data) {
        this.data = data;
    }
}
