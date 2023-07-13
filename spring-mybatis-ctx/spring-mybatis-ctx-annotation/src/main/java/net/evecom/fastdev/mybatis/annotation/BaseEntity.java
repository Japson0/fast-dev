/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis.annotation;

import java.io.Serializable;

/**
 * <P><B>基类标识:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月20日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public interface BaseEntity<T extends Serializable> extends Serializable {

    /**
     * 获取ID
     * RevisionTrail:(Date/Author/Description)
     * 2021年12月03日
     *
     * @author Japson Huang
     */
    T getId();

    /**
     * 设置ID
     * RevisionTrail:(Date/Author/Description)
     * 2021年12月03日
     *
     * @author Japson Huang
     */
    void setId(T t);
}
