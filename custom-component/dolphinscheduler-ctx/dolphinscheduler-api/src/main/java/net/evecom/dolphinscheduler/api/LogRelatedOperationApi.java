package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.ResponseEntity;
import io.swagger.client.model.ResultResponseTaskLog;
import io.swagger.client.model.Resultstring;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface LogRelatedOperationApi extends ApiClient.Api {

    /**
     * downloadTaskLog
     * download task instance log
     *
     * @param taskInstanceId task instance id (required)
     * @return ResponseEntity
     */
    @RequestLine("GET /dolphinscheduler/log/download-log?taskInstanceId={taskInstanceId}")
    @Headers({
            "Accept: */*",
    })
    ResponseEntity downloadTaskLogUsingGET1(@Param("taskInstanceId") Integer taskInstanceId);

    /**
     * downloadTaskLog
     * download task instance log
     * Note, this is equivalent to the other <code>downloadTaskLogUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link DownloadTaskLogUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>taskInstanceId - task instance id (required)</li>
     *                    </ul>
     * @return ResponseEntity
     */
    @RequestLine("GET /dolphinscheduler/log/download-log?taskInstanceId={taskInstanceId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    ResponseEntity downloadTaskLogUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>downloadTaskLogUsingGET1</code> method in a fluent style.
     */
    public static class DownloadTaskLogUsingGET1QueryParams extends HashMap<String, Object> {
        public DownloadTaskLogUsingGET1QueryParams taskInstanceId(final Integer value) {
            put("taskInstanceId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * downloadTaskLogInSpecifiedProject
     * download task instance log in specified project
     *
     * @param projectCode    project code (required)
     * @param taskInstanceId task instance id (required)
     * @return ResponseEntity
     */
    @RequestLine("GET /dolphinscheduler/log/{projectCode}/download-log?taskInstanceId={taskInstanceId}")
    @Headers({
            "Accept: */*",
    })
    ResponseEntity downloadTaskLogUsingGET3(@Param("projectCode") Long projectCode, @Param("taskInstanceId") Integer taskInstanceId);

    /**
     * downloadTaskLogInSpecifiedProject
     * download task instance log in specified project
     * Note, this is equivalent to the other <code>downloadTaskLogUsingGET3</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link DownloadTaskLogUsingGET3QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>taskInstanceId - task instance id (required)</li>
     *                    </ul>
     * @return ResponseEntity
     */
    @RequestLine("GET /dolphinscheduler/log/{projectCode}/download-log?taskInstanceId={taskInstanceId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    ResponseEntity downloadTaskLogUsingGET3(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>downloadTaskLogUsingGET3</code> method in a fluent style.
     */
    public static class DownloadTaskLogUsingGET3QueryParams extends HashMap<String, Object> {
        public DownloadTaskLogUsingGET3QueryParams taskInstanceId(final Integer value) {
            put("taskInstanceId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryLogInSpecifiedProject
     * query task instance log in specified project
     *
     * @param limit          limit (required)
     * @param projectCode    project code (required)
     * @param skipLineNum    skip line num (required)
     * @param taskInstanceId task instance id (required)
     * @return Resultstring
     */
    @RequestLine("GET /dolphinscheduler/log/{projectCode}/detail?limit={limit}&skipLineNum={skipLineNum}&taskInstanceId={taskInstanceId}")
    @Headers({
            "Accept: */*",
    })
    Resultstring queryLogUsingGET1(@Param("limit") Integer limit, @Param("projectCode") Long projectCode, @Param("skipLineNum") Integer skipLineNum, @Param("taskInstanceId") Integer taskInstanceId);

    /**
     * queryLogInSpecifiedProject
     * query task instance log in specified project
     * Note, this is equivalent to the other <code>queryLogUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryLogUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>limit - limit (required)</li>
     *                    <li>skipLineNum - skip line num (required)</li>
     *                    <li>taskInstanceId - task instance id (required)</li>
     *                    </ul>
     * @return Resultstring
     */
    @RequestLine("GET /dolphinscheduler/log/{projectCode}/detail?limit={limit}&skipLineNum={skipLineNum}&taskInstanceId={taskInstanceId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Resultstring queryLogUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryLogUsingGET1</code> method in a fluent style.
     */
    public static class QueryLogUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryLogUsingGET1QueryParams limit(final Integer value) {
            put("limit", EncodingUtils.encode(value));
            return this;
        }

        public QueryLogUsingGET1QueryParams skipLineNum(final Integer value) {
            put("skipLineNum", EncodingUtils.encode(value));
            return this;
        }

        public QueryLogUsingGET1QueryParams taskInstanceId(final Integer value) {
            put("taskInstanceId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryLog
     * query task instance log
     *
     * @param limit          limit (required)
     * @param skipLineNum    skip line num (required)
     * @param taskInstanceId task instance id (required)
     * @return ResultResponseTaskLog
     */
    @RequestLine("GET /dolphinscheduler/log/detail?limit={limit}&skipLineNum={skipLineNum}&taskInstanceId={taskInstanceId}")
    @Headers({
            "Accept: */*",
    })
    ResultResponseTaskLog queryLogUsingGET3(@Param("limit") Integer limit, @Param("skipLineNum") Integer skipLineNum, @Param("taskInstanceId") Integer taskInstanceId);

    /**
     * queryLog
     * query task instance log
     * Note, this is equivalent to the other <code>queryLogUsingGET3</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryLogUsingGET3QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>limit - limit (required)</li>
     *                    <li>skipLineNum - skip line num (required)</li>
     *                    <li>taskInstanceId - task instance id (required)</li>
     *                    </ul>
     * @return ResultResponseTaskLog
     */
    @RequestLine("GET /dolphinscheduler/log/detail?limit={limit}&skipLineNum={skipLineNum}&taskInstanceId={taskInstanceId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    ResultResponseTaskLog queryLogUsingGET3(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryLogUsingGET3</code> method in a fluent style.
     */
    public static class QueryLogUsingGET3QueryParams extends HashMap<String, Object> {
        public QueryLogUsingGET3QueryParams limit(final Integer value) {
            put("limit", EncodingUtils.encode(value));
            return this;
        }

        public QueryLogUsingGET3QueryParams skipLineNum(final Integer value) {
            put("skipLineNum", EncodingUtils.encode(value));
            return this;
        }

        public QueryLogUsingGET3QueryParams taskInstanceId(final Integer value) {
            put("taskInstanceId", EncodingUtils.encode(value));
            return this;
        }
    }
}
