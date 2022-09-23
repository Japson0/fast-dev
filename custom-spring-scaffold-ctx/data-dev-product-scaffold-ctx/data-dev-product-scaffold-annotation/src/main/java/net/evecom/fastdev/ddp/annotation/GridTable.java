/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 列表GridTable
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/26 10:08:53
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GridTable {

    /**
     * 中文
     *
     * @author Nick Lv
     * @created 2017 /05/26 10:08:53 Cn name string.
     */
    String cnName();

    /**
     * 属性名，为空则是字段名称
     *
     * @author Nick Lv
     * @created 2017 /05/26 10:08:53 En name string.
     */
    String enName() default "";

    /**
     * 样式
     *
     * @author Nick Lv
     * @created 2017 /05/26 10:08:53 Style class string.
     */
    String styleClass() default "";

    /**
     * 具体样式
     *
     * @author Nick Lv
     * @created 2017 /05/26 10:08:53 Style string.
     */
    String style() default "";

    /**
     * 排列顺序
     *
     * @author Nick Lv
     */
    int viewOrder() default -1;

    /**
     * 是否日期
     *
     * @author Nick Lv
     */
    boolean isNotDate() default false;

    /**
     * 日期格式化
     */
    String dateFormat() default "yyyy-MM-dd";

    /**
     * 是否可以点击，默认(不可点击)-0,可点击-1
     *
     * @author Nick Lv
     */
    boolean canClick() default false;

    /**
     * 所占行数
     *
     * @return
     */
    int rowSpan() default 1;

    /**
     * 所占列数
     */
    int colSpan() default 1;

    /**
     * 列宽
     */
    String width() default "100";

    /**
     * 是否是字典数据
     */
    boolean isDictionary() default false;

    /**
     * 复用类组别
     */
    GroupTableInfo[] group() default {};

}
