/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import net.evecom.fastdev.mybatis.BaseQuery;
import net.evecom.fastdev.mybatis.annotation.OrderInfo;
import net.evecom.fastdev.mybatis.annotation.PageRequest;
import net.evecom.fastdev.mybatis.annotation.PageResponse;
import org.springframework.util.Assert;

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
public class PageWrapper<P,DTO>  extends Page<DTO> {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 5257379308853253918L;

    /**
     * 空wrapper，无条件即是查询所有
     */
    @JsonIgnore
    private  final static QueryWrapper NULL_WRAPPER = new QueryWrapper();


    /**
     * 条件构造器
     */
    @JsonIgnore
    private transient QueryWrapper queryWrapper;


    private final PageRequest<P> pageRequest;


    public PageWrapper(PageRequest<P> pageRequest) {
        super(pageRequest.getPage(),pageRequest.getSize(),pageRequest.isSearchCount());
        this.pageRequest = pageRequest;
    }

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
        P condition = pageRequest.getCondition();
        if (condition != null) {
            if (pageRequest.isEncrypt()) {
                EncryptUtil.encryptObject(condition);
            }
            if (condition instanceof BaseQuery) {
                this.queryWrapper = ((BaseQuery) condition).buildQueryWrapper(condition, ignoreAlias);
            } else {
                this.queryWrapper = BaseQuery.buildQueryWrapper(condition, ignoreAlias);
            }
            if (!ArrayUtils.isEmpty(pageRequest.getColumnNames())) {
                queryWrapper.select(pageRequest.getColumnNames());
            } else if (!ArrayUtils.isEmpty(pageRequest.getExcludeColumnNames())) {
                Set<String> set = new HashSet<>(Arrays.asList(pageRequest.getExcludeColumnNames()));
                queryWrapper.select((Predicate<TableFieldInfo>) tableFieldInfo -> set.contains(tableFieldInfo.getProperty()));
            }
        }
        return queryWrapper == null ? NULL_WRAPPER : queryWrapper;
    }


    public QueryWrapper buildQueryWrapper() {
        return buildQueryWrapper(false);
    }


    public void cleanWrapper() {
        this.queryWrapper = NULL_WRAPPER;
    }

    @Override
    @JsonIgnore
    public List<OrderItem> orders() {
        List<OrderInfo> orderInfos = this.pageRequest.getOrderInfos();
        if (orderInfos != null) {
            List<OrderItem> orderItems = new ArrayList<>(orderInfos.size());
            for (OrderInfo orderInfo : orderInfos) {
                orderItems.add(orderInfo.isAsc() ? OrderItem.asc(orderInfo.getColumn()) :
                        OrderItem.desc(orderInfo.getColumn()));
            }
            return orderItems;
        }
        return Collections.emptyList();
    }




    public PageResponse<P,DTO> getPageResponse(){
        return new PageResponse<>(pageRequest,this.getRecords(),this.getTotal());
    }

}
