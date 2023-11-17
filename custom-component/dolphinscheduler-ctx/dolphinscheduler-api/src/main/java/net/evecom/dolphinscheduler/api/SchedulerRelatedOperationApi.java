package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameFailureStrategy;
import io.swagger.client.model.ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePriority;
import io.swagger.client.model.ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWarningType;
import io.swagger.client.model.Result;
import org.threeten.bp.OffsetDateTime;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface SchedulerRelatedOperationApi extends ApiClient.Api {

    /**
     * createSchedule
     * create schedule
     *
     * @param processDefinitionCode   process definition code (required)
     * @param projectCode             project code (required)
     * @param schedule                schedule (required)
     * @param environmentCode         environment code (optional)
     * @param failureStrategy         failure strategy (optional)
     * @param processInstancePriority process instance priority (optional)
     * @param warningGroupId          warning group id (optional)
     * @param warningType             warning type(sending strategy) (optional)
     * @param workerGroup             workerGroup (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/schedules?environmentCode={environmentCode}&failureStrategy={failureStrategy}&processDefinitionCode={processDefinitionCode}&processInstancePriority={processInstancePriority}&schedule={schedule}&warningGroupId={warningGroupId}&warningType={warningType}&workerGroup={workerGroup}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createScheduleUsingPOST1(@Param("processDefinitionCode") Long processDefinitionCode, @Param("projectCode") Long projectCode, @Param("schedule") String schedule, @Param("environmentCode") Long environmentCode, @Param("failureStrategy") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameFailureStrategy failureStrategy, @Param("processInstancePriority") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePriority processInstancePriority, @Param("warningGroupId") Integer warningGroupId, @Param("warningType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWarningType warningType, @Param("workerGroup") String workerGroup);

    /**
     * createSchedule
     * create schedule
     * Note, this is equivalent to the other <code>createScheduleUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateScheduleUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>environmentCode - environment code (optional)</li>
     *                    <li>failureStrategy - failure strategy (optional)</li>
     *                    <li>processDefinitionCode - process definition code (required)</li>
     *                    <li>processInstancePriority - process instance priority (optional)</li>
     *                    <li>schedule - schedule (required)</li>
     *                    <li>warningGroupId - warning group id (optional)</li>
     *                    <li>warningType - warning type(sending strategy) (optional)</li>
     *                    <li>workerGroup - workerGroup (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/schedules?environmentCode={environmentCode}&failureStrategy={failureStrategy}&processDefinitionCode={processDefinitionCode}&processInstancePriority={processInstancePriority}&schedule={schedule}&warningGroupId={warningGroupId}&warningType={warningType}&workerGroup={workerGroup}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createScheduleUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createScheduleUsingPOST1</code> method in a fluent style.
     */
    public static class CreateScheduleUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateScheduleUsingPOST1QueryParams environmentCode(final Long value) {
            put("environmentCode", EncodingUtils.encode(value));
            return this;
        }

        public CreateScheduleUsingPOST1QueryParams failureStrategy(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameFailureStrategy value) {
            put("failureStrategy", EncodingUtils.encode(value));
            return this;
        }

        public CreateScheduleUsingPOST1QueryParams processDefinitionCode(final Long value) {
            put("processDefinitionCode", EncodingUtils.encode(value));
            return this;
        }

        public CreateScheduleUsingPOST1QueryParams processInstancePriority(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePriority value) {
            put("processInstancePriority", EncodingUtils.encode(value));
            return this;
        }

        public CreateScheduleUsingPOST1QueryParams schedule(final String value) {
            put("schedule", EncodingUtils.encode(value));
            return this;
        }

        public CreateScheduleUsingPOST1QueryParams warningGroupId(final Integer value) {
            put("warningGroupId", EncodingUtils.encode(value));
            return this;
        }

        public CreateScheduleUsingPOST1QueryParams warningType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWarningType value) {
            put("warningType", EncodingUtils.encode(value));
            return this;
        }

        public CreateScheduleUsingPOST1QueryParams workerGroup(final String value) {
            put("workerGroup", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * deleteScheduleById
     * offline schedule
     *
     * @param id           schedule id (required)
     * @param projectCode  project code (required)
     * @param alertGroup   (optional)
     * @param createTime   (optional)
     * @param email        (optional)
     * @param phone        (optional)
     * @param queue        (optional)
     * @param queueName    (optional)
     * @param state        (optional)
     * @param tenantCode   (optional)
     * @param tenantId     (optional)
     * @param timeZone     (optional)
     * @param updateTime   (optional)
     * @param userName     (optional)
     * @param userPassword (optional)
     * @param userType     (optional)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/schedules/{id}?alertGroup={alertGroup}&createTime={createTime}&email={email}&id={id}&phone={phone}&queue={queue}&queueName={queueName}&state={state}&tenantCode={tenantCode}&tenantId={tenantId}&timeZone={timeZone}&updateTime={updateTime}&userName={userName}&userPassword={userPassword}&userType={userType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteScheduleByIdUsingDELETE1(@Param("id") Integer id, @Param("projectCode") Long projectCode, @Param("alertGroup") String alertGroup, @Param("createTime") OffsetDateTime createTime, @Param("email") String email, @Param("phone") String phone, @Param("queue") String queue, @Param("queueName") String queueName, @Param("state") Integer state, @Param("tenantCode") String tenantCode, @Param("tenantId") Integer tenantId, @Param("timeZone") String timeZone, @Param("updateTime") OffsetDateTime updateTime, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userType") String userType);

    /**
     * deleteScheduleById
     * offline schedule
     * Note, this is equivalent to the other <code>deleteScheduleByIdUsingDELETE1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link DeleteScheduleByIdUsingDELETE1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>alertGroup -  (optional)</li>
     *                    <li>createTime -  (optional)</li>
     *                    <li>email -  (optional)</li>
     *                    <li>id - schedule id (required)</li>
     *                    <li>phone -  (optional)</li>
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
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/schedules/{id}?alertGroup={alertGroup}&createTime={createTime}&email={email}&id={id}&phone={phone}&queue={queue}&queueName={queueName}&state={state}&tenantCode={tenantCode}&tenantId={tenantId}&timeZone={timeZone}&updateTime={updateTime}&userName={userName}&userPassword={userPassword}&userType={userType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteScheduleByIdUsingDELETE1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>deleteScheduleByIdUsingDELETE1</code> method in a fluent style.
     */
    public static class DeleteScheduleByIdUsingDELETE1QueryParams extends HashMap<String, Object> {
        public DeleteScheduleByIdUsingDELETE1QueryParams alertGroup(final String value) {
            put("alertGroup", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams createTime(final OffsetDateTime value) {
            put("createTime", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams email(final String value) {
            put("email", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams phone(final String value) {
            put("phone", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams queue(final String value) {
            put("queue", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams queueName(final String value) {
            put("queueName", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams state(final Integer value) {
            put("state", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams tenantId(final Integer value) {
            put("tenantId", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams timeZone(final String value) {
            put("timeZone", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams updateTime(final OffsetDateTime value) {
            put("updateTime", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams userPassword(final String value) {
            put("userPassword", EncodingUtils.encode(value));
            return this;
        }

        public DeleteScheduleByIdUsingDELETE1QueryParams userType(final String value) {
            put("userType", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * offline
     * offline schedule
     *
     * @param id          schedule id (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/schedules/{id}/offline")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result offlineScheduleUsingPOST1(@Param("id") Integer id, @Param("projectCode") Long projectCode);

    /**
     * previewSchedule
     * preview schedule
     *
     * @param schedule schedule (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/schedules/preview?schedule={schedule}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result previewScheduleUsingPOST1(@Param("schedule") String schedule);

    /**
     * previewSchedule
     * preview schedule
     * Note, this is equivalent to the other <code>previewScheduleUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link PreviewScheduleUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>schedule - schedule (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/schedules/preview?schedule={schedule}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result previewScheduleUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>previewScheduleUsingPOST1</code> method in a fluent style.
     */
    public static class PreviewScheduleUsingPOST1QueryParams extends HashMap<String, Object> {
        public PreviewScheduleUsingPOST1QueryParams schedule(final String value) {
            put("schedule", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * online
     * online schedule
     *
     * @param id          schedule id (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/schedules/{id}/online")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result publishScheduleOnlineUsingPOST1(@Param("id") Integer id, @Param("projectCode") Long projectCode);

    /**
     * queryScheduleListPaging
     * query schedule list paging
     *
     * @param pageNo                page no (required)
     * @param pageSize              page size (required)
     * @param projectCode           project code (required)
     * @param processDefinitionCode processDefinitionCode (required)
     * @param searchVal             search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/schedules?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}&processDefinitionCode={processDefinitionCode}")
    @Headers({
            "Accept: */*",
    })
    Result queryScheduleListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("projectCode") Long projectCode, @Param("processDefinitionCode") Long processDefinitionCode, @Param("searchVal") String searchVal);

    /**
     * queryScheduleListPaging
     * query schedule list paging
     * Note, this is equivalent to the other <code>queryScheduleListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryScheduleListPagingUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>searchVal - search val (optional)</li>
     *                    <li>processDefinitionCode - processDefinitionCode (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/schedules?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}&processDefinitionCode={processDefinitionCode}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryScheduleListPagingUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryScheduleListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryScheduleListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryScheduleListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryScheduleListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryScheduleListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }

        public QueryScheduleListPagingUsingGET1QueryParams processDefinitionCode(final Long value) {
            put("processDefinitionCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryScheduleList
     * query schedule list
     *
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/schedules/list")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result queryScheduleListUsingPOST1(@Param("projectCode") Long projectCode);

    /**
     * updateScheduleByProcessDefinitionCode
     * UPDATE_SCHEDULE_BY_PROCESS_DEFINITION_CODE_NOTES
     *
     * @param projectCode             project code (required)
     * @param schedule                schedule (required)
     * @param code                    code (required)
     * @param environmentCode         environment code (optional)
     * @param failureStrategy         failure strategy (optional)
     * @param processInstancePriority process instance priority (optional)
     * @param warningGroupId          warning group id (optional)
     * @param warningType             warning type(sending strategy) (optional)
     * @param workerGroup             work group (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/schedules/update/{code}?environmentCode={environmentCode}&failureStrategy={failureStrategy}&processInstancePriority={processInstancePriority}&schedule={schedule}&warningGroupId={warningGroupId}&warningType={warningType}&workerGroup={workerGroup}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateScheduleByProcessDefinitionCodeUsingPUT1(@Param("projectCode") Long projectCode, @Param("schedule") String schedule, @Param("code") Long code, @Param("environmentCode") Long environmentCode, @Param("failureStrategy") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameFailureStrategy failureStrategy, @Param("processInstancePriority") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePriority processInstancePriority, @Param("warningGroupId") Integer warningGroupId, @Param("warningType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWarningType warningType, @Param("workerGroup") String workerGroup);

    /**
     * updateScheduleByProcessDefinitionCode
     * UPDATE_SCHEDULE_BY_PROCESS_DEFINITION_CODE_NOTES
     * Note, this is equivalent to the other <code>updateScheduleByProcessDefinitionCodeUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateScheduleByProcessDefinitionCodeUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param code        code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>environmentCode - environment code (optional)</li>
     *                    <li>failureStrategy - failure strategy (optional)</li>
     *                    <li>processInstancePriority - process instance priority (optional)</li>
     *                    <li>schedule - schedule (required)</li>
     *                    <li>warningGroupId - warning group id (optional)</li>
     *                    <li>warningType - warning type(sending strategy) (optional)</li>
     *                    <li>workerGroup - work group (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/schedules/update/{code}?environmentCode={environmentCode}&failureStrategy={failureStrategy}&processInstancePriority={processInstancePriority}&schedule={schedule}&warningGroupId={warningGroupId}&warningType={warningType}&workerGroup={workerGroup}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateScheduleByProcessDefinitionCodeUsingPUT1(@Param("projectCode") Long projectCode, @Param("code") Long code, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateScheduleByProcessDefinitionCodeUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateScheduleByProcessDefinitionCodeUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateScheduleByProcessDefinitionCodeUsingPUT1QueryParams environmentCode(final Long value) {
            put("environmentCode", EncodingUtils.encode(value));
            return this;
        }

        public UpdateScheduleByProcessDefinitionCodeUsingPUT1QueryParams failureStrategy(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameFailureStrategy value) {
            put("failureStrategy", EncodingUtils.encode(value));
            return this;
        }

        public UpdateScheduleByProcessDefinitionCodeUsingPUT1QueryParams processInstancePriority(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePriority value) {
            put("processInstancePriority", EncodingUtils.encode(value));
            return this;
        }

        public UpdateScheduleByProcessDefinitionCodeUsingPUT1QueryParams schedule(final String value) {
            put("schedule", EncodingUtils.encode(value));
            return this;
        }

        public UpdateScheduleByProcessDefinitionCodeUsingPUT1QueryParams warningGroupId(final Integer value) {
            put("warningGroupId", EncodingUtils.encode(value));
            return this;
        }

        public UpdateScheduleByProcessDefinitionCodeUsingPUT1QueryParams warningType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWarningType value) {
            put("warningType", EncodingUtils.encode(value));
            return this;
        }

        public UpdateScheduleByProcessDefinitionCodeUsingPUT1QueryParams workerGroup(final String value) {
            put("workerGroup", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateSchedule
     * update schedule
     *
     * @param id                      schedule id (required)
     * @param projectCode             project code (required)
     * @param schedule                schedule (required)
     * @param environmentCode         environment code (optional)
     * @param failureStrategy         failure strategy (optional)
     * @param processInstancePriority process instance priority (optional)
     * @param warningGroupId          warning group id (optional)
     * @param warningType             warning type(sending strategy) (optional)
     * @param workerGroup             work group (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/schedules/{id}?environmentCode={environmentCode}&failureStrategy={failureStrategy}&processInstancePriority={processInstancePriority}&schedule={schedule}&warningGroupId={warningGroupId}&warningType={warningType}&workerGroup={workerGroup}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateScheduleUsingPUT1(@Param("id") Integer id, @Param("projectCode") Long projectCode, @Param("schedule") String schedule, @Param("environmentCode") Long environmentCode, @Param("failureStrategy") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameFailureStrategy failureStrategy, @Param("processInstancePriority") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePriority processInstancePriority, @Param("warningGroupId") Integer warningGroupId, @Param("warningType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWarningType warningType, @Param("workerGroup") String workerGroup);

    /**
     * updateSchedule
     * update schedule
     * Note, this is equivalent to the other <code>updateScheduleUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateScheduleUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          schedule id (required)
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>environmentCode - environment code (optional)</li>
     *                    <li>failureStrategy - failure strategy (optional)</li>
     *                    <li>processInstancePriority - process instance priority (optional)</li>
     *                    <li>schedule - schedule (required)</li>
     *                    <li>warningGroupId - warning group id (optional)</li>
     *                    <li>warningType - warning type(sending strategy) (optional)</li>
     *                    <li>workerGroup - work group (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/schedules/{id}?environmentCode={environmentCode}&failureStrategy={failureStrategy}&processInstancePriority={processInstancePriority}&schedule={schedule}&warningGroupId={warningGroupId}&warningType={warningType}&workerGroup={workerGroup}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateScheduleUsingPUT1(@Param("id") Integer id, @Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateScheduleUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateScheduleUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateScheduleUsingPUT1QueryParams environmentCode(final Long value) {
            put("environmentCode", EncodingUtils.encode(value));
            return this;
        }

        public UpdateScheduleUsingPUT1QueryParams failureStrategy(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameFailureStrategy value) {
            put("failureStrategy", EncodingUtils.encode(value));
            return this;
        }

        public UpdateScheduleUsingPUT1QueryParams processInstancePriority(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePriority value) {
            put("processInstancePriority", EncodingUtils.encode(value));
            return this;
        }

        public UpdateScheduleUsingPUT1QueryParams schedule(final String value) {
            put("schedule", EncodingUtils.encode(value));
            return this;
        }

        public UpdateScheduleUsingPUT1QueryParams warningGroupId(final Integer value) {
            put("warningGroupId", EncodingUtils.encode(value));
            return this;
        }

        public UpdateScheduleUsingPUT1QueryParams warningType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameWarningType value) {
            put("warningType", EncodingUtils.encode(value));
            return this;
        }

        public UpdateScheduleUsingPUT1QueryParams workerGroup(final String value) {
            put("workerGroup", EncodingUtils.encode(value));
            return this;
        }
    }
}
