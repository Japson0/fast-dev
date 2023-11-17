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
public interface K8sNamespaceRelatedOperationApi extends ApiClient.Api {

    /**
     * createK8sNamespace
     * create k8s namespace
     *
     * @param clusterCode  cluster code (required)
     * @param namespace    k8s namespace (required)
     * @param limitsCpu    limitsCpu (optional)
     * @param limitsMemory limitsMemory (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/k8s-namespace?clusterCode={clusterCode}&namespace={namespace}&limitsCpu={limitsCpu}&limitsMemory={limitsMemory}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createNamespaceUsingPOST1(@Param("clusterCode") Long clusterCode, @Param("namespace") String namespace, @Param("limitsCpu") Double limitsCpu, @Param("limitsMemory") Integer limitsMemory);

    /**
     * createK8sNamespace
     * create k8s namespace
     * Note, this is equivalent to the other <code>createNamespaceUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateNamespaceUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>clusterCode - cluster code (required)</li>
     *                    <li>namespace - k8s namespace (required)</li>
     *                    <li>limitsCpu - limitsCpu (optional)</li>
     *                    <li>limitsMemory - limitsMemory (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/k8s-namespace?clusterCode={clusterCode}&namespace={namespace}&limitsCpu={limitsCpu}&limitsMemory={limitsMemory}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createNamespaceUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createNamespaceUsingPOST1</code> method in a fluent style.
     */
    public static class CreateNamespaceUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateNamespaceUsingPOST1QueryParams clusterCode(final Long value) {
            put("clusterCode", EncodingUtils.encode(value));
            return this;
        }

        public CreateNamespaceUsingPOST1QueryParams namespace(final String value) {
            put("namespace", EncodingUtils.encode(value));
            return this;
        }

        public CreateNamespaceUsingPOST1QueryParams limitsCpu(final Double value) {
            put("limitsCpu", EncodingUtils.encode(value));
            return this;
        }

