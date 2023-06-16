/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.elastic.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
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
import net.evecom.elastic.enums.OrderType;
import net.evecom.elastic.enums.TotalHitRelation;
import net.evecom.elastic.exception.ElasticException;
import net.evecom.elastic.indexbuilder.ElasticIndexBuild;
import net.evecom.elastic.indexbuilder.ElasticQueryIndicesBuild;
import net.evecom.elastic.model.EPageRequest;
import net.evecom.elastic.model.ESort;
import net.evecom.elastic.model.EsBaseEntity;
import net.evecom.elastic.pojo.EObjectQueryWrapper;
import net.evecom.elastic.pojo.EQueryWrapper;
import net.evecom.elastic.result.CommonAnalysis;
import net.evecom.elastic.result.HighLightAnalysis;
import net.evecom.elastic.result.ResultAnalysis;
import net.evecom.elastic.result.ResultType;
import net.evecom.elastic.service.ElasticSearch;
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
     * ID处理，这里是将ID赋值到原数据
     */
    private static final BiConsumer<BulkResponse, EsBaseEntity[]> ID_HANDLE = (bulkItemResponses, object) -> {
        List<BulkResponseItem> items = bulkItemResponses.items();
        for (BulkResponseItem item : items) {
            if(item.error()==null){

            }
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).error()==null) {
                object[i].setId(items.get(i).id());
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
    public <T, R extends EsBaseEntity> EPageRequest<R> searchByObj(EQueryWrapper eQueryWrapper, EPageRequest<R> request,
                                                                   Class<R> responseType) {
        return this.searchByObj(eQueryWrapper, request, null, responseType);
    }

    @Override
    public <T, R extends EsBaseEntity> EPageRequest<R> searchByObj(EQueryWrapper eQueryWrapper, EPageRequest<R> request,
                                                                   ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild, Class<R> responseType) {

        String[] indices = this.buildIndices(eQueryWrapper, elasticQueryIndicesBuild);

        return this.buildResponse(eQueryWrapper.buildSearchSourceBuilder(), eQueryWrapper.getHighlightBuilder(),
                indices, eQueryWrapper.getColumn(), request, responseType);
    }

    @Override
    public <T> EPageRequest<Map> search2MapByObj(EQueryWrapper eQueryWrapper, EPageRequest<Map> request) {
        return this.search2MapByObj(eQueryWrapper, request, null);
    }

    @Override
    public <T> EPageRequest<Map> search2MapByObj(EQueryWrapper eQueryWrapper,
                                                 EPageRequest<Map> request,
                                                 ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild) {

        String[] indices = this.buildIndices(eQueryWrapper, elasticQueryIndicesBuild);

        return this.buildResponse(eQueryWrapper.buildSearchSourceBuilder(),
                eQueryWrapper.getHighlightBuilder(),
                indices, eQueryWrapper.getColumn(),
                request, Map.class);
    }


    @Override
    public Long countBySearchSource(Query query, String... indices) {

        CountRequest.Builder countRequest = new CountRequest.Builder();
        countRequest.index(Arrays.asList(indices));
        countRequest.query(query);
        try {
            CountResponse count = client.count(countRequest.build());
            return count.count();
        } catch (IOException e) {
            return 0L;
        }
    }

    @Override
    public <T> Long countByObj(EQueryWrapper eQueryWrapper, ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild) {

        String[] indices = this.buildIndices(eQueryWrapper, elasticQueryIndicesBuild);
        return this.countBySearchSource(eQueryWrapper.buildSearchSourceBuilder(), indices);
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
                BulkRequest.Builder bulkRequest = new BulkRequest.Builder();
                bulkRequest.refresh(refresh ? Refresh.True :
                        Refresh.False);
                List<BulkOperation> bulkOperations=new ArrayList<>(object.length);
                for (T t : object) {
                    BulkOperation.Builder builder = new BulkOperation.Builder();
                    builder.create(r->{
                        r.index(indicesBuild.buildIndices(t, alias, indices));
                        r.id(t.getId());
                        r.document(t);
                        return r;
                    });
                    bulkOperations.add(builder.build());
                }
                bulkRequest.operations(bulkOperations);
                BulkResponse bulk = client.bulk(bulkRequest.build());
                ID_HANDLE.accept(bulk, object);
            } else {
                CreateRequest.Builder<T> request=new CreateRequest.Builder<>();
                request.id(object[0].getId());
                request.index(indicesBuild.buildIndices(object[0], alias, indices));
                request.refresh(refresh ? Refresh.True :
                        Refresh.False);
                request.document(object[0]);
                CreateResponse response = client.create(request.build());
                object[0].setId(response.id());
            }
        } catch (Exception e) {
            throw new ElasticException("ES新增数据失败",e);
        }
    }


    @Override
    public <T extends EsBaseEntity> String[] updateById(boolean refresh, T... object) {
        return updateById(refresh, getIndex(object[0]), object);
    }

    public <T extends EsBaseEntity> String[] updateById(boolean refresh, String index, T... object) {
        Assert.notEmpty(object, "数组不允许为空");
        return updateById(refresh, (o, a1, a2) -> index, object);
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
                UpdateRequest.Builder<T, T> updateRequest = new UpdateRequest.Builder<>();
                updateRequest.index(indexBuild.buildIndices(object[0], alias, indices));
                updateRequest.id(object[0].getId());
                updateRequest.docAsUpsert(true);
                updateRequest.doc(object[0]);
                updateRequest.refresh(refresh==true?Refresh.True:Refresh.False);
                UpdateRequest<T, T> build = updateRequest.build();
                UpdateResponse response = client.update(build,(Class<T>) object[0].getClass());
                return new String[]{response.id()};
            }

        } catch (Exception e) {
            throw new ElasticException("ES更新数据失败:",e);
        }
    }


    @Override
    public <T extends EsBaseEntity> void updateByQuery(boolean refresh, EObjectQueryWrapper<T> queryWrapper, T object) {
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
                client.delete(deleteRequest);
                return id;
            }
        } catch (Exception e) {
            throw new ElasticException("ES删除数据失败", e);
        }
    }

    public void deleteByQuery(boolean refresh, EObjectQueryWrapper<?> queryWrapper, String... indices) {
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
    public <T> SearchResponse<T> executeSearchRequest(SearchRequest request,Class<T> responseClass) throws IOException {
        return client.search(request,responseClass);
    }

    public <T> MsearchResponse<T> executeMultiSearchRequest(MsearchRequest request,Class<T> responseClass) throws IOException {
        return client.msearch(request,responseClass);
    }


    @Override
    public <T> SearchResponse executeSearchRequest(
            EObjectQueryWrapper<T> eObjectQueryWrapper,
            ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild, Aggregation agg) {
        String[] indices = this.buildIndices(eObjectQueryWrapper, elasticQueryIndicesBuild);
        SearchRequest.Builder searchRequest = new SearchRequest.Builder();
        searchRequest.index(Arrays.asList(indices));
        Query query = eObjectQueryWrapper.buildSearchSourceBuilder();
        searchRequest.query(query);
        searchRequest.size(0);
        searchRequest.aggregations(agg.aggregations());

        //TODO 暂时放着
        return null;
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
    public <T> String[] buildIndices(EQueryWrapper eQueryWrapper, ElasticQueryIndicesBuild<T> elasticQueryIndicesBuild) {
        Assert.notNull(eQueryWrapper, "Search obj must be not null!");
        String index = eQueryWrapper.getIndex();
        String[] indexs = null;
        if (eQueryWrapper.isAlias()) {
            indexs = this.getIndices(index);
        }
        if (elasticQueryIndicesBuild != null) {
            indexs = elasticQueryIndicesBuild.buildIndices(eQueryWrapper, index, indexs);
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
        if(request==null){
            request=new EPageRequest<>();
        }

        if (request.getSize() <= 0) {
            request.setRecords(Collections.emptyList());
            return request;
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
            List<R> records = new ArrayList<>(hitsMetadata.hits().size());
            Function<Hit<R>, R> analysisRecord = analysisRecord(responseType, highlight);
            for (Hit<R> hit : hitsMetadata.hits()) {
                R record = analysisRecord.apply(hit);
                records.add(record);
            }
            request.setRecords(records);
            //处理请求总数
            TotalHits totalHits = hitsMetadata.total();
            if(totalHits.relation()==TotalHitsRelation.Eq){
                request.setTotal(totalHits.value());
                request.setTotalRel(TotalHitRelation.EQ);
            }else if(request.isSearchCount()){
                request.setTotal(countBySearchSource(query, indices));
                request.setTotalRel(TotalHitRelation.EQ);
            }else{
                request.setTotal(totalHits.value());
                request.setTotalRel(totalHits.relation() == TotalHitsRelation.Gte ? TotalHitRelation.GTE : TotalHitRelation.EQ);
            }
        } catch (Exception e) {
            throw new ElasticException("ES查询失败", e);
        }
        return request;
    }

    public ElasticsearchClient client() {
        return client;
    }


    private <R> Function<Hit<R>, R> analysisRecord(Class<R> rClass, Highlight highlight) {
        ResultType type = ResultType.getType(rClass);
        List<ResultAnalysis<R>> analyses=new ArrayList<>(2);
        if(type==ResultType.ENTITY){
            analyses.add(new CommonAnalysis(type));
        }
        if(highlight!=null){
            analyses.add(new HighLightAnalysis(highlight,type));
        }
        if(analyses.size()>0){
            return (f)->{
                R source = f.source();
                for (ResultAnalysis<R> analysis : analyses) {
                    source=analysis.apply(source,f);
                }
                return source;
            };
        }else{
            return Hit::source;
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
