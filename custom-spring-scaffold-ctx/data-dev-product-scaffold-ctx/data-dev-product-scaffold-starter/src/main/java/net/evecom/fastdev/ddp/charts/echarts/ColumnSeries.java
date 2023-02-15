/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.charts.echarts;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 列式数据对应的注解
 *
 * @author Nick Lv
 * @created 2018 /1/2 16:01
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnSeries {
    /**
     * 中文名
     */
    String cnName() default "";

    /**
     * 值的列名
     */
    String valueColumn() default "";

    /**
     * 堆叠
     */
    String stackGroup() default "";

    /**
     * 用来与X轴对应分组的列名
     */
    String groupByColumn() default "";

    /**
     * 图的类型
     * pie-饼图 bar-柱状图 line-折线图 ring_pie-环形图
     */
    String type() default "";
}
