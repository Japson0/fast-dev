/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.elastic.pojo;

import org.elasticsearch.search.SearchHit;

/**
 * <P><B>Hit转换类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年12月02日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@FunctionalInterface
public interface ConverType<T> {

    T converType(SearchHit hit);
}
