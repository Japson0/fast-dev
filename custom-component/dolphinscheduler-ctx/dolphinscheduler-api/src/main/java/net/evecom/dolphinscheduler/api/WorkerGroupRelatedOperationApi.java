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
public interface WorkerGroupRelatedOperationApi extends ApiClient.Api {

    /**
     * deleteWorkerGroupById
     * delete worker group by id
     *
     * @param id worker server group id (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/worker-groups/{id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteWorkerGroupByIdUsingDELETE1(@Param("id") Integer id);

    /**
     * queryAllWorkerGroupsPaging
     * query worker group paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/worker-groups?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Result queryAllWorkerGroupsPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryAllWorkerGroupsPaging
     * query worker group paging
     * Note, this is equivalent to the other <code>queryAllWorkerGroupsPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryAllWorkerGroupsPagingUsingGET1QueryParams} class that allows for
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
    @RequestLine("GET /dolphinscheduler/worker-groups?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryAllWorkerGroupsPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryAllWorkerGroupsPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryAllWorkerGroupsPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryAllWorkerGroupsPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryAllWorkerGroupsPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryAllWorkerGroupsPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryAllWorkerGroups
     * query worker group list
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/worker-groups/all")
    @Headers({
            "Accept: */*",
    })
    Result queryAllWorkerGroupsUsingGET1();

    /**
     * queryWorkerAddressList
     * query worker address list
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/worker-groups/worker-address-list")
    @Headers({
            "Accept: */*",
    })
    Result queryWorkerAddressListUsingGET1();

    /**
     * saveWorkerGroup
     * create worker group
     *
     * @param addrList        worker address list (required)
     * @param name            worker group name (required)
     * @param description     WORKER_DESC (optional)
     * @param id              worker server group id (optional)
     * @param otherParamsJson WORKER_PARMS_JSON (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/worker-groups?addrList={addrList}&description={description}&id={id}&name={name}&otherParamsJson={otherParamsJson}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result saveWorkerGroupUsingPOST1(@Param("addrList") String addrList, @Param("name") String name, @Param("description") String description, @Param("id") Integer id, @Param("otherParamsJson") String otherParamsJson);

    /**
     * saveWorkerGroup
     * create worker group
     * Note, this is equivalent to the other <code>saveWorkerGroupUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link SaveWorkerGroupUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>addrList - worker address list (required)</li>
     *                    <li>description - WORKER_DESC (optional)</li>
     *                    <li>id - worker server group id (optional)</li>
     *                    <li>name - worker group name (required)</li>
     *                    <li>otherParamsJson - WORKER_PARMS_JSON (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/worker-groups?addrList={addrList}&description={description}&id={id}&name={name}&otherParamsJson={otherParamsJson}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result saveWorkerGroupUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>saveWorkerGroupUsingPOST1</code> method in a fluent style.
     */
    public static class SaveWorkerGroupUsingPOST1QueryParams extends HashMap<String, Object> {
        public SaveWorkerGroupUsingPOST1QueryParams addrList(final String value) {
            put("addrList", EncodingUtils.encode(value));
            return this;
        }

        public SaveWorkerGroupUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public SaveWorkerGroupUsingPOST1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }

        public SaveWorkerGroupUsingPOST1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }

        public SaveWorkerGroupUsingPOST1QueryParams otherParamsJson(final String value) {
            put("otherParamsJson", EncodingUtils.encode(value));
            return this;
        }
    }
}
