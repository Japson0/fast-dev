/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.charts.table;

import java.io.Serializable;

/**
 * 列表信息实体类
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/24 16:55:22
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class GridTableInfo implements Serializable {
    /**
     * 中文名.
     */
    private String cnName;
    /**
     * 属性名
     */
    private String enName;
    /**
     * 样式类
     */
    private String styleClass;
    /**
     * 具体样式.
     */
    private String style;
    /**
     * 排列顺序
     */
    private Integer viewOrder;
    /**
     * 是否日期
     */
    private boolean notDate;
    /**
     * 是否可以被点击
     */
    private boolean canClick;
    /**
     * 所占列
     */
    private String colspan;
    /**
     * 所占行数
     */
    private Integer rowspan;
    /**
     * 日期格式化
     */
    private String dateFormat;
    /**
     * 宽度
     */
    private String width;

    /**
     * 是否字典表
     */
    private boolean dictionary;


    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Integer getViewOrder() {
        return viewOrder;
    }

    public void setViewOrder(Integer viewOrder) {
        this.viewOrder = viewOrder;
    }

    public boolean isNotDate() {
        return notDate;
    }

    public void setNotDate(boolean notDate) {
        this.notDate = notDate;
    }

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public String getColspan() {
        return colspan;
    }

    public void setColspan(String colspan) {
        this.colspan = colspan;
    }

    public Integer getRowspan() {
        return rowspan;
    }

    public void setRowspan(Integer rowspan) {
        this.rowspan = rowspan;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public boolean isDictionary() {
        return dictionary;
    }

    public void setDictionary(boolean dictionary) {
        this.dictionary = dictionary;
    }
}



