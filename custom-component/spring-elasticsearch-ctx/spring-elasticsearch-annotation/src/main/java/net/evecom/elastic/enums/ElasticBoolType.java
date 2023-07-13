/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.elastic.enums;

/**
 * <P><B>Bool类型:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年12月03日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public enum ElasticBoolType {

    /**
     * 对应 AND
     */
    MUST,

    /**
     * 对应 OR
     */
    SHOULD,

    /**
     * 对应NOT IN()
     */
    MUST_NOT,

    /**
     * 类似 AND ( 1=1 OR 2=2)
     */
    MUST_SHOULD,

    /**
     * filter，不评分查询
     */
    FILTER;
}
