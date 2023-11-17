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
public interface AnalysisRelatedOperationOfTaskStateApi extends ApiClient.Api {

    /**
     * countCommandState
     * count command state
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/analysis/command-state-count")
    @Headers({
            "Accept: */*",
    })
    Result countCommandStateUsingGET1();

    /**
     * countDefinitionByUser
     * count process definition by user
     *
     * @param projectCode project code (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/analysis/define-user-count?projectCode={projectCode}")
    @Headers({
            "Accept: */*",
    })
    Result countDefinitionByUserUsingGET1(@Param("projectCode") Long projectCode);

    /**
     * countDefinitionByUser
     * count process definition by user
     * Note, this is equivalent to the other <code>countDefinitionByUserUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CountDefinitionByUserUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>projectCode - project code (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/analysis/define-user-count?projectCode={projectCode}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result countDefinitionByUserUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>countDefinitionByUserUsingGET1</code> method in a fluent style.
     */
    public static class CountDefinitionByUserUsingGET1QueryParams extends HashMap<String, Object> {
        public CountDefinitionByUserUsingGET1QueryParams projectCode(final Long value) {
            put("projectCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * countProcessInstanceState
     * count process instance state
     *
     * @param endDate     end date (optional)
     * @param projectCode project code (optional)
     * @param startDate   start date (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/analysis/process-state-count?endDate={endDate}&projectCode={projectCode}&startDate={startDate}")
    @Headers({
            "Accept: */*",
    })
    Result countProcessInstanceStateUsingGET1(@Param("endDate") String endDate, @Param("projectCode") Long projectCode, @Param("startDate") String startDate);

    /**
     * countProcessInstanceState
     * count process instance state
     * Note, this is equivalent to the other <code>countProcessInstanceStateUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CountProcessInstanceStateUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>endDate - end date (optional)</li>
     *                    <li>projectCode - project code (optional)</li>
     *                    <li>startDate - start date (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/analysis/process-state-count?endDate={endDate}&projectCode={projectCode}&startDate={startDate}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result countProcessInstanceStateUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>countProcessInstanceStateUsingGET1</code> method in a fluent style.
     */
    public static class CountProcessInstanceStateUsingGET1QueryParams extends HashMap<String, Object> {
        public CountProcessInstanceStateUsingGET1QueryParams endDate(final String value) {
            put("endDate", EncodingUtils.encode(value));
            return this;
        }

        public CountProcessInstanceStateUsingGET1QueryParams projectCode(final Long value) {
            put("projectCode", EncodingUtils.encode(value));
            return this;
        }

        public CountProcessInstanceStateUsingGET1QueryParams startDate(final String value) {
            put("startDate", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * countQueueState
     * count the running status of the task in the queue
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/analysis/queue-count")
    @Headers({
            "Accept: */*",
    })
    Result countQueueStateUsingGET1();

    /**
     * countTaskState
     * count task state
     *
     * @param endDate     end date (optional)
     * @param projectCode project code (optional)
     * @param startDate   start date (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/analysis/task-state-count?endDate={endDate}&projectCode={projectCode}&startDate={startDate}")
    @Headers({
            "Accept: */*",
    })
    Result countTaskStateUsingGET1(@Param("endDate") String endDate, @Param("projectCode") Long projectCode, @Param("startDate") String startDate);

    /**
     * countTaskState
     * count task state
     * Note, this is equivalent to the other <code>countTaskStateUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CountTaskStateUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>endDate - end date (optional)</li>
     *                    <li>projectCode - project code (optional)</li>
     *                    <li>startDate - start date (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/analysis/task-state-count?endDate={endDate}&projectCode={projectCode}&startDate={startDate}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result countTaskStateUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>countTaskStateUsingGET1</code> method in a fluent style.
     */
    public static class CountTaskStateUsingGET1QueryParams extends HashMap<String, Object> {
        public CountTaskStateUsingGET1QueryParams endDate(final String value) {
            put("endDate", EncodingUtils.encode(value));
            return this;
        }

        public CountTaskStateUsingGET1QueryParams projectCode(final Long value) {
            put("projectCode", EncodingUtils.encode(value));
            return this;
        }

        public CountTaskStateUsingGET1QueryParams startDate(final String value) {
            put("startDate", EncodingUtils.encode(value));
            return this;
        }
    }
}
