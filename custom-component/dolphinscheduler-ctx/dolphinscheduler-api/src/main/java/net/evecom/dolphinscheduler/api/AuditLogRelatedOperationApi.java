package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameAuditOperationType;
import io.swagger.client.model.ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameAuditResourceType;
import io.swagger.client.model.Result;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface AuditLogRelatedOperationApi extends ApiClient.Api {

    /**
     * queryAuditLogListPaging
     * query audit log
     *
     * @param pageNo        page no (required)
     * @param pageSize      page size (required)
     * @param endDate       end date (optional)
     * @param operationType operation type (optional)
     * @param resourceType  resource file type (optional)
     * @param startDate     start date (optional)
     * @param userName      user name (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/audit/audit-log-list?endDate={endDate}&operationType={operationType}&pageNo={pageNo}&pageSize={pageSize}&resourceType={resourceType}&startDate={startDate}&userName={userName}")
    @Headers({
            "Accept: */*",
    })
    Result queryAuditLogListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("endDate") String endDate, @Param("operationType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameAuditOperationType operationType, @Param("resourceType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameAuditResourceType resourceType, @Param("startDate") String startDate, @Param("userName") String userName);

    /**
     * queryAuditLogListPaging
     * query audit log
     * Note, this is equivalent to the other <code>queryAuditLogListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryAuditLogListPagingUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>endDate - end date (optional)</li>
     *                    <li>operationType - operation type (optional)</li>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>resourceType - resource file type (optional)</li>
     *                    <li>startDate - start date (optional)</li>
     *                    <li>userName - user name (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/audit/audit-log-list?endDate={endDate}&operationType={operationType}&pageNo={pageNo}&pageSize={pageSize}&resourceType={resourceType}&startDate={startDate}&userName={userName}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryAuditLogListPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryAuditLogListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryAuditLogListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryAuditLogListPagingUsingGET1QueryParams endDate(final String value) {
            put("endDate", EncodingUtils.encode(value));
            return this;
        }

        public QueryAuditLogListPagingUsingGET1QueryParams operationType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameAuditOperationType value) {
            put("operationType", EncodingUtils.encode(value));
            return this;
        }

        public QueryAuditLogListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryAuditLogListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryAuditLogListPagingUsingGET1QueryParams resourceType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameAuditResourceType value) {
            put("resourceType", EncodingUtils.encode(value));
            return this;
        }

        public QueryAuditLogListPagingUsingGET1QueryParams startDate(final String value) {
            put("startDate", EncodingUtils.encode(value));
            return this;
        }

        public QueryAuditLogListPagingUsingGET1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }
    }
}
