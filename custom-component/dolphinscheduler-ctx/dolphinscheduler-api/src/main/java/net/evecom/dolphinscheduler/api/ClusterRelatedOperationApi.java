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
public interface ClusterRelatedOperationApi extends ApiClient.Api {

    /**
     * createCluster
     * create cluster
     *
     * @param config      cluster configuration (required)
     * @param name        cluster name (required)
     * @param description cluster description (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/cluster/create?config={config}&description={description}&name={name}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createProjectUsingPOST1(@Param("config") String config, @Param("name") String name, @Param("description") String description);

    /**
     * createCluster
     * create cluster
     * Note, this is equivalent to the other <code>createProjectUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateProjectUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>config - cluster configuration (required)</li>
     *                    <li>description - cluster description (optional)</li>
     *                    <li>name - cluster name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/cluster/create?config={config}&description={description}&name={name}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createProjectUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createProjectUsingPOST1</code> method in a fluent style.
     */
    public static class CreateProjectUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateProjectUsingPOST1QueryParams config(final String value) {
            put("config", EncodingUtils.encode(value));
            return this;
        }

        public CreateProjectUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public CreateProjectUsingPOST1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * deleteClusterByCode
     * delete cluster by code
     *
     * @param clusterCode cluster code (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/cluster/delete?clusterCode={clusterCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteClusterUsingPOST1(@Param("clusterCode") Long clusterCode);

    /**
     * deleteClusterByCode
     * delete cluster by code
     * Note, this is equivalent to the other <code>deleteClusterUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link DeleteClusterUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>clusterCode - cluster code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/cluster/delete?clusterCode={clusterCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteClusterUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>deleteClusterUsingPOST1</code> method in a fluent style.
     */
    public static class DeleteClusterUsingPOST1QueryParams extends HashMap<String, Object> {
        public DeleteClusterUsingPOST1QueryParams clusterCode(final Long value) {
            put("clusterCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryAllClusterList
     * query all cluster list
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/cluster/query-cluster-list")
    @Headers({
            "Accept: */*",
    })
    Result queryAllClusterListUsingGET1();

    /**
     * queryClusterByCode
     * query cluster by code
     *
     * @param clusterCode cluster code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/cluster/query-by-code?clusterCode={clusterCode}")
    @Headers({
            "Accept: */*",
    })
    Result queryClusterByCodeUsingGET1(@Param("clusterCode") Long clusterCode);

    /**
     * queryClusterByCode
     * query cluster by code
     * Note, this is equivalent to the other <code>queryClusterByCodeUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryClusterByCodeUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>clusterCode - cluster code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/cluster/query-by-code?clusterCode={clusterCode}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryClusterByCodeUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryClusterByCodeUsingGET1</code> method in a fluent style.
     */
    public static class QueryClusterByCodeUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryClusterByCodeUsingGET1QueryParams clusterCode(final Long value) {
            put("clusterCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryClusterListPaging
     * query cluster list paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/cluster/list-paging?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Result queryClusterListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryClusterListPaging
     * query cluster list paging
     * Note, this is equivalent to the other <code>queryClusterListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryClusterListPagingUsingGET1QueryParams} class that allows for
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
    @RequestLine("GET /dolphinscheduler/cluster/list-paging?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryClusterListPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryClusterListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryClusterListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryClusterListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryClusterListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryClusterListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateCluster
     * update cluster
     *
     * @param code        cluster code (required)
     * @param config      cluster configuration (required)
     * @param name        cluster name (required)
     * @param description cluster description (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/cluster/update?code={code}&config={config}&description={description}&name={name}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateClusterUsingPOST1(@Param("code") Long code, @Param("config") String config, @Param("name") String name, @Param("description") String description);

    /**
     * updateCluster
     * update cluster
     * Note, this is equivalent to the other <code>updateClusterUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateClusterUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>code - cluster code (required)</li>
     *                    <li>config - cluster configuration (required)</li>
     *                    <li>description - cluster description (optional)</li>
     *                    <li>name - cluster name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/cluster/update?code={code}&config={config}&description={description}&name={name}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateClusterUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateClusterUsingPOST1</code> method in a fluent style.
     */
    public static class UpdateClusterUsingPOST1QueryParams extends HashMap<String, Object> {
        public UpdateClusterUsingPOST1QueryParams code(final Long value) {
            put("code", EncodingUtils.encode(value));
            return this;
        }

        public UpdateClusterUsingPOST1QueryParams config(final String value) {
            put("config", EncodingUtils.encode(value));
            return this;
        }

        public UpdateClusterUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public UpdateClusterUsingPOST1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * verifyCluster
     * verify cluster
     *
     * @param clusterName cluster name (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/cluster/verify-cluster?clusterName={clusterName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result verifyClusterUsingPOST1(@Param("clusterName") String clusterName);

    /**
     * verifyCluster
     * verify cluster
     * Note, this is equivalent to the other <code>verifyClusterUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyClusterUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>clusterName - cluster name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/cluster/verify-cluster?clusterName={clusterName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result verifyClusterUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyClusterUsingPOST1</code> method in a fluent style.
     */
    public static class VerifyClusterUsingPOST1QueryParams extends HashMap<String, Object> {
        public VerifyClusterUsingPOST1QueryParams clusterName(final String value) {
            put("clusterName", EncodingUtils.encode(value));
            return this;
        }
    }
}
