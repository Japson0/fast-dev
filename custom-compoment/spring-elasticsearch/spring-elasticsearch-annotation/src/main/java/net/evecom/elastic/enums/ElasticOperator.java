/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.elastic.enums;

/**
 * <P><B>Elastic操作符:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年05月24日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public enum ElasticOperator {

    /**
     * 精确匹配
     */
    TERM,
    /**
     * 精确匹配多字段
     */
    TERMS,
    /**
     * 全文检索
     */
    MATCH,
    /**
     * 短语匹配
     */
    MATCH_PHRASE,

    /**
     * 前缀短语匹配
     */
    MATCH_PHRASE_PREFIX,

    /**
     * 前缀查询，类比于 Like  3500%
     */
    PREFIX,

    /**
     * 二进制查询
     */
    BITAND,

    /**
     * 同比如 like '3500%'
     */
    @Deprecated
    RLIKE,

    /**
     * 搜索 尽量别用
     */
    LIKE,

    /**
     * 大于
     */
    GT,
    /**
     * 小于
     */
    LT,
    /**
     * 大等于
     */
    GTE,
    /**
     * 小等于
     */
    LTE,
    /**
     * 存在该字段
     */
    EXISTS
}
