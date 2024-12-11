/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis.annotation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * <P><B>分页数据结构:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年05月22日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@ApiModel(value = "分页数据结构")
public class PageRequest<P> implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 5257379308853253918L;

    /**
     * 开始页从0开始
     */
    @ApiModelProperty(value = "当前页")
    @JsonSerialize(using = LongSerializer.class)

    private long page;

    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量", example = "20")
    @JsonProperty("size")
    @JsonSerialize(using = LongSerializer.class)

    private long size;
    /**
     * 实体类参数对象
     */
    @ApiModelProperty(value = "条件构造器", name = "condition")
    @JsonProperty(value = "queryParams", access = JsonProperty.Access.WRITE_ONLY)
    private P condition;

    /**
     * 是否需要加密
     */
    @JsonIgnore
    private boolean encrypt;

    /**
     * 扩展类，可存放一些额外信息，
     */
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected Map<String, Object> extend;

    /**
     * 展示的列名，为NUll则全展示
     */
    @JsonIgnore
    protected String[] columnNames;

    /**
     * 需要排除的列明，如果columnNames有数据，则此字段无效
     */
    @JsonIgnore
    protected String[] excludeColumnNames;
    /**
     * 排序信息
     */
    @JsonIgnore
    protected List<OrderInfo> orderInfos;

    /**
     * 是否需要查总数
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean searchCount = true;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public P getCondition() {
        return condition;
    }

    public void setCondition(P condition) {
        this.condition = condition;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public String[] getExcludeColumnNames() {
        return excludeColumnNames;
    }

    public void setExcludeColumnNames(String[] excludeColumnNames) {
        this.excludeColumnNames = excludeColumnNames;
    }

    public List<OrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

    public PageRequest<P> addOrdersFirst(OrderInfo... orderItems) {
        if (this.orderInfos == null) {
            this.addOrdersLast(orderItems);
        } else {
            List<OrderInfo> temp = new LinkedList();
            OrderInfo[] var3 = orderItems;
            int var4 = orderItems.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                OrderInfo orderItem = var3[var5];
                ((LinkedList) temp).addFirst(orderItem);
            }

            this.orderInfos = temp;
        }

        return this;
    }

    public PageRequest<P> addOrdersLast(OrderInfo... orderItems) {
        if (this.orderInfos == null) {
            this.orderInfos = new LinkedList();
        }

        this.orderInfos.addAll(Arrays.asList(orderItems));
        return this;
    }


    /**
     * 不分页查询，也就是直接SELECT * FROM TABLE 。没有分页信息
     * RevisionTrail:(Date/Author/Description)
     * 2021年12月06日
     *
     * @author Japson Huang
     */
    public PageRequest<P> noPageAndCount() {
        setSize(-1);
        return this;
    }

    /**
     * 只查总数，其他什么都不查
     * RevisionTrail:(Date/Author/Description)
     * 2021年12月14日
     *
     * @author Japson Huang
     */
    public PageRequest<P> onlySearchCount() {
        setPage(Long.MAX_VALUE);
        return this;
    }
}
