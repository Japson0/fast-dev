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
public interface QueueRelatedOperationApi extends ApiClient.Api {

    /**
     * createQueue
     * create queue
     *
     * @param queue     yarn(hadoop) queue name (required)
     * @param queueName queue name (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/queues?queue={queue}&queueName={queueName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createQueueUsingPOST1(@Param("queue") String queue, @Param("queueName") String queueName);

    /**
     * createQueue
     * create queue
     * Note, this is equivalent to the other <code>createQueueUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateQueueUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>queue - yarn(hadoop) queue name (required)</li>
     *                    <li>queueName - queue name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/queues?queue={queue}&queueName={queueName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createQueueUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createQueueUsingPOST1</code> method in a fluent style.
     */
    public static class CreateQueueUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateQueueUsingPOST1QueryParams queue(final String value) {
            put("queue", EncodingUtils.encode(value));
            return this;
        }

        public CreateQueueUsingPOST1QueryParams queueName(final String value) {
            put("queueName", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryList
     * query queue list
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/queues/list")
    @Headers({
            "Accept: */*",
    })
    Result queryListUsingGET1();

    /**
     * queryQueueListPaging
     * query queue list paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/queues?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Result queryQueueListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryQueueListPaging
     * query queue list paging
     * Note, this is equivalent to the other <code>queryQueueListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryQueueListPagingUsingGET1QueryParams} class that allows for
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
    @RequestLine("GET /dolphinscheduler/queues?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryQueueListPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryQueueListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryQueueListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryQueueListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryQueueListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryQueueListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateQueue
     * update queue
     *
     * @param id        queue id (required)
     * @param queue     yarn(hadoop) queue name (required)
     * @param queueName queue name (required)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/queues/{id}?queue={queue}&queueName={queueName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateQueueUsingPUT1(@Param("id") Integer id, @Param("queue") String queue, @Param("queueName") String queueName);

    /**
     * updateQueue
     * update queue
     * Note, this is equivalent to the other <code>updateQueueUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateQueueUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          queue id (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>queue - yarn(hadoop) queue name (required)</li>
     *                    <li>queueName - queue name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/queues/{id}?queue={queue}&queueName={queueName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateQueueUsingPUT1(@Param("id") Integer id, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateQueueUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateQueueUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateQueueUsingPUT1QueryParams queue(final String value) {
            put("queue", EncodingUtils.encode(value));
            return this;
        }

        public UpdateQueueUsingPUT1QueryParams queueName(final String value) {
            put("queueName", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * verifyQueue
     * verify queue
     *
     * @param queue     yarn(hadoop) queue name (required)
     * @param queueName queue name (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/queues/verify?queue={queue}&queueName={queueName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result verifyQueueUsingPOST1(@Param("queue") String queue, @Param("queueName") String queueName);

    /**
     * verifyQueue
     * verify queue
     * Note, this is equivalent to the other <code>verifyQueueUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyQueueUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>queue - yarn(hadoop) queue name (required)</li>
     *                    <li>queueName - queue name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/queues/verify?queue={queue}&queueName={queueName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result verifyQueueUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyQueueUsingPOST1</code> method in a fluent style.
     */
    public static class VerifyQueueUsingPOST1QueryParams extends HashMap<String, Object> {
        public VerifyQueueUsingPOST1QueryParams queue(final String value) {
            put("queue", EncodingUtils.encode(value));
            return this;
        }

        public VerifyQueueUsingPOST1QueryParams queueName(final String value) {
            put("queueName", EncodingUtils.encode(value));
            return this;
        }
    }
}
