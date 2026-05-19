package com.nledu.cloud.server.domain.dto;

import net.github.fastdev.mybatis.BaseQuery;
import net.github.fastdev.mybatis.annotation.Operation;
import net.github.fastdev.mybatis.annotation.QueryField;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年04月07日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserQuery extends BaseQuery {

    /**
     * 模糊查询名称
     */
    @QueryField(condition = Operation.LIKE)
    private String name;

    /**
     * 精确查询主键
     */
    @QueryField(condition = Operation.EQ)
    private String id;

    /**
     * 同一个key，多个字段精确查询主键
     */
    @QueryField({"name","address"})
    private String keyword;

    /**
     *
     */
    @QueryField(value = "myNo",condition = Operation.IN,alias="A")
    private String[] no;
}
