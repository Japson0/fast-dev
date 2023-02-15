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
 * echarts x轴
 *
 * @author Nick Lv
 * @created 2018 /1/2 9:57
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface XAxis {
    /**
     * x轴类型
     */
    String type() default "category";

    /**
     * x轴数据对应的字段
     */
    String valueColumn() default "";

    /**
     * 最终体现在图上的内容
     * 1-week/2-month/3-date/4-other
     */
    int resultType() default 4;

    /**
     * 日期格式化成星期、月份、自定义格式
     */
    String dateFormatMethod() default "dateFormat";

    /**
     * 默认日期格式
     */
    String dateFormat() default "yyyy/MM/dd";

    /**
     * 数字格式化成星期或月份
     */
    String numberFormatMethod() default "numberFormat";

    /**
     * 数据
     */
    String[] data() default "";

    /**
     * 是否需要排序
     */
    boolean isNotSort() default true;
}
