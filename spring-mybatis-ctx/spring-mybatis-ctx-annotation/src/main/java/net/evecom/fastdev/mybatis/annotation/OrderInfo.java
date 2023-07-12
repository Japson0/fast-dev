package net.evecom.fastdev.mybatis.annotation;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <P><B>排序信息:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月22日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class OrderInfo implements Serializable {

    /**
     * 字段名称
     */
    private String column;
    /**
     * 排序
     */
    private boolean asc;

    public OrderInfo() {
    }

    private OrderInfo(String column, boolean asc) {
        this.column = column;
        this.asc = asc;
    }

    public static OrderInfo asc(String column) {
        return new OrderInfo(column, true);
    }

    public static OrderInfo desc(String column) {
        return new OrderInfo(column, false);
    }

    public static List<OrderInfo> ascs(String... columns) {
        return Arrays.stream(columns).map(OrderInfo::asc).collect(Collectors.toList());
    }

    public static List<OrderInfo> descs(String... columns) {
        return Arrays.stream(columns).map(OrderInfo::desc).collect(Collectors.toList());
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }
}
