package net.evecom.fastdev.ddp.annotation;

/**
 * <P><B>分组表格信息:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月25日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Deprecated
public @interface GroupTableInfo {

    /**
     * 组别
     */
    Class<?> group();

    /**
     * 中文名.
     */
    String cnName() default "";

    /**
     * 属性名
     */
    String enName() default "";

    /**
     * 是否继承主属性样式
     */
    boolean extend() default true;

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

}