        public CreateNamespaceUsingPOST1QueryParams limitsMemory(final Integer value) {
            put("limitsMemory", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * delNamespaceById
     * delete k8s namespace by id
     *
     * @param id k8s namespace ID (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/k8s-namespace/delete?id={id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result delNamespaceByIdUsingPOST1(@Param("id") Integer id);

    /**
     * delNamespaceById
     * delete k8s namespace by id
     * Note, this is equivalent to the other <code>delNamespaceByIdUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link DelNamespaceByIdUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>id - k8s namespace ID (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/k8s-namespace/delete?id={id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result delNamespaceByIdUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>delNamespaceByIdUsingPOST1</code> method in a fluent style.
     */
    public static class DelNamespaceByIdUsingPOST1QueryParams extends HashMap<String, Object> {
        public DelNamespaceByIdUsingPOST1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryAuthorizedNamespace
     * query authorized k8s namespace
     *
     * @param userId user id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/k8s-namespace/authed-namespace?userId={userId}")
    @Headers({
            "Accept: */*",
    })
    Result queryAuthorizedNamespaceUsingGET1(@Param("userId") Integer userId);

    /**
     * queryAuthorizedNamespace
     * query authorized k8s namespace
     * Note, this is equivalent to the other <code>queryAuthorizedNamespaceUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryAuthorizedNamespaceUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/k8s-namespace/authed-namespace?userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryAuthorizedNamespaceUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryAuthorizedNamespaceUsingGET1</code> method in a fluent style.
     */
    public static class QueryAuthorizedNamespaceUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryAuthorizedNamespaceUsingGET1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryAvailableNamespaceList
     * query available k8s namespace
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/k8s-namespace/available-list")
    @Headers({
            "Accept: */*",
    })
    Result queryAvailableNamespaceListUsingGET1();

    /**
     * queryNamespaceListPaging
     * query k8s namespace list paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/k8s-namespace?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Result queryNamespaceListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryNamespaceListPaging
     * query k8s namespace list paging
     * Note, this is equivalent to the other <code>queryNamespaceListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryNamespaceListPagingUsingGET1QueryParams} class that allows for
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
    @RequestLine("GET /dolphinscheduler/k8s-namespace?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryNamespaceListPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryNamespaceListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryNamespaceListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryNamespaceListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryNamespaceListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryNamespaceListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryUnauthorizedNamespace
     * query unauthorized k8s namespace
     *
     * @param userId user id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/k8s-namespace/unauth-namespace?userId={userId}")
    @Headers({
            "Accept: */*",
    })
    Result queryUnauthorizedNamespaceUsingGET1(@Param("userId") Integer userId);

    /**
     * queryUnauthorizedNamespace
     * query unauthorized k8s namespace
     * Note, this is equivalent to the other <code>queryUnauthorizedNamespaceUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryUnauthorizedNamespaceUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/k8s-namespace/unauth-namespace?userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryUnauthorizedNamespaceUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryUnauthorizedNamespaceUsingGET1</code> method in a fluent style.
     */
    public static class QueryUnauthorizedNamespaceUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryUnauthorizedNamespaceUsingGET1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateK8sNamespace
     * update k8s namespace
     *
     * @param id           k8s namespace ID (required)
     * @param limitsCpu    LIMITS_CPU (optional)
     * @param limitsMemory LIMITS_MEMORY (optional)
     * @param userName     OWNER (optional)
     * @param tag          tag (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/k8s-namespace/{id}?limitsCpu={limitsCpu}&limitsMemory={limitsMemory}&userName={userName}&tag={tag}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateNamespaceUsingPUT1(@Param("id") Integer id, @Param("limitsCpu") Double limitsCpu, @Param("limitsMemory") Integer limitsMemory, @Param("userName") String userName, @Param("tag") String tag);

    /**
     * updateK8sNamespace
     * update k8s namespace
     * Note, this is equivalent to the other <code>updateNamespaceUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateNamespaceUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          k8s namespace ID (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>limitsCpu - LIMITS_CPU (optional)</li>
     *                    <li>limitsMemory - LIMITS_MEMORY (optional)</li>
     *                    <li>userName - OWNER (optional)</li>
     *                    <li>tag - tag (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/k8s-namespace/{id}?limitsCpu={limitsCpu}&limitsMemory={limitsMemory}&userName={userName}&tag={tag}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateNamespaceUsingPUT1(@Param("id") Integer id, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateNamespaceUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateNamespaceUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateNamespaceUsingPUT1QueryParams limitsCpu(final Double value) {
            put("limitsCpu", EncodingUtils.encode(value));
            return this;
        }

        public UpdateNamespaceUsingPUT1QueryParams limitsMemory(final Integer value) {
            put("limitsMemory", EncodingUtils.encode(value));
            return this;
        }

        public UpdateNamespaceUsingPUT1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }

        public UpdateNamespaceUsingPUT1QueryParams tag(final String value) {
            put("tag", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * verifyNamespaceK8s
     * verify k8s namespace
     *
     * @param clusterCode cluster code (required)
     * @param namespace   k8s namespace (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/k8s-namespace/verify?clusterCode={clusterCode}&namespace={namespace}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result verifyNamespaceUsingPOST1(@Param("clusterCode") Long clusterCode, @Param("namespace") String namespace);

    /**
     * verifyNamespaceK8s
     * verify k8s namespace
     * Note, this is equivalent to the other <code>verifyNamespaceUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyNamespaceUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>clusterCode - cluster code (required)</li>
     *                    <li>namespace - k8s namespace (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/k8s-namespace/verify?clusterCode={clusterCode}&namespace={namespace}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result verifyNamespaceUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyNamespaceUsingPOST1</code> method in a fluent style.
     */
    public static class VerifyNamespaceUsingPOST1QueryParams extends HashMap<String, Object> {
        public VerifyNamespaceUsingPOST1QueryParams clusterCode(final Long value) {
            put("clusterCode", EncodingUtils.encode(value));
            return this;
        }

        public VerifyNamespaceUsingPOST1QueryParams namespace(final String value) {
            put("namespace", EncodingUtils.encode(value));
            return this;
        }
    }
}
