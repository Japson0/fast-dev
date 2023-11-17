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
public interface DataSourceRelatedOperationApi extends ApiClient.Api {

    /**
     * authedDatasource
     * authorized data source
     *
     * @param userId user id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/authed-datasource?userId={userId}")
    @Headers({
            "Accept: */*",
    })
    Result authedDatasourceUsingGET1(@Param("userId") Integer userId);

    /**
     * authedDatasource
     * authorized data source
     * Note, this is equivalent to the other <code>authedDatasourceUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link AuthedDatasourceUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/authed-datasource?userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result authedDatasourceUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>authedDatasourceUsingGET1</code> method in a fluent style.
     */
    public static class AuthedDatasourceUsingGET1QueryParams extends HashMap<String, Object> {
        public AuthedDatasourceUsingGET1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * connectDataSource
     * CONNECT DATA SOURCE
     *
     * @param body (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/datasources/connect")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result connectDataSourceUsingPOST1(String body);

    /**
     * connectionTest
     * connect data source test
     *
     * @param id DATA SOURCE ID (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/{id}/connect-test")
    @Headers({
            "Accept: */*",
    })
    Result connectionTestUsingGET1(@Param("id") Integer id);

    /**
     * createDataSource
     * create data source
     *
     * @param body (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/datasources")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createDataSourceUsingPOST1(String body);

    /**
     * deleteDataSource
     * delete data source
     *
     * @param id DATA SOURCE ID (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/datasources/{id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteDataSourceUsingDELETE1(@Param("id") Integer id);

    /**
     * getKerberosStartupState
     * get user info
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/kerberos-startup-state")
    @Headers({
            "Accept: */*",
    })
    Result getKerberosStartupStateUsingGET1();

    /**
     * tableColumns
     * get datasource table columns
     *
     * @param datasourceId DATA SOURCE ID (required)
     * @param tableName    table name (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/tableColumns?datasourceId={datasourceId}&tableName={tableName}")
    @Headers({
            "Accept: */*",
    })
    Result getTableColumnsUsingGET1(@Param("datasourceId") Integer datasourceId, @Param("tableName") String tableName);

    /**
     * tableColumns
     * get datasource table columns
     * Note, this is equivalent to the other <code>getTableColumnsUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GetTableColumnsUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>datasourceId - DATA SOURCE ID (required)</li>
     *                    <li>tableName - table name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/tableColumns?datasourceId={datasourceId}&tableName={tableName}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result getTableColumnsUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>getTableColumnsUsingGET1</code> method in a fluent style.
     */
    public static class GetTableColumnsUsingGET1QueryParams extends HashMap<String, Object> {
        public GetTableColumnsUsingGET1QueryParams datasourceId(final Integer value) {
            put("datasourceId", EncodingUtils.encode(value));
            return this;
        }

        public GetTableColumnsUsingGET1QueryParams tableName(final String value) {
            put("tableName", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * tables
     * get datasource table
     *
     * @param datasourceId DATA SOURCE ID (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/tables?datasourceId={datasourceId}")
    @Headers({
            "Accept: */*",
    })
    Result getTablesUsingGET1(@Param("datasourceId") Integer datasourceId);

    /**
     * tables
     * get datasource table
     * Note, this is equivalent to the other <code>getTablesUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GetTablesUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>datasourceId - DATA SOURCE ID (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/tables?datasourceId={datasourceId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result getTablesUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>getTablesUsingGET1</code> method in a fluent style.
     */
    public static class GetTablesUsingGET1QueryParams extends HashMap<String, Object> {
        public GetTablesUsingGET1QueryParams datasourceId(final Integer value) {
            put("datasourceId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryDataSourceListPaging
     * query data source list paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Result queryDataSourceListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryDataSourceListPaging
     * query data source list paging
     * Note, this is equivalent to the other <code>queryDataSourceListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryDataSourceListPagingUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>searchVal - search val (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryDataSourceListPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryDataSourceListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryDataSourceListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryDataSourceListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryDataSourceListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryDataSourceListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryDataSourceList
     * query data source list by database type
     *
     * @param type database type (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/list?type={type}")
    @Headers({
            "Accept: */*",
    })
    Result queryDataSourceListUsingGET1(@Param("type") String type);

    /**
     * queryDataSourceList
     * query data source list by database type
     * Note, this is equivalent to the other <code>queryDataSourceListUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryDataSourceListUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>type - database type (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/list?type={type}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryDataSourceListUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryDataSourceListUsingGET1</code> method in a fluent style.
     */
    public static class QueryDataSourceListUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryDataSourceListUsingGET1QueryParams type(final String value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryDataSource
     * query data source by id
     *
     * @param id DATA SOURCE ID (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/{id}")
    @Headers({
            "Accept: */*",
    })
    Result queryDataSourceUsingGET1(@Param("id") Integer id);

    /**
     * unauthDatasource
     * unauthorized data source
     *
     * @param userId user id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/unauth-datasource?userId={userId}")
    @Headers({
            "Accept: */*",
    })
    Result unauthDatasourceUsingGET1(@Param("userId") Integer userId);

    /**
     * unauthDatasource
     * unauthorized data source
     * Note, this is equivalent to the other <code>unauthDatasourceUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UnauthDatasourceUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/unauth-datasource?userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result unauthDatasourceUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>unauthDatasourceUsingGET1</code> method in a fluent style.
     */
    public static class UnauthDatasourceUsingGET1QueryParams extends HashMap<String, Object> {
        public UnauthDatasourceUsingGET1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateDataSource
     * update data source
     *
     * @param id   DATA SOURCE ID (required)
     * @param body (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/datasources/{id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateDataSourceUsingPUT1(@Param("id") Integer id, String body);

    /**
     * verifyDataSourceName
     * verify data source
     *
     * @param name data source name (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/verify-name?name={name}")
    @Headers({
            "Accept: */*",
    })
    Result verifyDataSourceNameUsingGET1(@Param("name") String name);

    /**
     * verifyDataSourceName
     * verify data source
     * Note, this is equivalent to the other <code>verifyDataSourceNameUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyDataSourceNameUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>name - data source name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/datasources/verify-name?name={name}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result verifyDataSourceNameUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyDataSourceNameUsingGET1</code> method in a fluent style.
     */
    public static class VerifyDataSourceNameUsingGET1QueryParams extends HashMap<String, Object> {
        public VerifyDataSourceNameUsingGET1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }
    }
}
