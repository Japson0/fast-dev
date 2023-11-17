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
public interface TenantRelatedOperationApi extends ApiClient.Api {

    /**
     * createTenant
     * create tenant
     *
     * @param queueId     queue id (required)
     * @param tenantCode  os tenant code (required)
     * @param description tenant desc (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/tenants?description={description}&queueId={queueId}&tenantCode={tenantCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createTenantUsingPOST1(@Param("queueId") Integer queueId, @Param("tenantCode") String tenantCode, @Param("description") String description);

    /**
     * createTenant
     * create tenant
     * Note, this is equivalent to the other <code>createTenantUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateTenantUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>description - tenant desc (optional)</li>
     *                    <li>queueId - queue id (required)</li>
     *                    <li>tenantCode - os tenant code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/tenants?description={description}&queueId={queueId}&tenantCode={tenantCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createTenantUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createTenantUsingPOST1</code> method in a fluent style.
     */
    public static class CreateTenantUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateTenantUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public CreateTenantUsingPOST1QueryParams queueId(final Integer value) {
            put("queueId", EncodingUtils.encode(value));
            return this;
        }

        public CreateTenantUsingPOST1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * deleteTenantById
     * delete tenant
     *
     * @param id tenant id (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/tenants/{id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteTenantByIdUsingDELETE1(@Param("id") Integer id);

    /**
     * queryTenantlistPaging
     * query tenant list paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/tenants?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Result queryTenantlistPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryTenantlistPaging
     * query tenant list paging
     * Note, this is equivalent to the other <code>queryTenantlistPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryTenantlistPagingUsingGET1QueryParams} class that allows for
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
    @RequestLine("GET /dolphinscheduler/tenants?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryTenantlistPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryTenantlistPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryTenantlistPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryTenantlistPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryTenantlistPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryTenantlistPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryTenantlist
     * query tenant list
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/tenants/list")
    @Headers({
            "Accept: */*",
    })
    Result queryTenantlistUsingGET1();

    /**
     * updateTenant
     * update tenant
     *
     * @param id          tenant id (required)
     * @param queueId     queue id (required)
     * @param tenantCode  os tenant code (required)
     * @param description tenant desc (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/tenants/{id}?description={description}&queueId={queueId}&tenantCode={tenantCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateTenantUsingPUT1(@Param("id") Integer id, @Param("queueId") Integer queueId, @Param("tenantCode") String tenantCode, @Param("description") String description);

    /**
     * updateTenant
     * update tenant
     * Note, this is equivalent to the other <code>updateTenantUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateTenantUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          tenant id (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>description - tenant desc (optional)</li>
     *                    <li>queueId - queue id (required)</li>
     *                    <li>tenantCode - os tenant code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/tenants/{id}?description={description}&queueId={queueId}&tenantCode={tenantCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateTenantUsingPUT1(@Param("id") Integer id, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateTenantUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateTenantUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateTenantUsingPUT1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public UpdateTenantUsingPUT1QueryParams queueId(final Integer value) {
            put("queueId", EncodingUtils.encode(value));
            return this;
        }

        public UpdateTenantUsingPUT1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * verifyTenantCode
     * verify tenant code
     *
     * @param tenantCode os tenant code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/tenants/verify-code?tenantCode={tenantCode}")
    @Headers({
            "Accept: */*",
    })
    Result verifyTenantCodeUsingGET1(@Param("tenantCode") String tenantCode);

    /**
     * verifyTenantCode
     * verify tenant code
     * Note, this is equivalent to the other <code>verifyTenantCodeUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyTenantCodeUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>tenantCode - os tenant code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/tenants/verify-code?tenantCode={tenantCode}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result verifyTenantCodeUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyTenantCodeUsingGET1</code> method in a fluent style.
     */
    public static class VerifyTenantCodeUsingGET1QueryParams extends HashMap<String, Object> {
        public VerifyTenantCodeUsingGET1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }
    }
}
