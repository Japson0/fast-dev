/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import net.evecom.fastdev.mybatis.annotation.OrderInfo;
import net.evecom.fastdev.mybatis.annotation.PageConditionQuery;
import net.evecom.fastdev.mybatis.util.EncryptUtil;

import java.util.*;
import java.util.function.Predicate;


/**
 * <P><B>分页数据结构:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年05月22日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@ApiModel(value = "分页数据结构")
public class PageConditionDTO<P> extends PageConditionQuery<P> implements IPage {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 5257379308853253918L;

    /**
     * 空wrapper，无条件即是查询所有
     */
    @JsonIgnore
    private transient final static QueryWrapper NULL_WRAPPER = new QueryWrapper();

    /**
     * 是否需要加密
     */
    @JsonIgnore
    private boolean encrypt;

    /**
     * 条件构造器
     */
    @JsonIgnore
    private transient QueryWrapper queryWrapper;
    /**
     * 是否需要查总数
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean searchCount = true;

    /**
     * 查询总数的类名
     */
    @JsonIgnore
    private String countId;

    /**
     * 生成QueryWrapper， 如果ignoreAlias=true,则会忽略所有别名的信息
     * RevisionTrail:(Date/Author/Description)
     * 2021年09月09日
     *
     * @author Japson Huang
     */
    public QueryWrapper buildQueryWrapper(boolean ignoreAlias) {
        if (queryWrapper != null) {
            return queryWrapper;
        }
        if (this.condition != null) {
            if (encrypt) {
                EncryptUtil.encryptObject(this.condition);
            }
            if (this.condition instanceof BaseQuery) {
                this.queryWrapper = ((BaseQuery) this.condition).buildQueryWrapper(this.condition, ignoreAlias);
            } else {
                this.queryWrapper = BaseQuery.buildQueryWrapper(this.condition, ignoreAlias);
            }
            if (columnNames != null && columnNames.length > 0) {
                queryWrapper.select(columnNames);
            } else if (excludeColumnNames != null && excludeColumnNames.length > 0) {
                Set<String> set = new HashSet<>(Arrays.asList(excludeColumnNames));
                queryWrapper.select((Predicate<TableFieldInfo>) tableFieldInfo -> set.contains(tableFieldInfo.getProperty()));
            }
        }
        return queryWrapper == null ? NULL_WRAPPER : queryWrapper;
    }

    public void cleanWrapper() {
        this.queryWrapper = NULL_WRAPPER;
    }

    public QueryWrapper buildQueryWrapper() {
        return buildQueryWrapper(false);
    }

    @Override
    @JsonIgnore
    public List<OrderItem> orders() {
        if (this.orderInfos != null) {
            List<OrderItem> orderItems = new ArrayList<>(this.orderInfos.size());
            for (OrderInfo orderInfo : this.orderInfos) {
                orderItems.add(orderInfo.isAsc() ? OrderItem.asc(orderInfo.getColumn()) :
                        OrderItem.desc(orderInfo.getColumn()));
            }
            return orderItems;
        }
        return Collections.emptyList();
    }


    @Override
    @JsonIgnore
    public boolean searchCount() {
        return this.searchCount;
    }

    @Override
    @JsonIgnore
    public List getRecords() {
        return getpRecords();
    }

    @Override
    @JsonIgnore
    public IPage setRecords(List records) {
        setpRecords(records);
        return this;
    }

    @Override
    @JsonIgnore
    public long getTotal() {
        return getpTotal();
    }

    @Override
    @JsonIgnore
    public IPage setTotal(long total) {
        setpTotal(total);
        return this;
    }

    @Override
    @JsonIgnore
    public long getSize() {
        return getpSize();
    }

    @Override
    @JsonIgnore
    public IPage setSize(long size) {
        setpSize(size);
        return this;
    }

    @Override
    @JsonIgnore
    public long getCurrent() {
        return getpCurrent();
    }

    @Override
    @JsonIgnore
    public IPage setCurrent(long current) {
        setpCurrent(current);
        return this;
    }


    /**
     * 排序值靠前
     * RevisionTrail:(Date/Author/Description)
     * 2020年09月02日
     *
     * @author Japson Huang
     */
    @Override
    public PageConditionDTO<P> addOrdersFirst(OrderInfo... orderItems) {
        super.addOrdersFirst(orderItems);
        return this;
    }

    /**
     * 排序值靠后
     * RevisionTrail:(Date/Author/Description)
     * 2020年09月02日
     *
     * @author Japson Huang
     */
    @Override
    public PageConditionDTO<P> addOrdersLast(OrderInfo... orderItems) {
        super.addOrdersLast(orderItems);
        return this;
    }

    /**
     * 不分页查询，也就是直接SELECT * FROM TABLE 。没有分页信息
     * RevisionTrail:(Date/Author/Description)
     * 2021年12月06日
     *
     * @author Japson Huang
     */
    public PageConditionDTO<P> noPageAndCount() {
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
    public PageConditionDTO<P> onlySearchCount() {
        setCurrent(Long.MAX_VALUE);
        return this;
    }

    @Override
    public String countId() {
        return this.countId;
    }

    public PageConditionDTO<P> countId(String countId) {
        this.countId = countId;
        return this;
    }

    public PageConditionDTO() {
    }

    public PageConditionDTO(PageConditionQuery<P> pageConditionQuery) {
        this.pCurrent = pageConditionQuery.getpCurrent();
        this.pSize = pageConditionQuery.getpSize();
        this.condition = pageConditionQuery.getCondition();
        this.extend = pageConditionQuery.getExtend();
        this.orderInfos = pageConditionQuery.getOrderInfos();
        this.setpSearchCount(pageConditionQuery.ispSearchCount());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageConditionDTO<?> that = (PageConditionDTO<?>) o;
        return getCurrent() == that.getpCurrent() &&
                getSize() == that.getSize() &&
                Objects.equals(condition, that.condition) &&
                Objects.equals(orderInfos, that.orderInfos);
    }


    @Override
    public int hashCode() {
        return Objects.hash(condition, getCurrent(), getSize(), orderInfos);
    }
}
