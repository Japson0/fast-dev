/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.charts.table;

import java.io.Serializable;
import java.util.Collection;

/**
 * GridTable对象
 *
 * @param <T> the type parameter
 * @author Nick Lv
 * @created 2017 /7/7 11:20
 */
@Deprecated
public class GridData<T> implements Serializable {
    /**
     * 数据
     */
    private T value;
    /**
     * 列名
     */
    private Collection<GridTableInfo> column;

    public GridData() {
    }

    public GridData(T value, Collection<GridTableInfo> column) {
        this.value = value;
        this.column = column;
    }

    /**
     * Gets column.
     *
     * @return the column
     */
    public Collection<GridTableInfo> getColumn() {
        return column;
    }

    /**
     * Sets column.
     *
     * @param column the column
     */
    public void setColumn(Collection<GridTableInfo> column) {
        this.column = column;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
