/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.elastic.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import net.evecom.elastic.annotations.ElasticQueryIndex;
import net.evecom.elastic.indexbuilder.ElasticIndexBuild;
import net.evecom.elastic.indexbuilder.ElasticQueryIndicesBuild;
import net.evecom.elastic.model.EPageRequest;
import net.evecom.elastic.pojo.EsBaseEntity;
import net.evecom.elastic.pojo.EsQueryWrapper;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilder;

import java.io.IOException;
import java.util.Map;

/**
 * <P><B>ElasticSearch通用搜索</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年05月10日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface ElasticSearch {

    /**
     * 获取分页数据
     * RevisionTrail:(Date/Author/Description)
     * 2020年08月05日
     *
     * @param esQueryWrapper Es查询类
     * @param request        分页类
     * @author Japson Huang
     */
    <T, R extends EsBaseEntity> EPageRequest<R> searchByObj(EsQueryWrapper<T> esQueryWrapper, EPageRequest<R> request, Class<R> responseType);

    /**
     * 获取分页数据
     * RevisionTrail:(Date/Author/Description)
     * 2020年08月05日
     *
     * @param esQueryWrapper           Es查询类
     * @param request                  分页类
     * @param elasticQueryIndicesBuild 索引处理器
     * @author Japson Huang
     */
    <T, R extends EsBaseEntity> EPageRequest<R> searchByObj(EsQueryWrapper<T> esQueryWrapper, EPageRequest<R> request,
                                                            ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild, Class<R> responseType);

    /**
     * 获取分页数据
     * RevisionTrail:(Date/Author/Description)
     * 2020年08月05日
     *
     * @param esQueryWrapper Es查询类
     * @param request        分页类
     * @author Japson Huang
     */
    <T> EPageRequest<Map<String, Object>> search2MapByObj(EsQueryWrapper<T> esQueryWrapper, EPageRequest<?> request);

    /**
     * 获取分页数据
     * RevisionTrail:(Date/Author/Description)
     * 2020年08月05日
     *
     * @param esQueryWrapper           Es查询类
     * @param request                  分页类
     * @param elasticQueryIndicesBuild 索引处理器
     * @author Japson Huang
     */
    <T> EPageRequest<Map<String, Object>> search2MapByObj(EsQueryWrapper<T> esQueryWrapper, EPageRequest<?> request,
                                                          ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild);

    /**
     * 根据实体类伤的@ElasticClass注解分析出索引，如果该索引名是别名，则会调用索引分隔器进行查询实际的索引
     * RevisionTrail:(Date/Author/Description)
     * 2019年12月02日
     *
     * @param esQueryWrapper    查询类
     * @param elasticQueryIndex see ElasticClass.class
     * @author Japson Huang
     */
    <T> String[] buildIndices(EsQueryWrapper<T> esQueryWrapper, ElasticQueryIndex elasticQueryIndex, ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild);

    /**
     * 搜索总数
     * RevisionTrail:(Date/Author/Description)
     * 2019年12月02日
     *
     * @author Japson Huang
     */
    Long countBySearchSource(Query query, String... indices);

    /**
     * 查询总数
     * RevisionTrail:(Date/Author/Description)
     * 2019年12月04日
     *
     * @author Japson Huang
     */
    <T> Long countByObj(EsQueryWrapper<T> esQueryWrapper, ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild);

    /**
     * 新增
     * RevisionTrail:(Date/Author/Description)
     * 2022年01月19日
     *
     * @author Japson Huang
     */
    <T extends EsBaseEntity> void insert(boolean refresh, T... object);

    /**
     * 新增
     * RevisionTrail:(Date/Author/Description)
     * 2022年01月19日
     *
     * @author Japson Huang
     */
    <T extends EsBaseEntity> void insert(boolean refresh, String index, T... object);

    /**
     * 新增
     * RevisionTrail:(Date/Author/Description)
     * 2022年01月19日
     *
     * @param indicesBuild 索引构造器
     * @author Japson Huang
     */
    <T extends EsBaseEntity> void insert(boolean refresh, ElasticIndexBuild<T> indicesBuild, T... object);

    /**
     * 更新数据
     * RevisionTrail:(Date/Author/Description)
     * 2022年01月19日
     *
     * @param refresh 是否马上刷新segment
     * @author Japson Huang
     */
    <T extends EsBaseEntity> void updateById(boolean refresh, T... object);

    /**
     * 更新数据
     * RevisionTrail:(Date/Author/Description)
     * 2022年01月19日
     *
     * @param refresh 是否马上刷新segment
     * @author Japson Huang
     */
    <T extends EsBaseEntity> void updateById(boolean refresh, String index, T... object);

    /**
     * 更新数据
     * RevisionTrail:(Date/Author/Description)
     * 2022年01月19日
     *
     * @param indexBuild 索引构造器
     * @author Japson Huang
     */
    <T extends EsBaseEntity> void updateById(boolean refresh, ElasticIndexBuild<T> indexBuild, T... object);

    /**
     * 根据查询更新数据
     * RevisionTrail:(Date/Author/Description)
     * 2022年01月19日
     *
     * @param refresh 是否马上刷新segment
     * @author Japson Huang
     */
    <T extends EsBaseEntity> void updateByQuery(boolean refresh, EsQueryWrapper<T> queryWrapper, T object);

    /**
     * 根据ID删除对应的索引数据
     * RevisionTrail:(Date/Author/Description)
     * 2022年01月19日
     *
     * @author Japson Huang
     */
    String[] deleteById(boolean refresh, String index, String... id);

    /**
     * 根据查询类删除信息
     * RevisionTrail:(Date/Author/Description)
     * 2022年01月19日
     *
     * @param queryWrapper 查询类
     * @param indices      索引列表，如果为空，则会调用queryWrapper上的注解的索引
     * @author Japson Huang
     */
    void deleteByQuery(boolean refresh, EsQueryWrapper<?> queryWrapper, String... indices);

    /**
     * 刷新一次index,
     * RevisionTrail:(Date/Author/Description)
     * 2022年01月19日
     *
     * @author Japson Huang
     */
    void refresh(String index);

    /**
     * 根据别名返回所有的
     * RevisionTrail:(Date/Author/Description)
     * 2019年12月09日
     *
     * @param alias 别名
     * @author Japson Huang
     */
    String[] getIndices(String alias);

    /**
     * 执行请求
     * RevisionTrail:(Date/Author/Description)
     * 2019年12月04日
     *
     * @author Japson Huang
     */
    SearchResponse executeSearchRequest(SearchRequest request) throws IOException;


    /**
     * 批量查询
     *
     * @param request
     * @return
     */
    MultiSearchResponse executeMultiSearchRequest(MultiSearchRequest request) throws IOException;

    /*
     * es 数据统计
     * Revision Trail: (Date/Author/Description)
     *                 2019/12/9 Roosevelt Luo CREATE
     * @author  Roosevelt Luo
     *
     * @params [obj 查询条件, elasticIndicesBuild build, aggs 统计脚本]
     * @return
     */
    <T> SearchResponse executeSearchRequest(
            EsQueryWrapper<T> esQueryWrapper,
            ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild, AggregationBuilder... aggs);

    /**
     * 根据ID去查询
     * Revision Trail: (Date/Author/Description)
     * 2019/12/27 Roosevelt Luo CREATE
     *
     * @return
     * @author Roosevelt Luo
     * @params [tClass, id]
     */
    <T> T searchByIds(Class<T> responseType, String id, String... indices);

    /**
     * 判断索引是否存在
     * RevisionTrail:(Date/Author/Description)
     * 2020年08月07日
     *
     * @author Japson Huang
     */
    boolean existsIndex(String index);

    /**
     * 获取client客户端
     * RevisionTrail:(Date/Author/Description)
     * 2022年04月02日
     *
     * @author Japson Huang
     */
    ElasticsearchClient client();
}
