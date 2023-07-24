/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis.annotation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

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
public class PageConditionQuery<P> {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 5257379308853253918L;

    /**
     * 开始页从0开始
     */
    @ApiModelProperty(value = "当前页")
    @JsonProperty("page")
    protected long pCurrent;

    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量", example = "20")
    @JsonProperty("size")
    protected long pSize;
    /**
     * 实体类参数对象
     */
    @ApiModelProperty(value = "条件构造器", name = "condition")
    @JsonProperty(value = "queryParams", access = JsonProperty.Access.WRITE_ONLY)
    protected P condition;

    /**
     * 总数
     */
    @ApiParam(hidden = true)
    @JsonProperty("total")
    protected long pTotal;
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
     * 记录
     */
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(hidden = true)
    @JsonProperty("records")
    protected List pRecords;

    /**
     * 是否需要查总数
     */
    @JsonProperty("searchCount")
    private boolean pSearchCount = true;

    public long getpCurrent() {
        return pCurrent;
    }

    public void setpCurrent(long pCurrent) {
        this.pCurrent = pCurrent;
    }

    public long getpSize() {
        return pSize;
    }

    public void setpSize(long pSize) {
        this.pSize = pSize;
    }

    public P getCondition() {
        return condition;
    }

    public void setCondition(P condition) {
        this.condition = condition;
    }

    public long getpTotal() {
        return pTotal;
    }

    public void setpTotal(long pTotal) {
        this.pTotal = pTotal;
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

    public List getpRecords() {
        return pRecords;
    }

    public void setpRecords(List pRecords) {
        this.pRecords = pRecords;
    }

    public boolean ispSearchCount() {
        return pSearchCount;
    }

    public void setpSearchCount(boolean pSearchCount) {
        this.pSearchCount = pSearchCount;
    }

    public PageConditionQuery<P> addOrdersFirst(OrderInfo... orderItems) {
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

    public PageConditionQuery<P> addOrdersLast(OrderInfo... orderItems) {
        if (this.orderInfos == null) {
            this.orderInfos = new LinkedList();
        }

        this.orderInfos.addAll(Arrays.asList(orderItems));
        return this;
    }

}
