/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.elastic.indexbuilder;

import net.evecom.elastic.pojo.EsQueryWrapper;

/**
 * <P><B>查询索引构建器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年05月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@FunctionalInterface
public interface ElasticQueryIndicesBuild<T> {

    /**
     * 索引拆分器，查询时候会将实体类和索引数组传入出去，返回一个需要进行查询的实际索引数组
     * RevisionTrail:(Date/Author/Description)
     * 2020年09月25日
     *
     * @param esQueryWrapper 查询类
     * @param alias          别名
     * @param indices        索引数组
     * @return 索引数组
     * @author Japson Huang
     */
    String[] buildIndices(EsQueryWrapper<T> esQueryWrapper, String alias, String[] indices);

}
