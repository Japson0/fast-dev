/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.elastic.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.*;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.IdsQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.elasticsearch.core.bulk.DeleteOperation;
import co.elastic.clients.elasticsearch.core.search.*;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.elasticsearch.indices.GetAliasRequest;
import co.elastic.clients.elasticsearch.indices.GetAliasResponse;
import co.elastic.clients.elasticsearch.indices.get_alias.IndexAliases;
import net.evecom.elastic.annotations.ElasticIndex;
import net.evecom.elastic.annotations.ElasticQueryIndex;
import net.evecom.elastic.enums.OrderType;
import net.evecom.elastic.enums.TotalHitRelation;
import net.evecom.elastic.exception.ElasticException;
import net.evecom.elastic.indexbuilder.ElasticIndexBuild;
import net.evecom.elastic.indexbuilder.ElasticQueryIndicesBuild;
import net.evecom.elastic.model.EPageRequest;
import net.evecom.elastic.model.ESort;
import net.evecom.elastic.pojo.EsBaseEntity;
import net.evecom.elastic.pojo.EsQueryWrapper;
import net.evecom.elastic.service.ElasticSearch;
import net.evecom.fastdev.elastics.utils.ESConverUtils;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.ActionNotFoundTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * <P><B>ElasticSearch服务:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年05月08日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ElasticSearchImpl implements ElasticSearch {


    /**
     * 日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(ElasticSearchImpl.class);

    private static final BiConsumer<BulkResponse, EsBaseEntity[]> ID_HANDLE = (bulkItemResponses, object) -> {
        BulkItemResponse[] items = bulkItemResponses.getItems();
        for (int i = 0; i < items.length; i++) {
            if (items[i].status() == RestStatus.OK) {
                object[i].setId(items[i].getId());
            }
        }
    };
    /**
     * 客户端
     */
    private final ElasticsearchClient client;


    public ElasticSearchImpl(ElasticsearchClient client) {
        this.client = client;
    }

    @Override
    public <T, R extends EsBaseEntity> EPageRequest<R> searchByObj(EsQueryWrapper<T> esQueryWrapper, EPageRequest<R> request,
                                                                   Class<R> responseType) {
        return this.searchByObj(esQueryWrapper, request, null, responseType);
    }

    @Override
    public <T, R extends EsBaseEntity> EPageRequest<R> searchByObj(EsQueryWrapper<T> esQueryWrapper, EPageRequest<R> request,
                                                                   ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild, Class<R> responseType) {

        String[] indices = this.buildIndices(esQueryWrapper, esQueryWrapper.getElasticClass(), elasticQueryIndicesBuild);

        return this.buildResponse(esQueryWrapper.buildSearchSourceBuilder(), esQueryWrapper.getHighlightBuilder(),
                indices, esQueryWrapper.getSource(), request, responseType);
    }

    @Override
    public <T> EPageRequest<Map<String, Object>> search2MapByObj(EsQueryWrapper<T> esQueryWrapper, EPageRequest request) {
        return this.search2MapByObj(esQueryWrapper, request, null);
    }

    @Override
    public <T> EPageRequest<Map<String, Object>> search2MapByObj(EsQueryWrapper<T> esQueryWrapper, EPageRequest request,
                                                                 ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild) {

        String[] indices = this.buildIndices(esQueryWrapper, esQueryWrapper.getElasticClass(), elasticQueryIndicesBuild);

        return this.buildResponse(esQueryWrapper.buildSearchSourceBuilder(), esQueryWrapper.getHighlightBuilder(), indices, esQueryWrapper.getSource(), request, Map.class);
    }


    @Override
    public Long countBySearchSource(QueryBuilder queryBuilder, String... indices) {

        CountRequest countRequest = new CountRequest(indices, queryBuilder);
        try {
            CountResponse count = client.count(countRequest, RequestOptions.DEFAULT);
            return count.getCount();
        } catch (IOException e) {
            return 0L;
        }
    }

    @Override
    public <T> Long countByObj(EsQueryWrapper<T> esQueryWrapper, ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild) {

        String[] indices = this.buildIndices(esQueryWrapper, esQueryWrapper.getElasticClass(), elasticQueryIndicesBuild);
        return this.countBySearchSource(esQueryWrapper.buildSearchSourceBuilder(), indices);
    }


    @Override
    public <T extends EsBaseEntity> void insert(boolean refresh, T... object) {
        Assert.notEmpty(object, "数组不允许为空");
        insert(refresh, getIndex(object[0]), object);
    }

    public <T extends EsBaseEntity> void insert(boolean refresh, String index, T... object) {
        Assert.notEmpty(object, "数组不允许为空");
        insert(true, (o, alas, indices) -> index, object);
    }

    public <T extends EsBaseEntity> void insert(boolean refresh, ElasticIndexBuild<T> indicesBuild, T... object) {
        Assert.notEmpty(object, "数组不允许为空");
        try {
            String alias = getIndex(object[0]);
            String[] indices = indicesBuild.isAlias() ? getIndexByAlias(alias) : new String[]{alias};
            if (object.length > 1) {
                BulkRequest bulkRequest = new BulkRequest();
                bulkRequest.setRefreshPolicy(refresh ? WriteRequest.RefreshPolicy.IMMEDIATE :
                        WriteRequest.RefreshPolicy.NONE);
                for (T t : object) {
                    IndexRequest request = new IndexRequest(indicesBuild.buildIndices(t, alias, indices));
                    request.create(true);
                    request.id(t.getId());
                    request.source(ESConverUtils.trans2Json(t), XContentType.JSON);
                    bulkRequest.add(request);
                }
                BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
                ID_HANDLE.accept(bulk, object);
            } else {
                IndexRequest request = new IndexRequest(indicesBuild.buildIndices(object[0], alias, indices));
                request.create(true);
                request.id(object[0].getId());
                request.setRefreshPolicy(refresh ? WriteRequest.RefreshPolicy.IMMEDIATE :
                        WriteRequest.RefreshPolicy.NONE);
                request.source(ESConverUtils.trans2Json(object[0]), XContentType.JSON);
                IndexResponse response = client.index(request, RequestOptions.DEFAULT);
                RestStatus status = response.status();
                if (status != RestStatus.CREATED) {
                    throw new ActionNotFoundTransportException(response.toString());
                }
                object[0].setId(response.getId());
            }
        } catch (Exception e) {
            throw new ElasticsearchException("ES插入失败:{}", e.getMessage());
        }
    }


    @Override
    public <T extends EsBaseEntity> void updateById(boolean refresh, T... object) {
        updateById(refresh, getIndex(object[0]), object);
    }

    public <T extends EsBaseEntity> void updateById(boolean refresh, String index, T... object) {
        Assert.notEmpty(object, "数组不允许为空");
        updateById(refresh, (o, a1, a2) -> index, object);
    }

    public <T extends EsBaseEntity> String[] updateById(boolean refresh, ElasticIndexBuild<T> indexBuild, T... object) {
        Assert.notEmpty(object, "数组不允许为空");
        try {
            String alias = getIndex(object[0]);
            String[] indices = indexBuild.isAlias() ? getIndexByAlias(alias) : new String[]{alias};
            if (object.length > 1) {
                List<BulkOperation> bulkOperations = new ArrayList<>(object.length);
                for (T t : object) {
                    bulkOperations.add(new BulkOperation.Builder()
                            .update(v -> v.action(u -> u.doc(t)).id(t.getId())).build());
                }
                BulkRequest bulkRequest = new BulkRequest.Builder()
                        .refresh(refresh ? Refresh.True : Refresh.False)
                        .operations(bulkOperations)
                        .build();
                return successId(client.bulk(bulkRequest), null);
            } else {
                new UpdateRequest.Builder<T, T>().doc(object[0])
                UpdateRequest updateRequest = new UpdateRequest(indexBuild.buildIndices(object[0], alias, indices), object[0].getId());
                updateRequest.doc(ESConverUtils.trans2Json(object[0]), XContentType.JSON);
                updateRequest.setRefreshPolicy(refresh ? WriteRequest.RefreshPolicy.IMMEDIATE :
                        WriteRequest.RefreshPolicy.NONE);
                UpdateResponse response = client.update(updateRequest, RequestOptions.DEFAULT);
                if (response.status() != RestStatus.OK) {
                    throw new ActionNotFoundTransportException(response.toString());
                }
            }

        } catch (Exception e) {
            throw new ElasticsearchException("ES更新数据失败:{}", e.getMessage());
        }
    }


    @Override
    public <T extends EsBaseEntity> void updateByQuery(boolean refresh, EsQueryWrapper queryWrapper, T object) {
        throw new UnsupportedOperationException("暂不支持，感觉没必要");
    }


    @Override
    public String[] deleteById(boolean refresh, String index, String... id) {
        Assert.notNull(index, "index不允许为空");
        Assert.notNull(id, "id不允许为空");
        try {
            if (id.length > 1) {
                List<BulkOperation> deleteOperations = new ArrayList<>(id.length);
                for (String i : id) {
                    deleteOperations.add(new BulkOperation.Builder().delete(
                            new DeleteOperation.Builder().id(i).build())
                            .build());
                }
                BulkRequest bulkRequest = new BulkRequest.Builder()
                        .index(index)
                        .operations(deleteOperations)
                        .refresh(refresh ? Refresh.True : Refresh.False)
                        .build();
                return successId(client.bulk(bulkRequest), id);
            } else {
                DeleteRequest deleteRequest = new DeleteRequest.Builder()
                        .id(id[0])
                        .index(index)
                        .refresh(refresh ? Refresh.True : Refresh.False)
                        .build();
                DeleteResponse delete = client.delete(deleteRequest);
                return id;
            }
        } catch (Exception e) {
            throw new ElasticException("ES删除数据失败", e);
        }
    }

    public void deleteByQuery(boolean refresh, EsQueryWrapper<?> queryWrapper, String... indices) {
        if (indices.length == 0) {
            String index = queryWrapper.getElasticClass().index();
            Assert.notNull(index, "不存在索引信息");
        }
        DeleteByQueryRequest deleteRequest = new DeleteByQueryRequest.Builder()
                .refresh(Boolean.TRUE)
                .query(queryWrapper.buildSearchSourceBuilder())
                .build();
        try {
            client.deleteByQuery(deleteRequest);
        } catch (Exception e) {
            throw new ElasticException("ES删除数据失败:{}", e);
        }
    }


    public void refresh(String index) {
        IndexRequest<Object> request = new IndexRequest.Builder<>().index(index)
                .refresh(Refresh.True).build();
        try {
            client.index(request);
        } catch (Exception e) {
            throw new ElasticException("索引刷新失败:{}", e);
        }
    }


    @Override
    public SearchResponse executeSearchRequest(SearchRequest request) throws IOException {
        return client.search(request, RequestOptions.DEFAULT);
    }

    public MultiSearchResponse executeMultiSearchRequest(MultiSearchRequest request) throws IOException {
        return client.msearch(request, RequestOptions.DEFAULT);
    }

    @Override
    public <T> SearchResponse executeSearchRequest(
            EsQueryWrapper<T> esQueryWrapper,
            ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild, Aggregation... aggs) {
        String[] indices = this.buildIndices(esQueryWrapper, esQueryWrapper.getElasticClass(), elasticQueryIndicesBuild);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indices);
        QueryBuilder queryBuilder = esQueryWrapper.buildSearchSourceBuilder();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(0);
        for (AggregationBuilder agg : aggs) {
            searchSourceBuilder.aggregation(agg);
        }
        searchRequest.source(searchSourceBuilder);
        SearchResponse response;

        try {
            response = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (Exception ex) {
            return null;
        }
        return response;
    }

    @Override
    public <T> T searchByIds(Class<T> responseType, String id, String... indices) {
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index(Arrays.asList(indices))
                .query(new Query(new IdsQuery.Builder().values(id).build()))
                .build();
        try {
            SearchResponse<T> response = client.search(searchRequest, responseType);
            List<Hit<T>> hits = response.hits().hits();
            if (hits.size() == 1) {
                return hits.get(0).source();
            }
            return null;
        } catch (Exception e) {
            throw new ElasticException("查询数据失败", e);
        }
    }

    @Override
    public boolean existsIndex(String index) {
        try {
            return client.indices().exists(new ExistsRequest.Builder().index(index).build()).value();
        } catch (IOException e) {
            throw new ElasticException("查询ES索引是否存在失败", e);
        }
    }


    @Override
    public <T> String[] buildIndices(EsQueryWrapper<T> esQueryWrapper, ElasticQueryIndex elasticQueryIndex, ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild) {
        Assert.notNull(esQueryWrapper, "Search obj must be not null!");
        Assert.notNull(elasticQueryIndex, "Search obj must has @ElasticQueryIndex!");
        String index = elasticQueryIndex.index();
        String[] indexs = null;
        if (elasticQueryIndex.isAlias()) {
            indexs = this.getIndices(index);
        }
        if (elasticQueryIndicesBuild != null) {
            indexs = elasticQueryIndicesBuild.buildIndices(esQueryWrapper, index, indexs);
        }
        if (indexs == null) {
            indexs = new String[]{index};
        }
        return indexs;
    }


    public String[] getIndices(String alia) {

        ElasticsearchIndicesClient indices = client.indices();
        GetAliasRequest request = new GetAliasRequest.Builder().index(alia).build();
        try {
            GetAliasResponse alias = indices.getAlias(request);
            IndexAliases indexAliases = alias.get(alia);
            return indexAliases.aliases().keySet().toArray(new String[0]);
        } catch (Exception e) {
            throw new ElasticException("查询索引别名失败", e);
        }
    }

    private String[] getIndexByAlias(String alias) {
        return getIndices(alias);
    }

    private <T extends EsBaseEntity> String getIndex(T object) {
        Assert.notNull(object, "object must not be null!");
        ElasticIndex annotation = object.getClass().getAnnotation(ElasticIndex.class);
        Assert.notNull(annotation, object.getClass().getName() + "不存在索引信息");
        return annotation.value();
    }

    private <R> EPageRequest<R> buildResponse(
            Query query,
            Highlight highlight,
            String[] indices,
            String[] columns,
            EPageRequest<R> request,
            Class<R> responseType) {
        if (request.getSize() <= 0) {
            request.setRecords(Collections.emptyList());
            return request;
        }


        long total = -1;
        if (request.isSearchCount()) {
            total = countBySearchSource(query, indices);
            request.setTotal(total);
            if (total == 0) {
                request.setRecords(Collections.emptyList());
                return request;
            }
        }
        SearchRequest.Builder searchRequest = new SearchRequest.Builder()
                .index(Arrays.asList(indices))
                .from(request.getOffset())
                .size(request.getSize())
                .highlight(highlight)
                .query(query);
        if (columns.length > 0) {
            searchRequest.source(new SourceConfig.Builder().filter(f -> f.includes(Arrays.asList(columns)))
                    .build());
        }
        if (request.getESorts() != null) {
            List<SortOptions> sortOptions = new ArrayList<>(request.getESorts().length);
            for (ESort eSort : request.getESorts()) {
                sortOptions.add(new SortOptions.Builder().field(new FieldSort.Builder().field(eSort.getName())
                        .order(eSort.getOrderType() == OrderType.DESC ? SortOrder.Desc : SortOrder.Asc).build()).build());
            }
            searchRequest.sort(sortOptions);
        }
        try {
            SearchResponse<R> response = client.search(searchRequest.build(), responseType);
            HitsMetadata<R> hitsMetadata = response.hits();
            if (total == -1) {
                TotalHits totalHits = hitsMetadata.total();
                request.setTotal(totalHits.value());
                request.setTotalRel(totalHits.relation() == TotalHitsRelation.Gte ? TotalHitRelation.GTE : TotalHitRelation.EQ);
            }
            List<R> records = new ArrayList<>(hitsMetadata.hits().size());
            Function<Hit<R>, R> analysisRecord = analysisRecord(responseType, highlight);
            for (Hit<R> hit : hitsMetadata.hits()) {
                records.add(analysisRecord.apply(hit));
            }
            request.setRecords(records);
        } catch (Exception e) {
            throw new ElasticException("elasticClient查询失败", e);
        }
        return request;
    }

    public ElasticsearchClient client() {
        return client;
    }

    private <R> Function<Hit<R>, R> analysisRecord(Class<R> rClass, Highlight highlight) {
        if (highlight == null) {
            return Hit::source;
        } else {
            boolean isMap = Map.class.isAssignableFrom(rClass);
            return (h -> {
                if (h.highlight().isEmpty()) return h.source();
                R r = h.source();
                if (isMap) {
                    Map<String, Object> temp = (Map<String, Object>) r;
                    for (Map.Entry<String, List<String>> entry : h.highlight().entrySet()) {
                        temp.put(entry.getKey(), entry.getValue().get(0));
                    }
                } else {
                    BeanMap beanMap = BeanMap.create(h.source());
                    for (Map.Entry<String, List<String>> entry : h.highlight().entrySet()) {
                        beanMap.put(entry.getKey(), entry.getValue().get(0));
                    }
                    r = (R) beanMap.getBean();
                }
                return r;
            });
        }
    }


    private String[] successId(BulkResponse bulkResponse, String[] ids) {
        if (bulkResponse.errors()) {
            List<String> result = new ArrayList<>(ids.length);
            List<String> errorId = new ArrayList<>();
            String errorMessage = null;
            for (BulkResponseItem item : bulkResponse.items()) {
                if (item.error() == null) {
                    result.add(item.id());
                } else {
                    errorId.add(item.id());
                    if (errorMessage == null) {
                        errorMessage = item.error().toString();
                    }
                }
            }
            StringBuilder errIds = new StringBuilder();
            for (String s : errorId) {
                errIds.append(s).append(",");
            }
            LOGGER.warn("批量操作失败，失败ID有：{},其中一个失败原因为：{}", errIds, errorMessage);
            return result.toArray(new String[0]);
        } else {
            if (ids == null) {
                return bulkResponse.items().stream().map(BulkResponseItem::id).toArray(String[]::new);
            } else {
                return ids;
            }
        }
    }

}
