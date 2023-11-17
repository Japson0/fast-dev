package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.*;
import org.threeten.bp.OffsetDateTime;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface ExecutorOperationApi extends ApiClient.Api {

    /**
     * batchExecute
     * batch change state for muliple process instances(Will raise error with specific id when any it cannot be found, and will only show detail error message when some instances change state not as expected)
     *
     * @param executeType        execute type (required)
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
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/executors/batch-execute?alertGroup={alertGroup}&createTime={createTime}&email={email}&executeType={executeType}&id={id}&phone={phone}&processInstanceIds={processInstanceIds}&queue={queue}&queueName={queueName}&state={state}&tenantCode={tenantCode}&tenantId={tenantId}&timeZone={timeZone}&updateTime={updateTime}&userName={userName}&userPassword={userPassword}&userType={userType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result batchExecuteUsingPOST1(@Param("executeType") ErrorModelNamenamespaceorgApacheDolphinschedulerApiEnumsNameExecuteType executeType, @Param("processInstanceIds") String processInstanceIds, @Param("projectCode") Integer projectCode, @Param("alertGroup") String alertGroup, @Param("createTime") OffsetDateTime createTime, @Param("email") String email, @Param("id") Integer id, @Param("phone") String phone, @Param("queue") String queue, @Param("queueName") String queueName, @Param("state") Integer state, @Param("tenantCode") String tenantCode, @Param("tenantId") Integer tenantId, @Param("timeZone") String timeZone, @Param("updateTime") OffsetDateTime updateTime, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userType") String userType);

    /**
     * batchExecute
     * batch change state for muliple process instances(Will raise error with specific id when any it cannot be found, and will only show detail error message when some instances change state not as expected)
     * Note, this is equivalent to the other <code>batchExecuteUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link BatchExecuteUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>alertGroup -  (optional)</li>
     *                    <li>createTime -  (optional)</li>
     *                    <li>email -  (optional)</li>
     *                    <li>executeType - execute type (required)</li>
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
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/executors/batch-execute?alertGroup={alertGroup}&createTime={createTime}&email={email}&executeType={executeType}&id={id}&phone={phone}&processInstanceIds={processInstanceIds}&queue={queue}&queueName={queueName}&state={state}&tenantCode={tenantCode}&tenantId={tenantId}&timeZone={timeZone}&updateTime={updateTime}&userName={userName}&userPassword={userPassword}&userType={userType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result batchExecuteUsingPOST1(@Param("projectCode") Integer projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>batchExecuteUsingPOST1</code> method in a fluent style.
     */
    public static class BatchExecuteUsingPOST1QueryParams extends HashMap<String, Object> {
        public BatchExecuteUsingPOST1QueryParams alertGroup(final String value) {
            put("alertGroup", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams createTime(final OffsetDateTime value) {
            put("createTime", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams email(final String value) {
            put("email", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams executeType(final ErrorModelNamenamespaceorgApacheDolphinschedulerApiEnumsNameExecuteType value) {
            put("executeType", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams phone(final String value) {
            put("phone", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams processInstanceIds(final String value) {
            put("processInstanceIds", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams queue(final String value) {
            put("queue", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams queueName(final String value) {
            put("queueName", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams state(final Integer value) {
            put("state", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams tenantId(final Integer value) {
            put("tenantId", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams timeZone(final String value) {
            put("timeZone", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams updateTime(final OffsetDateTime value) {
            put("updateTime", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams userPassword(final String value) {
            put("userPassword", EncodingUtils.encode(value));
            return this;
        }

        public BatchExecuteUsingPOST1QueryParams userType(final String value) {
            put("userType", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * batchStartProcessInstance
     * batch run process instance(If any processDefinitionCode cannot be found, the failure information is returned and the status is set to failed. The successful task will run normally and will not stop)
     *
     * @param failureStrategy           failure strategy (required)
     * @param processDefinitionCodes    process definition code list (required)
     * @param processInstancePriority   process instance priority (required)
     * @param projectCode               project code (required)
     * @param scheduleTime              schedule time,empty string indicates the current day (required)
     * @param warningGroupId            warning group id (required)
     * @param warningType               warning type(sending strategy) (required)
     * @param complementDependentMode   complement dependent mode (optional)
     * @param dryRun                    dry run (optional)
     * @param environmentCode           environment code (optional)
     * @param execType                  command type (optional)
     * @param expectedParallelismNumber custom parallelism to set the complement task threads (optional)
     * @param runMode                   run mode (optional)
     * @param startNodeList             start node list（node name） (optional)
     * @param taskDependType            task depend type (optional)
     * @param timeout                   timeout (optional)
     * @param workerGroup               work group (optional)
     * @param startParams               start parameters (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/executors/batch-start-process-instance?complementDependentMode={complementDependentMode}&dryRun={dryRun}&environmentCode={environmentCode}&execType={execType}&expectedParallelismNumber={expectedParallelismNumber}&failureStrategy={failureStrategy}&processDefinitionCodes={processDefinitionCodes}&processInstancePriority={processInstancePriority}&runMode={runMode}&scheduleTime={scheduleTime}&startNodeList={startNodeList}&taskDependType={taskDependType}&timeout={timeout}&warningGroupId={warningGroupId}&warningType={warningType}&workerGroup={workerGroup}&startParams={startParams}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result batchStartProcessInstanceUsingPOST1(@Param("failureStrategy") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameFailureStrategy failureStrategy, @Param("processDefinitionCodes") String processDefinitionCodes, @Param("processInstancePriority") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePriority processInstancePriority, @Param("projectCode") Long projectCode, @Param("scheduleTime") String scheduleTime, @Param("warningGroupId") Integer warningGroupId, @Param("warningType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWarningType warningType, @Param("complementDependentMode") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameComplementDependentMode complementDependentMode, @Param("dryRun") Integer dryRun, @Param("environmentCode") Long environmentCode, @Param("execType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameCommandType execType, @Param("expectedParallelismNumber") Integer expectedParallelismNumber, @Param("runMode") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameRunMode runMode, @Param("startNodeList") String startNodeList, @Param("taskDependType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameTaskDependType taskDependType, @Param("timeout") Integer timeout, @Param("workerGroup") String workerGroup, @Param("startParams") String startParams);

    /**
     * batchStartProcessInstance
     * batch run process instance(If any processDefinitionCode cannot be found, the failure information is returned and the status is set to failed. The successful task will run normally and will not stop)
     * Note, this is equivalent to the other <code>batchStartProcessInstanceUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link BatchStartProcessInstanceUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>complementDependentMode - complement dependent mode (optional)</li>
     *                    <li>dryRun - dry run (optional)</li>
     *                    <li>environmentCode - environment code (optional)</li>
     *                    <li>execType - command type (optional)</li>
     *                    <li>expectedParallelismNumber - custom parallelism to set the complement task threads (optional)</li>
     *                    <li>failureStrategy - failure strategy (required)</li>
     *                    <li>processDefinitionCodes - process definition code list (required)</li>
     *                    <li>processInstancePriority - process instance priority (required)</li>
     *                    <li>runMode - run mode (optional)</li>
     *                    <li>scheduleTime - schedule time,empty string indicates the current day (required)</li>
     *                    <li>startNodeList - start node list（node name） (optional)</li>
     *                    <li>taskDependType - task depend type (optional)</li>
     *                    <li>timeout - timeout (optional)</li>
     *                    <li>warningGroupId - warning group id (required)</li>
     *                    <li>warningType - warning type(sending strategy) (required)</li>
     *                    <li>workerGroup - work group (optional)</li>
     *                    <li>startParams - start parameters (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/executors/batch-start-process-instance?complementDependentMode={complementDependentMode}&dryRun={dryRun}&environmentCode={environmentCode}&execType={execType}&expectedParallelismNumber={expectedParallelismNumber}&failureStrategy={failureStrategy}&processDefinitionCodes={processDefinitionCodes}&processInstancePriority={processInstancePriority}&runMode={runMode}&scheduleTime={scheduleTime}&startNodeList={startNodeList}&taskDependType={taskDependType}&timeout={timeout}&warningGroupId={warningGroupId}&warningType={warningType}&workerGroup={workerGroup}&startParams={startParams}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result batchStartProcessInstanceUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>batchStartProcessInstanceUsingPOST1</code> method in a fluent style.
     */
    public static class BatchStartProcessInstanceUsingPOST1QueryParams extends HashMap<String, Object> {
        public BatchStartProcessInstanceUsingPOST1QueryParams complementDependentMode(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameComplementDependentMode value) {
            put("complementDependentMode", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams dryRun(final Integer value) {
            put("dryRun", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams environmentCode(final Long value) {
            put("environmentCode", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams execType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameCommandType value) {
            put("execType", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams expectedParallelismNumber(final Integer value) {
            put("expectedParallelismNumber", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams failureStrategy(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameFailureStrategy value) {
            put("failureStrategy", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams processDefinitionCodes(final String value) {
            put("processDefinitionCodes", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams processInstancePriority(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePriority value) {
            put("processInstancePriority", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams runMode(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameRunMode value) {
            put("runMode", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams scheduleTime(final String value) {
            put("scheduleTime", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams startNodeList(final String value) {
            put("startNodeList", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams taskDependType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameTaskDependType value) {
            put("taskDependType", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams timeout(final Integer value) {
            put("timeout", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams warningGroupId(final Integer value) {
            put("warningGroupId", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams warningType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWarningType value) {
            put("warningType", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams workerGroup(final String value) {
            put("workerGroup", EncodingUtils.encode(value));
            return this;
        }

        public BatchStartProcessInstanceUsingPOST1QueryParams startParams(final String value) {
            put("startParams", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * execute
     * execute action to process instance
     *
     * @param executeType       execute type (required)
     * @param processInstanceId process instance id (required)
     * @param projectCode       project code (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/executors/execute?executeType={executeType}&processInstanceId={processInstanceId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result executeUsingPOST1(@Param("executeType") ErrorModelNamenamespaceorgApacheDolphinschedulerApiEnumsNameExecuteType executeType, @Param("processInstanceId") Integer processInstanceId, @Param("projectCode") Long projectCode);

    /**
     * execute
     * execute action to process instance
     * Note, this is equivalent to the other <code>executeUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ExecuteUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>executeType - execute type (required)</li>
     *                    <li>processInstanceId - process instance id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/executors/execute?executeType={executeType}&processInstanceId={processInstanceId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result executeUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>executeUsingPOST1</code> method in a fluent style.
     */
    public static class ExecuteUsingPOST1QueryParams extends HashMap<String, Object> {
        public ExecuteUsingPOST1QueryParams executeType(final ErrorModelNamenamespaceorgApacheDolphinschedulerApiEnumsNameExecuteType value) {
            put("executeType", EncodingUtils.encode(value));
            return this;
        }

        public ExecuteUsingPOST1QueryParams processInstanceId(final Integer value) {
            put("processInstanceId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryExecutingWorkflow
     * query workflow execution data
     *
     * @param id id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/executors/query-executing-workflow?id={id}")
    @Headers({
            "Accept: */*",
    })
    Result queryExecutingWorkflowUsingGET1(@Param("id") Integer id);

    /**
     * queryExecutingWorkflow
     * query workflow execution data
     * Note, this is equivalent to the other <code>queryExecutingWorkflowUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryExecutingWorkflowUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>id - id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/executors/query-executing-workflow?id={id}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryExecutingWorkflowUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryExecutingWorkflowUsingGET1</code> method in a fluent style.
     */
    public static class QueryExecutingWorkflowUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryExecutingWorkflowUsingGET1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * startCheckProcessDefinition
     * start check process definition
     *
     * @param processDefinitionCode process definition code (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/executors/start-check?processDefinitionCode={processDefinitionCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result startCheckProcessDefinitionUsingPOST1(@Param("processDefinitionCode") Long processDefinitionCode);

    /**
     * startCheckProcessDefinition
     * start check process definition
     * Note, this is equivalent to the other <code>startCheckProcessDefinitionUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link StartCheckProcessDefinitionUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>processDefinitionCode - process definition code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/executors/start-check?processDefinitionCode={processDefinitionCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result startCheckProcessDefinitionUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>startCheckProcessDefinitionUsingPOST1</code> method in a fluent style.
     */
    public static class StartCheckProcessDefinitionUsingPOST1QueryParams extends HashMap<String, Object> {
        public StartCheckProcessDefinitionUsingPOST1QueryParams processDefinitionCode(final Long value) {
            put("processDefinitionCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * startProcessInstance
     * run process instance
     *
     * @param failureStrategy           failure strategy (required)
     * @param processDefinitionCode     process definition code (required)
     * @param processInstancePriority   process instance priority (required)
     * @param projectCode               project code (required)
     * @param scheduleTime              schedule time,empty string indicates the current day (required)
     * @param warningType               warning type(sending strategy) (required)
     * @param complementDependentMode   complement dependent mode (optional)
     * @param dryRun                    dry run (optional)
     * @param environmentCode           environment code (optional)
     * @param execType                  command type (optional)
     * @param expectedParallelismNumber custom parallelism to set the complement task threads (optional)
     * @param runMode                   run mode (optional)
     * @param startNodeList             start node list（node name） (optional)
     * @param taskDependType            task depend type (optional)
     * @param timeout                   timeout (optional)
     * @param warningGroupId            warning group id (optional)
     * @param workerGroup               work group (optional)
     * @param startParams               start parameters (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/executors/start-process-instance?complementDependentMode={complementDependentMode}&dryRun={dryRun}&environmentCode={environmentCode}&execType={execType}&expectedParallelismNumber={expectedParallelismNumber}&failureStrategy={failureStrategy}&processDefinitionCode={processDefinitionCode}&processInstancePriority={processInstancePriority}&runMode={runMode}&scheduleTime={scheduleTime}&startNodeList={startNodeList}&taskDependType={taskDependType}&timeout={timeout}&warningGroupId={warningGroupId}&warningType={warningType}&workerGroup={workerGroup}&startParams={startParams}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result startProcessInstanceUsingPOST1(@Param("failureStrategy") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameFailureStrategy failureStrategy, @Param("processDefinitionCode") Long processDefinitionCode, @Param("processInstancePriority") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePriority processInstancePriority, @Param("projectCode") Long projectCode, @Param("scheduleTime") String scheduleTime, @Param("warningType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWarningType warningType, @Param("complementDependentMode") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameComplementDependentMode complementDependentMode, @Param("dryRun") Integer dryRun, @Param("environmentCode") Long environmentCode, @Param("execType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameCommandType execType, @Param("expectedParallelismNumber") Integer expectedParallelismNumber, @Param("runMode") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameRunMode runMode, @Param("startNodeList") String startNodeList, @Param("taskDependType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameTaskDependType taskDependType, @Param("timeout") Integer timeout, @Param("warningGroupId") Integer warningGroupId, @Param("workerGroup") String workerGroup, @Param("startParams") String startParams);

    /**
     * startProcessInstance
     * run process instance
     * Note, this is equivalent to the other <code>startProcessInstanceUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link StartProcessInstanceUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>complementDependentMode - complement dependent mode (optional)</li>
     *                    <li>dryRun - dry run (optional)</li>
     *                    <li>environmentCode - environment code (optional)</li>
     *                    <li>execType - command type (optional)</li>
     *                    <li>expectedParallelismNumber - custom parallelism to set the complement task threads (optional)</li>
     *                    <li>failureStrategy - failure strategy (required)</li>
     *                    <li>processDefinitionCode - process definition code (required)</li>
     *                    <li>processInstancePriority - process instance priority (required)</li>
     *                    <li>runMode - run mode (optional)</li>
     *                    <li>scheduleTime - schedule time,empty string indicates the current day (required)</li>
     *                    <li>startNodeList - start node list（node name） (optional)</li>
     *                    <li>taskDependType - task depend type (optional)</li>
     *                    <li>timeout - timeout (optional)</li>
     *                    <li>warningGroupId - warning group id (optional)</li>
     *                    <li>warningType - warning type(sending strategy) (required)</li>
     *                    <li>workerGroup - work group (optional)</li>
     *                    <li>startParams - start parameters (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/executors/start-process-instance?complementDependentMode={complementDependentMode}&dryRun={dryRun}&environmentCode={environmentCode}&execType={execType}&expectedParallelismNumber={expectedParallelismNumber}&failureStrategy={failureStrategy}&processDefinitionCode={processDefinitionCode}&processInstancePriority={processInstancePriority}&runMode={runMode}&scheduleTime={scheduleTime}&startNodeList={startNodeList}&taskDependType={taskDependType}&timeout={timeout}&warningGroupId={warningGroupId}&warningType={warningType}&workerGroup={workerGroup}&startParams={startParams}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result startProcessInstanceUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>startProcessInstanceUsingPOST1</code> method in a fluent style.
     */
    public static class StartProcessInstanceUsingPOST1QueryParams extends HashMap<String, Object> {
        public StartProcessInstanceUsingPOST1QueryParams complementDependentMode(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameComplementDependentMode value) {
            put("complementDependentMode", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams dryRun(final Integer value) {
            put("dryRun", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams environmentCode(final Long value) {
            put("environmentCode", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams execType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameCommandType value) {
            put("execType", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams expectedParallelismNumber(final Integer value) {
            put("expectedParallelismNumber", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams failureStrategy(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameFailureStrategy value) {
            put("failureStrategy", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams processDefinitionCode(final Long value) {
            put("processDefinitionCode", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams processInstancePriority(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePriority value) {
            put("processInstancePriority", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams runMode(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameRunMode value) {
            put("runMode", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams scheduleTime(final String value) {
            put("scheduleTime", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams startNodeList(final String value) {
            put("startNodeList", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams taskDependType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameTaskDependType value) {
            put("taskDependType", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams timeout(final Integer value) {
            put("timeout", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams warningGroupId(final Integer value) {
            put("warningGroupId", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams warningType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWarningType value) {
            put("warningType", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams workerGroup(final String value) {
            put("workerGroup", EncodingUtils.encode(value));
            return this;
        }

        public StartProcessInstanceUsingPOST1QueryParams startParams(final String value) {
            put("startParams", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * startTaskInstance
     * run task instance
     *
     * @param projectCode     project code (required)
     * @param version         version (required)
     * @param code            TASK_CODE (required)
     * @param dryRun          dry run (optional)
     * @param environmentCode environment code (optional)
     * @param warningGroupId  warning group id (optional)
     * @param workerGroup     work group (optional)
     * @param startParams     start parameters (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/executors/task-instance/{code}/start?dryRun={dryRun}&environmentCode={environmentCode}&version={version}&warningGroupId={warningGroupId}&workerGroup={workerGroup}&startParams={startParams}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result startStreamTaskInstanceUsingPOST1(@Param("projectCode") Long projectCode, @Param("version") Integer version, @Param("code") Long code, @Param("dryRun") Integer dryRun, @Param("environmentCode") Long environmentCode, @Param("warningGroupId") Integer warningGroupId, @Param("workerGroup") String workerGroup, @Param("startParams") String startParams);

    /**
     * startTaskInstance
     * run task instance
     * Note, this is equivalent to the other <code>startStreamTaskInstanceUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link StartStreamTaskInstanceUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param code        TASK_CODE (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>dryRun - dry run (optional)</li>
     *                    <li>environmentCode - environment code (optional)</li>
     *                    <li>version - version (required)</li>
     *                    <li>warningGroupId - warning group id (optional)</li>
     *                    <li>workerGroup - work group (optional)</li>
     *                    <li>startParams - start parameters (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/executors/task-instance/{code}/start?dryRun={dryRun}&environmentCode={environmentCode}&version={version}&warningGroupId={warningGroupId}&workerGroup={workerGroup}&startParams={startParams}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result startStreamTaskInstanceUsingPOST1(@Param("projectCode") Long projectCode, @Param("code") Long code, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>startStreamTaskInstanceUsingPOST1</code> method in a fluent style.
     */
    public static class StartStreamTaskInstanceUsingPOST1QueryParams extends HashMap<String, Object> {
        public StartStreamTaskInstanceUsingPOST1QueryParams dryRun(final Integer value) {
            put("dryRun", EncodingUtils.encode(value));
            return this;
        }

        public StartStreamTaskInstanceUsingPOST1QueryParams environmentCode(final Long value) {
            put("environmentCode", EncodingUtils.encode(value));
            return this;
        }

        public StartStreamTaskInstanceUsingPOST1QueryParams version(final Integer value) {
            put("version", EncodingUtils.encode(value));
            return this;
        }

        public StartStreamTaskInstanceUsingPOST1QueryParams warningGroupId(final Integer value) {
            put("warningGroupId", EncodingUtils.encode(value));
            return this;
        }

        public StartStreamTaskInstanceUsingPOST1QueryParams workerGroup(final String value) {
            put("workerGroup", EncodingUtils.encode(value));
            return this;
        }

        public StartStreamTaskInstanceUsingPOST1QueryParams startParams(final String value) {
            put("startParams", EncodingUtils.encode(value));
            return this;
        }
    }
}
