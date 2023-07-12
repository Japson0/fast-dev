package net.evecom.elastic.pojo;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import net.evecom.elastic.enums.ElasticBoolType;

import java.util.*;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月15日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public abstract class AbstractQueryWrapper implements EQueryWrapper {

    private Map<ElasticBoolType, List<Query>> convertBean2Query(Collection<ESearchValue> searchValues) {
        Map<ElasticBoolType, List<Query>> ans = new HashMap<>();
        for (ESearchValue searchValue : searchValues) {
            ElasticBoolType boolType = searchValue.getElasticBoolType();
            Object value = searchValue.getValue();
            if (value == null) continue;
            ans.computeIfAbsent(boolType, s -> new ArrayList<>()).add(new Query(searchValue.objectBuilder().build()));
        }

        return ans;
    }

    protected abstract List<ESearchValue> getSearchValue();

    public Query buildSearchSourceBuilder() {
        Query.Builder queryBuilder = new Query.Builder();
        List<ESearchValue> searchValue = getSearchValue();
        if (searchValue.isEmpty()) {
            queryBuilder.matchAll(new MatchAllQuery.Builder().build());
        } else {
            Map<ElasticBoolType, List<Query>> queryBuildersMap = convertBean2Query(searchValue);
            BoolQuery.Builder boolBuilder = new BoolQuery.Builder();

            List<Query> filterBuilder = queryBuildersMap.get(ElasticBoolType.FILTER);
            if (filterBuilder != null) {
                boolBuilder.filter(filterBuilder);
            }
            List<Query> mustBuilder = queryBuildersMap.get(ElasticBoolType.MUST);
            if (mustBuilder != null) {
                boolBuilder.must(mustBuilder);
            }
            List<Query> mustNotBuilder = queryBuildersMap.get(ElasticBoolType.MUST_NOT);
            if (mustNotBuilder != null) {
                boolBuilder.mustNot(mustNotBuilder);
            }
            List<Query> shouldBuilder = queryBuildersMap.get(ElasticBoolType.SHOULD);
            if (shouldBuilder != null) {
                boolBuilder.should(shouldBuilder);
            }
            queryBuilder.bool(boolBuilder.build());
        }
        return queryBuilder.build();
    }
}
