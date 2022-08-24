/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis.annotation;

/**
 * <P><B>条件操作符枚举，配合@Condition使用:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月07日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public enum ConditionOperation {

    /**
     * 操作符
     */
    EQ, LIKE, RIGHT_LIKE, LEFT_LIKE, NOT_EQ, IN, NOT_IN, GT, GTE, LT, LTE
}
