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
public interface AlertGroupRelatedOperationApi extends ApiClient.Api {

    /**
     * createAlertgroup
     * create alert group
     *
     * @param alertInstanceIds alertInstanceIds (required)
     * @param groupName        group name (required)
     * @param description      description (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/alert-groups?alertInstanceIds={alertInstanceIds}&description={description}&groupName={groupName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createAlertgroupUsingPOST1(@Param("alertInstanceIds") String alertInstanceIds, @Param("groupName") String groupName, @Param("description") String description);

    /**
     * createAlertgroup
     * create alert group
     * Note, this is equivalent to the other <code>createAlertgroupUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateAlertgroupUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>alertInstanceIds - alertInstanceIds (required)</li>
     *                    <li>description - description (optional)</li>
     *                    <li>groupName - group name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/alert-groups?alertInstanceIds={alertInstanceIds}&description={description}&groupName={groupName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createAlertgroupUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createAlertgroupUsingPOST1</code> method in a fluent style.
     */
    public static class CreateAlertgroupUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateAlertgroupUsingPOST1QueryParams alertInstanceIds(final String value) {
            put("alertInstanceIds", EncodingUtils.encode(value));
            return this;
        }

        public CreateAlertgroupUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public CreateAlertgroupUsingPOST1QueryParams groupName(final String value) {
            put("groupName", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * delAlertgroupById
     * delete alert group by id
     *
     * @param id alert group id (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/alert-groups/{id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result delAlertgroupByIdUsingDELETE1(@Param("id") Integer id);

    /**
     * queryAlertGroupListPaging
     * query alert group list paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/alert-groups?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Result listPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryAlertGroupListPaging
     * query alert group list paging
     * Note, this is equivalent to the other <code>listPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ListPagingUsingGET1QueryParams} class that allows for
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
    @RequestLine("GET /dolphinscheduler/alert-groups?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result listPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>listPagingUsingGET1</code> method in a fluent style.
     */
    public static class ListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public ListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public ListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public ListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * listAlertgroupById
     * query alert group list
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/alert-groups/list")
    @Headers({
            "Accept: */*",
    })
    Result listUsingGET1();

    /**
     * queryAlertGroupById
     * QUERY_ALERT_GROUP_BY_ID_NOTES
     *
     * @param id alert group id (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/alert-groups/query?id={id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result queryAlertGroupByIdUsingPOST1(@Param("id") Integer id);

    /**
     * queryAlertGroupById
     * QUERY_ALERT_GROUP_BY_ID_NOTES
     * Note, this is equivalent to the other <code>queryAlertGroupByIdUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryAlertGroupByIdUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>id - alert group id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/alert-groups/query?id={id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result queryAlertGroupByIdUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryAlertGroupByIdUsingPOST1</code> method in a fluent style.
     */
    public static class QueryAlertGroupByIdUsingPOST1QueryParams extends HashMap<String, Object> {
        public QueryAlertGroupByIdUsingPOST1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateAlertgroup
     * update alert group
     *
     * @param alertInstanceIds alert instance ids(string format, multiple instances separated by \&quot;,\&quot;) (required)
     * @param groupName        group name (required)
     * @param id               alert group id (required)
     * @param description      description (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/alert-groups/{id}?alertInstanceIds={alertInstanceIds}&description={description}&groupName={groupName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateAlertgroupUsingPUT1(@Param("alertInstanceIds") String alertInstanceIds, @Param("groupName") String groupName, @Param("id") Integer id, @Param("description") String description);

    /**
     * updateAlertgroup
     * update alert group
     * Note, this is equivalent to the other <code>updateAlertgroupUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateAlertgroupUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          alert group id (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>alertInstanceIds - alert instance ids(string format, multiple instances separated by \&quot;,\&quot;) (required)</li>
     *                    <li>description - description (optional)</li>
     *                    <li>groupName - group name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/alert-groups/{id}?alertInstanceIds={alertInstanceIds}&description={description}&groupName={groupName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateAlertgroupUsingPUT1(@Param("id") Integer id, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateAlertgroupUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateAlertgroupUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateAlertgroupUsingPUT1QueryParams alertInstanceIds(final String value) {
            put("alertInstanceIds", EncodingUtils.encode(value));
            return this;
        }

        public UpdateAlertgroupUsingPUT1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public UpdateAlertgroupUsingPUT1QueryParams groupName(final String value) {
            put("groupName", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * verifyGroupName
     * verify alert group name, check alert group exist or not
     *
     * @param groupName group name (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/alert-groups/verify-name?groupName={groupName}")
    @Headers({
            "Accept: */*",
    })
    Result verifyGroupNameUsingGET1(@Param("groupName") String groupName);

    /**
     * verifyGroupName
     * verify alert group name, check alert group exist or not
     * Note, this is equivalent to the other <code>verifyGroupNameUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyGroupNameUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>groupName - group name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/alert-groups/verify-name?groupName={groupName}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result verifyGroupNameUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyGroupNameUsingGET1</code> method in a fluent style.
     */
    public static class VerifyGroupNameUsingGET1QueryParams extends HashMap<String, Object> {
        public VerifyGroupNameUsingGET1QueryParams groupName(final String value) {
            put("groupName", EncodingUtils.encode(value));
            return this;
        }
    }
}
