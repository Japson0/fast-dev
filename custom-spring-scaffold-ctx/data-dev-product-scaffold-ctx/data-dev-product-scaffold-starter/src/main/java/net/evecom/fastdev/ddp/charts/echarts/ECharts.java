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
 * E对象
 *
 * @author Nick Lv
 * @created 2018 /1/2 9:52
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ECharts {

    /**
     * 大标题
     */
    String text() default "";

    /**
     * 小标题
     */
    String subText() default "";

    /**
     * 是否需要堆叠
     */
    boolean isStack() default false;

    /**
     * 堆叠名称
     */
    String stackName() default "默认分组";

    /**
     * 图类型
     * 此图类型为dataType为row，且一张图只有一个图
     */
    String graphType() default "";

    /**
     * 图例
     */
    String legendColumn() default "";

    /**
     * 值字段
     */
    String valueColumn() default "";

    /**
     * x轴对象
     */
    XAxis xAxis();

    /**
     * y轴对象
     */
    YAxis yAxis();

    /**
     * 是否需要原本的数据
     */
    boolean isNeedData() default false;


    /**
     * 数据类型
     * column/row
     */
    String dataType() default "";

    /**
     * 数据类型为column的详情
     */
    ColumnSeries[] columnSeries() default {};
}
