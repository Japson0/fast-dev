package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.Result;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface DataQualityServiceApi extends ApiClient.Api {

    /**
     * getDatasourceOptionsById
     * get datasource options
     *
     * @param datasourceId DATA SOURCE ID (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/data-quality/getDatasourceOptionsById?datasourceId={datasourceId}")
    @Headers({
            "Accept: */*",
    })
    Result getDatasourceOptionsByIdUsingGET1(@Param("datasourceId") Integer datasourceId);

    /**
     * getDatasourceOptionsById
     * get datasource options
     * Note, this is equivalent to the other <code>getDatasourceOptionsByIdUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GetDatasourceOptionsByIdUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>datasourceId - DATA SOURCE ID (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/data-quality/getDatasourceOptionsById?datasourceId={datasourceId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result getDatasourceOptionsByIdUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>getDatasourceOptionsByIdUsingGET1</code> method in a fluent style.
     */
    public static class GetDatasourceOptionsByIdUsingGET1QueryParams extends HashMap<String, Object> {
        public GetDatasourceOptionsByIdUsingGET1QueryParams datasourceId(final Integer value) {
            put("datasourceId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * getRuleFormCreateJson
     * get rule form-create json
     *
     * @param ruleId rule id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/data-quality/getRuleFormCreateJson?ruleId={ruleId}")
    @Headers({
            "Accept: */*",
    })
    Result getRuleFormCreateJsonByIdUsingGET1(@Param("ruleId") Integer ruleId);

    /**
     * getRuleFormCreateJson
     * get rule form-create json
     * Note, this is equivalent to the other <code>getRuleFormCreateJsonByIdUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GetRuleFormCreateJsonByIdUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>ruleId - rule id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/data-quality/getRuleFormCreateJson?ruleId={ruleId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result getRuleFormCreateJsonByIdUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>getRuleFormCreateJsonByIdUsingGET1</code> method in a fluent style.
     */
    public static class GetRuleFormCreateJsonByIdUsingGET1QueryParams extends HashMap<String, Object> {
        public GetRuleFormCreateJsonByIdUsingGET1QueryParams ruleId(final Integer value) {
            put("ruleId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryExecuteResultListPaging
     * query execute result list paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param endDate   end date (optional)
     * @param ruleType  rule type (optional)
     * @param searchVal search val (optional)
     * @param startDate start date (optional)
     * @param state     state (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/data-quality/result/page?endDate={endDate}&pageNo={pageNo}&pageSize={pageSize}&ruleType={ruleType}&searchVal={searchVal}&startDate={startDate}&state={state}")
    @Headers({
            "Accept: */*",
    })
    Result queryExecuteResultListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("endDate") String endDate, @Param("ruleType") Integer ruleType, @Param("searchVal") String searchVal, @Param("startDate") String startDate, @Param("state") Integer state);

    /**
     * queryExecuteResultListPaging
     * query execute result list paging
     * Note, this is equivalent to the other <code>queryExecuteResultListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryExecuteResultListPagingUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>endDate - end date (optional)</li>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>ruleType - rule type (optional)</li>
     *                    <li>searchVal - search val (optional)</li>
     *                    <li>startDate - start date (optional)</li>
     *                    <li>state - state (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/data-quality/result/page?endDate={endDate}&pageNo={pageNo}&pageSize={pageSize}&ruleType={ruleType}&searchVal={searchVal}&startDate={startDate}&state={state}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryExecuteResultListPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryExecuteResultListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryExecuteResultListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryExecuteResultListPagingUsingGET1QueryParams endDate(final String value) {
            put("endDate", EncodingUtils.encode(value));
            return this;
        }

        public QueryExecuteResultListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryExecuteResultListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryExecuteResultListPagingUsingGET1QueryParams ruleType(final Integer value) {
            put("ruleType", EncodingUtils.encode(value));
            return this;
        }

        public QueryExecuteResultListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }

        public QueryExecuteResultListPagingUsingGET1QueryParams startDate(final String value) {
            put("startDate", EncodingUtils.encode(value));
            return this;
        }

        public QueryExecuteResultListPagingUsingGET1QueryParams state(final Integer value) {
            put("state", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryRuleListPaging
     * query rule list paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param endDate   end date (optional)
     * @param ruleType  rule type (optional)
     * @param searchVal search val (optional)
     * @param startDate start date (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/data-quality/rule/page?endDate={endDate}&pageNo={pageNo}&pageSize={pageSize}&ruleType={ruleType}&searchVal={searchVal}&startDate={startDate}")
    @Headers({
            "Accept: */*",
    })
    Result queryRuleListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("endDate") String endDate, @Param("ruleType") Integer ruleType, @Param("searchVal") String searchVal, @Param("startDate") String startDate);

    /**
     * queryRuleListPaging
     * query rule list paging
     * Note, this is equivalent to the other <code>queryRuleListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryRuleListPagingUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>endDate - end date (optional)</li>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>ruleType - rule type (optional)</li>
     *                    <li>searchVal - search val (optional)</li>
     *                    <li>startDate - start date (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/data-quality/rule/page?endDate={endDate}&pageNo={pageNo}&pageSize={pageSize}&ruleType={ruleType}&searchVal={searchVal}&startDate={startDate}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryRuleListPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryRuleListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryRuleListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryRuleListPagingUsingGET1QueryParams endDate(final String value) {
            put("endDate", EncodingUtils.encode(value));
            return this;
        }

        public QueryRuleListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryRuleListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryRuleListPagingUsingGET1QueryParams ruleType(final Integer value) {
            put("ruleType", EncodingUtils.encode(value));
            return this;
        }

        public QueryRuleListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }

        public QueryRuleListPagingUsingGET1QueryParams startDate(final String value) {
            put("startDate", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryRuleList
     * query rule list
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/data-quality/ruleList")
    @Headers({
            "Accept: */*",
    })
    Result queryRuleListUsingGET1();
}
