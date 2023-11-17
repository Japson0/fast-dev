package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameTaskExecuteType;
import io.swagger.client.model.ErrorModelNamenamespaceorgApacheDolphinschedulerPluginTaskApiEnumsNameTaskExecutionStatus;
import io.swagger.client.model.Result;
import io.swagger.client.model.Resultobject;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface TaskInstanceRelatedOperationApi extends ApiClient.Api {

    /**
     * force-success
     * force task success
     *
     * @param id          task instance id (required)
     * @param projectCode project code (required)
     * @return Resultobject
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/task-instances/{id}/force-success")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject forceTaskSuccessUsingPOST1(@Param("id") Integer id, @Param("projectCode") Long projectCode);

    /**
     * queryTaskListPaging
     * query task instance list paging
     *
     * @param pageNo                page no (required)
     * @param pageSize              page size (required)
     * @param projectCode           project code (required)
     * @param endDate               end date (optional)
     * @param executorName          executor name (optional)
     * @param host                  ip address of running task (optional)
     * @param processInstanceId     process instance id (optional)
     * @param processInstanceName   process instance name (optional)
     * @param searchVal             search val (optional)
     * @param startDate             start date (optional)
     * @param stateType             runing status for workflow and task nodes (optional)
     * @param taskExecuteType       task execution type (optional)
     * @param taskName              task instance name (optional)
     * @param processDefinitionName processDefinitionName (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/task-instances?endDate={endDate}&executorName={executorName}&host={host}&pageNo={pageNo}&pageSize={pageSize}&processInstanceId={processInstanceId}&processInstanceName={processInstanceName}&searchVal={searchVal}&startDate={startDate}&stateType={stateType}&taskExecuteType={taskExecuteType}&taskName={taskName}&processDefinitionName={processDefinitionName}")
    @Headers({
            "Accept: */*",
    })
    Result queryTaskListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("projectCode") Long projectCode, @Param("endDate") String endDate, @Param("executorName") String executorName, @Param("host") String host, @Param("processInstanceId") Integer processInstanceId, @Param("processInstanceName") String processInstanceName, @Param("searchVal") String searchVal, @Param("startDate") String startDate, @Param("stateType") ErrorModelNamenamespaceorgApacheDolphinschedulerPluginTaskApiEnumsNameTaskExecutionStatus stateType, @Param("taskExecuteType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameTaskExecuteType taskExecuteType, @Param("taskName") String taskName, @Param("processDefinitionName") String processDefinitionName);

    /**
     * queryTaskListPaging
     * query task instance list paging
     * Note, this is equivalent to the other <code>queryTaskListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryTaskListPagingUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>endDate - end date (optional)</li>
     *                    <li>executorName - executor name (optional)</li>
     *                    <li>host - ip address of running task (optional)</li>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>processInstanceId - process instance id (optional)</li>
     *                    <li>processInstanceName - process instance name (optional)</li>
     *                    <li>searchVal - search val (optional)</li>
     *                    <li>startDate - start date (optional)</li>
     *                    <li>stateType - runing status for workflow and task nodes (optional)</li>
     *                    <li>taskExecuteType - task execution type (optional)</li>
     *                    <li>taskName - task instance name (optional)</li>
     *                    <li>processDefinitionName - processDefinitionName (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/task-instances?endDate={endDate}&executorName={executorName}&host={host}&pageNo={pageNo}&pageSize={pageSize}&processInstanceId={processInstanceId}&processInstanceName={processInstanceName}&searchVal={searchVal}&startDate={startDate}&stateType={stateType}&taskExecuteType={taskExecuteType}&taskName={taskName}&processDefinitionName={processDefinitionName}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryTaskListPagingUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryTaskListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryTaskListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryTaskListPagingUsingGET1QueryParams endDate(final String value) {
            put("endDate", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskListPagingUsingGET1QueryParams executorName(final String value) {
            put("executorName", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskListPagingUsingGET1QueryParams host(final String value) {
            put("host", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskListPagingUsingGET1QueryParams processInstanceId(final Integer value) {
            put("processInstanceId", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskListPagingUsingGET1QueryParams processInstanceName(final String value) {
            put("processInstanceName", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskListPagingUsingGET1QueryParams startDate(final String value) {
            put("startDate", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskListPagingUsingGET1QueryParams stateType(final ErrorModelNamenamespaceorgApacheDolphinschedulerPluginTaskApiEnumsNameTaskExecutionStatus value) {
            put("stateType", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskListPagingUsingGET1QueryParams taskExecuteType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameTaskExecuteType value) {
            put("taskExecuteType", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskListPagingUsingGET1QueryParams taskName(final String value) {
            put("taskName", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskListPagingUsingGET1QueryParams processDefinitionName(final String value) {
            put("processDefinitionName", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * stop
     * stop task instance
     *
     * @param id          task instance id (required)
     * @param projectCode project code (required)
     * @return Resultobject
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/task-instances/{id}/stop")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject stopTaskUsingPOST1(@Param("id") Integer id, @Param("projectCode") Long projectCode);

    /**
     * savepoint
     * task savepoint
     *
     * @param id          task instance id (required)
     * @param projectCode project code (required)
     * @return Resultobject
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/task-instances/{id}/savepoint")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject taskSavePointUsingPOST1(@Param("id") Integer id, @Param("projectCode") Long projectCode);
}
