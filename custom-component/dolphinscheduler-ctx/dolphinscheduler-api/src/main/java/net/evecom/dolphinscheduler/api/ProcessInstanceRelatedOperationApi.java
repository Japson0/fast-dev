package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWorkflowExecutionStatus;
import io.swagger.client.model.Result;
import io.swagger.client.model.ResultProcessInstance;
import org.threeten.bp.OffsetDateTime;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface ProcessInstanceRelatedOperationApi extends ApiClient.Api {

    /**
     * batchDeleteProcessInstanceByIds
     * batch delete process instance by process ids
     *
     * @param processInstanceIds process_instance ids, delimiter by \&quot;,\&quot; if more than one id (required)
     * @param projectCode        project code (required)
     * @param alertGroup         (optional)
     * @param createTime         (optional)
     * @param email              (optional)
     * @param id                 (optional)
     * @param phone              (optional)
     * @param queue              (optional)
     * @param queueName          (optional)
     * @param state              (optional)
     * @param tenantCode         (optional)
     * @param tenantId           (optional)
     * @param timeZone           (optional)
     * @param updateTime         (optional)
     * @param userName           (optional)
     * @param userPassword       (optional)
     * @param userType           (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-instances/batch-delete?alertGroup={alertGroup}&createTime={createTime}&email={email}&id={id}&phone={phone}&processInstanceIds={processInstanceIds}&queue={queue}&queueName={queueName}&state={state}&tenantCode={tenantCode}&tenantId={tenantId}&timeZone={timeZone}&updateTime={updateTime}&userName={userName}&userPassword={userPassword}&userType={userType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result batchDeleteProcessInstanceByIdsUsingPOST1(@Param("processInstanceIds") String processInstanceIds, @Param("projectCode") Integer projectCode, @Param("alertGroup") String alertGroup, @Param("createTime") OffsetDateTime createTime, @Param("email") String email, @Param("id") Integer id, @Param("phone") String phone, @Param("queue") String queue, @Param("queueName") String queueName, @Param("state") Integer state, @Param("tenantCode") String tenantCode, @Param("tenantId") Integer tenantId, @Param("timeZone") String timeZone, @Param("updateTime") OffsetDateTime updateTime, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userType") String userType);

    /**
     * batchDeleteProcessInstanceByIds
     * batch delete process instance by process ids
     * Note, this is equivalent to the other <code>batchDeleteProcessInstanceByIdsUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>alertGroup -  (optional)</li>
     *                    <li>createTime -  (optional)</li>
     *                    <li>email -  (optional)</li>
     *                    <li>id -  (optional)</li>
     *                    <li>phone -  (optional)</li>
     *                    <li>processInstanceIds - process_instance ids, delimiter by \&quot;,\&quot; if more than one id (required)</li>
     *                    <li>queue -  (optional)</li>
     *                    <li>queueName -  (optional)</li>
     *                    <li>state -  (optional)</li>
     *                    <li>tenantCode -  (optional)</li>
     *                    <li>tenantId -  (optional)</li>
     *                    <li>timeZone -  (optional)</li>
     *                    <li>updateTime -  (optional)</li>
     *                    <li>userName -  (optional)</li>
     *                    <li>userPassword -  (optional)</li>
     *                    <li>userType -  (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-instances/batch-delete?alertGroup={alertGroup}&createTime={createTime}&email={email}&id={id}&phone={phone}&processInstanceIds={processInstanceIds}&queue={queue}&queueName={queueName}&state={state}&tenantCode={tenantCode}&tenantId={tenantId}&timeZone={timeZone}&updateTime={updateTime}&userName={userName}&userPassword={userPassword}&userType={userType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result batchDeleteProcessInstanceByIdsUsingPOST1(@Param("projectCode") Integer projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>batchDeleteProcessInstanceByIdsUsingPOST1</code> method in a fluent style.
     */
    public static class BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams extends HashMap<String, Object> {
        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams alertGroup(final String value) {
            put("alertGroup", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams createTime(final OffsetDateTime value) {
            put("createTime", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams email(final String value) {
            put("email", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams phone(final String value) {
            put("phone", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams processInstanceIds(final String value) {
            put("processInstanceIds", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams queue(final String value) {
            put("queue", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams queueName(final String value) {
            put("queueName", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams state(final Integer value) {
            put("state", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams tenantId(final Integer value) {
            put("tenantId", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams timeZone(final String value) {
            put("timeZone", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams updateTime(final OffsetDateTime value) {
            put("updateTime", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams userPassword(final String value) {
            put("userPassword", EncodingUtils.encode(value));
            return this;
        }

        public BatchDeleteProcessInstanceByIdsUsingPOST1QueryParams userType(final String value) {
            put("userType", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * deleteProcessInstanceById
     * delete process instance by process instance id
     *
     * @param id          process instance id (required)
     * @param projectCode project code (required)
     * @return ResultProcessInstance
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/process-instances/{id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    ResultProcessInstance deleteProcessInstanceByIdUsingDELETE1(@Param("id") Integer id, @Param("projectCode") Long projectCode);

    /**
     * queryParentInstanceBySubId
     * query parent process instance info by sub process instance id
     *
     * @param projectCode project code (required)
     * @param subId       sub process instance id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-instances/query-parent-by-sub?subId={subId}")
    @Headers({
            "Accept: */*",
    })
    Result queryParentInstanceBySubIdUsingGET1(@Param("projectCode") Long projectCode, @Param("subId") Integer subId);

    /**
     * queryParentInstanceBySubId
     * query parent process instance info by sub process instance id
     * Note, this is equivalent to the other <code>queryParentInstanceBySubIdUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryParentInstanceBySubIdUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>subId - sub process instance id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-instances/query-parent-by-sub?subId={subId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryParentInstanceBySubIdUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryParentInstanceBySubIdUsingGET1</code> method in a fluent style.
     */
    public static class QueryParentInstanceBySubIdUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryParentInstanceBySubIdUsingGET1QueryParams subId(final Integer value) {
            put("subId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryProcessInstanceById
     * query process instance by process instance id
     *
     * @param id          process instance id (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-instances/{id}")
    @Headers({
            "Accept: */*",
    })
    Result queryProcessInstanceByIdUsingGET1(@Param("id") Integer id, @Param("projectCode") Long projectCode);

    /**
     * queryProcessInstanceListPaging
     * query process instance list
     *
     * @param pageNo            page no (required)
     * @param pageSize          page size (required)
     * @param projectCode       project code (required)
     * @param endDate           end date (optional)
     * @param executorName      executor name (optional)
     * @param host              ip address of running task (optional)
     * @param processDefineCode process definition code (optional)
     * @param searchVal         search val (optional)
     * @param startDate         start date (optional)
     * @param stateType         runing status for workflow and task nodes (optional)
     * @param otherParamsJson   otherParamsJson (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-instances?endDate={endDate}&executorName={executorName}&host={host}&pageNo={pageNo}&pageSize={pageSize}&processDefineCode={processDefineCode}&searchVal={searchVal}&startDate={startDate}&stateType={stateType}&otherParamsJson={otherParamsJson}")
    @Headers({
            "Accept: */*",
    })
    Result queryProcessInstanceListUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("projectCode") Long projectCode, @Param("endDate") String endDate, @Param("executorName") String executorName, @Param("host") String host, @Param("processDefineCode") Long processDefineCode, @Param("searchVal") String searchVal, @Param("startDate") String startDate, @Param("stateType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWorkflowExecutionStatus stateType, @Param("otherParamsJson") String otherParamsJson);

    /**
     * queryProcessInstanceListPaging
     * query process instance list
     * Note, this is equivalent to the other <code>queryProcessInstanceListUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryProcessInstanceListUsingGET1QueryParams} class that allows for
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
     *                    <li>processDefineCode - process definition code (optional)</li>
     *                    <li>searchVal - search val (optional)</li>
     *                    <li>startDate - start date (optional)</li>
     *                    <li>stateType - runing status for workflow and task nodes (optional)</li>
     *                    <li>otherParamsJson - otherParamsJson (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-instances?endDate={endDate}&executorName={executorName}&host={host}&pageNo={pageNo}&pageSize={pageSize}&processDefineCode={processDefineCode}&searchVal={searchVal}&startDate={startDate}&stateType={stateType}&otherParamsJson={otherParamsJson}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryProcessInstanceListUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryProcessInstanceListUsingGET1</code> method in a fluent style.
     */
    public static class QueryProcessInstanceListUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryProcessInstanceListUsingGET1QueryParams endDate(final String value) {
            put("endDate", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessInstanceListUsingGET1QueryParams executorName(final String value) {
            put("executorName", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessInstanceListUsingGET1QueryParams host(final String value) {
            put("host", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessInstanceListUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessInstanceListUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessInstanceListUsingGET1QueryParams processDefineCode(final Long value) {
            put("processDefineCode", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessInstanceListUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessInstanceListUsingGET1QueryParams startDate(final String value) {
            put("startDate", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessInstanceListUsingGET1QueryParams stateType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWorkflowExecutionStatus value) {
            put("stateType", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessInstanceListUsingGET1QueryParams otherParamsJson(final String value) {
            put("otherParamsJson", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * querySubProcessInstanceByTaskCode
     * QUERY_SUBPROCESS_INSTANCE_BY_TASK_CODE_NOTES
     *
     * @param projectCode project code (required)
     * @param taskId      taskId (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-instances/query-sub-by-parent?taskId={taskId}")
    @Headers({
            "Accept: */*",
    })
    Result querySubProcessInstanceByTaskIdUsingGET1(@Param("projectCode") Long projectCode, @Param("taskId") Integer taskId);

    /**
     * querySubProcessInstanceByTaskCode
     * QUERY_SUBPROCESS_INSTANCE_BY_TASK_CODE_NOTES
     * Note, this is equivalent to the other <code>querySubProcessInstanceByTaskIdUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QuerySubProcessInstanceByTaskIdUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>taskId - taskId (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-instances/query-sub-by-parent?taskId={taskId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result querySubProcessInstanceByTaskIdUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>querySubProcessInstanceByTaskIdUsingGET1</code> method in a fluent style.
     */
    public static class QuerySubProcessInstanceByTaskIdUsingGET1QueryParams extends HashMap<String, Object> {
        public QuerySubProcessInstanceByTaskIdUsingGET1QueryParams taskId(final Integer value) {
            put("taskId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryTaskListByProcessId
     * query task list by process instance id
     *
     * @param id          process instance id (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-instances/{id}/tasks")
    @Headers({
            "Accept: */*",
    })
    Result queryTaskListByProcessIdUsingGET1(@Param("id") Integer id, @Param("projectCode") Long projectCode);

    /**
     * queryTopNLongestRunningProcessInstance
     * query topN longest running process instance
     *
     * @param endTime     process instance end time (required)
     * @param projectCode project code (required)
     * @param size        process instance size (required)
     * @param startTime   process instance start time (required)
     * @return ResultProcessInstance
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-instances/top-n?endTime={endTime}&size={size}&startTime={startTime}")
    @Headers({
            "Accept: */*",
    })
    ResultProcessInstance queryTopNLongestRunningProcessInstanceUsingGET1(@Param("endTime") String endTime, @Param("projectCode") Long projectCode, @Param("size") Integer size, @Param("startTime") String startTime);

    /**
     * queryTopNLongestRunningProcessInstance
     * query topN longest running process instance
     * Note, this is equivalent to the other <code>queryTopNLongestRunningProcessInstanceUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryTopNLongestRunningProcessInstanceUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>endTime - process instance end time (required)</li>
     *                    <li>size - process instance size (required)</li>
     *                    <li>startTime - process instance start time (required)</li>
     *                    </ul>
     * @return ResultProcessInstance
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-instances/top-n?endTime={endTime}&size={size}&startTime={startTime}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    ResultProcessInstance queryTopNLongestRunningProcessInstanceUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryTopNLongestRunningProcessInstanceUsingGET1</code> method in a fluent style.
     */
    public static class QueryTopNLongestRunningProcessInstanceUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryTopNLongestRunningProcessInstanceUsingGET1QueryParams endTime(final String value) {
            put("endTime", EncodingUtils.encode(value));
            return this;
        }

        public QueryTopNLongestRunningProcessInstanceUsingGET1QueryParams size(final Integer value) {
            put("size", EncodingUtils.encode(value));
            return this;
        }

        public QueryTopNLongestRunningProcessInstanceUsingGET1QueryParams startTime(final String value) {
            put("startTime", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateProcessInstance
     * update process instance
     *
     * @param id                 process instance id (required)
     * @param projectCode        project code (required)
     * @param syncDefine         update the information of the process instance to the process definition (required)
     * @param taskDefinitionJson task definition json (required)
     * @param taskRelationJson   TASK_RELATION_JSON (required)
     * @param tenantCode         os tenant code (required)
     * @param globalParams       PROCESS_GLOBAL_PARAMS (optional)
     * @param locations          process instance node locations info (json format) (optional)
     * @param scheduleTime       schedule time,empty string indicates the current day (optional)
     * @param timeout            PROCESS_TIMEOUT (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/process-instances/{id}?globalParams={globalParams}&locations={locations}&scheduleTime={scheduleTime}&syncDefine={syncDefine}&taskDefinitionJson={taskDefinitionJson}&taskRelationJson={taskRelationJson}&tenantCode={tenantCode}&timeout={timeout}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateProcessInstanceUsingPUT1(@Param("id") Integer id, @Param("projectCode") Long projectCode, @Param("syncDefine") Boolean syncDefine, @Param("taskDefinitionJson") String taskDefinitionJson, @Param("taskRelationJson") String taskRelationJson, @Param("tenantCode") String tenantCode, @Param("globalParams") String globalParams, @Param("locations") String locations, @Param("scheduleTime") String scheduleTime, @Param("timeout") Integer timeout);

    /**
     * updateProcessInstance
     * update process instance
     * Note, this is equivalent to the other <code>updateProcessInstanceUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateProcessInstanceUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          process instance id (required)
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>globalParams - PROCESS_GLOBAL_PARAMS (optional)</li>
     *                    <li>locations - process instance node locations info (json format) (optional)</li>
     *                    <li>scheduleTime - schedule time,empty string indicates the current day (optional)</li>
     *                    <li>syncDefine - update the information of the process instance to the process definition (required)</li>
     *                    <li>taskDefinitionJson - task definition json (required)</li>
     *                    <li>taskRelationJson - TASK_RELATION_JSON (required)</li>
     *                    <li>tenantCode - os tenant code (required)</li>
     *                    <li>timeout - PROCESS_TIMEOUT (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/process-instances/{id}?globalParams={globalParams}&locations={locations}&scheduleTime={scheduleTime}&syncDefine={syncDefine}&taskDefinitionJson={taskDefinitionJson}&taskRelationJson={taskRelationJson}&tenantCode={tenantCode}&timeout={timeout}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateProcessInstanceUsingPUT1(@Param("id") Integer id, @Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateProcessInstanceUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateProcessInstanceUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateProcessInstanceUsingPUT1QueryParams globalParams(final String value) {
            put("globalParams", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessInstanceUsingPUT1QueryParams locations(final String value) {
            put("locations", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessInstanceUsingPUT1QueryParams scheduleTime(final String value) {
            put("scheduleTime", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessInstanceUsingPUT1QueryParams syncDefine(final Boolean value) {
            put("syncDefine", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessInstanceUsingPUT1QueryParams taskDefinitionJson(final String value) {
            put("taskDefinitionJson", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessInstanceUsingPUT1QueryParams taskRelationJson(final String value) {
            put("taskRelationJson", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessInstanceUsingPUT1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessInstanceUsingPUT1QueryParams timeout(final Integer value) {
            put("timeout", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * vieGanttTree
     * view gantt
     *
     * @param id          process instance id (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-instances/{id}/view-gantt")
    @Headers({
            "Accept: */*",
    })
    Result viewTreeUsingGET3(@Param("id") Integer id, @Param("projectCode") Long projectCode);

    /**
     * viewVariables
     * query process instance global variables and local variables
     *
     * @param id          process instance id (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-instances/{id}/view-variables")
    @Headers({
            "Accept: */*",
    })
    Result viewVariablesUsingGET3(@Param("id") Integer id, @Param("projectCode") Long projectCode);
}
