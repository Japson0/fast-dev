package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameReleaseState;
import io.swagger.client.model.ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameTaskExecuteType;
import io.swagger.client.model.Result;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface TaskDefinitionRelatedOperationApi extends ApiClient.Api {

    /**
     * saveSingle
     * create single task definition
     *
     * @param processDefinitionCode process definition code (required)
     * @param projectCode           project code (required)
     * @param taskDefinitionJsonObj task definition json (required)
     * @param upstreamCodes         upstream code list (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/task-definition/save-single?processDefinitionCode={processDefinitionCode}&taskDefinitionJsonObj={taskDefinitionJsonObj}&upstreamCodes={upstreamCodes}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createTaskBindsWorkFlowUsingPOST1(@Param("processDefinitionCode") Long processDefinitionCode, @Param("projectCode") Long projectCode, @Param("taskDefinitionJsonObj") String taskDefinitionJsonObj, @Param("upstreamCodes") String upstreamCodes);

    /**
     * saveSingle
     * create single task definition
     * Note, this is equivalent to the other <code>createTaskBindsWorkFlowUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateTaskBindsWorkFlowUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>processDefinitionCode - process definition code (required)</li>
     *                    <li>taskDefinitionJsonObj - task definition json (required)</li>
     *                    <li>upstreamCodes - upstream code list (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/task-definition/save-single?processDefinitionCode={processDefinitionCode}&taskDefinitionJsonObj={taskDefinitionJsonObj}&upstreamCodes={upstreamCodes}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createTaskBindsWorkFlowUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createTaskBindsWorkFlowUsingPOST1</code> method in a fluent style.
     */
    public static class CreateTaskBindsWorkFlowUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateTaskBindsWorkFlowUsingPOST1QueryParams processDefinitionCode(final Long value) {
            put("processDefinitionCode", EncodingUtils.encode(value));
            return this;
        }

        public CreateTaskBindsWorkFlowUsingPOST1QueryParams taskDefinitionJsonObj(final String value) {
            put("taskDefinitionJsonObj", EncodingUtils.encode(value));
            return this;
        }

        public CreateTaskBindsWorkFlowUsingPOST1QueryParams upstreamCodes(final String value) {
            put("upstreamCodes", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * save
     * create task definition
     *
     * @param projectCode        project code (required)
     * @param taskDefinitionJson task definition json (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/task-definition?taskDefinitionJson={taskDefinitionJson}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createTaskDefinitionUsingPOST1(@Param("projectCode") Long projectCode, @Param("taskDefinitionJson") String taskDefinitionJson);

    /**
     * save
     * create task definition
     * Note, this is equivalent to the other <code>createTaskDefinitionUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateTaskDefinitionUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>taskDefinitionJson - task definition json (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/task-definition?taskDefinitionJson={taskDefinitionJson}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createTaskDefinitionUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createTaskDefinitionUsingPOST1</code> method in a fluent style.
     */
    public static class CreateTaskDefinitionUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateTaskDefinitionUsingPOST1QueryParams taskDefinitionJson(final String value) {
            put("taskDefinitionJson", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * deleteTaskDefinition
     * delete task definition by code
     *
     * @param code        task definition code (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/task-definition/{code}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteTaskDefinitionByCodeUsingDELETE1(@Param("code") Long code, @Param("projectCode") Long projectCode);

    /**
     * deleteVersion
     * delete task definition version
     *
     * @param code        task definition code (required)
     * @param projectCode project code (required)
     * @param version     version (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/task-definition/{code}/versions/{version}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteTaskDefinitionVersionUsingDELETE1(@Param("code") Long code, @Param("projectCode") Long projectCode, @Param("version") Integer version);

    /**
     * genTaskCodeList
     * query task code list
     *
     * @param genNum number (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/task-definition/gen-task-codes?genNum={genNum}")
    @Headers({
            "Accept: */*",
    })
    Result genTaskCodeListUsingGET1(@Param("genNum") Integer genNum);

    /**
     * genTaskCodeList
     * query task code list
     * Note, this is equivalent to the other <code>genTaskCodeListUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GenTaskCodeListUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>genNum - number (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/task-definition/gen-task-codes?genNum={genNum}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result genTaskCodeListUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>genTaskCodeListUsingGET1</code> method in a fluent style.
     */
    public static class GenTaskCodeListUsingGET1QueryParams extends HashMap<String, Object> {
        public GenTaskCodeListUsingGET1QueryParams genNum(final Integer value) {
            put("genNum", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryTaskDefinitionByCode
     * query task definition detail
     *
     * @param code        task definition code (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/task-definition/{code}")
    @Headers({
            "Accept: */*",
    })
    Result queryTaskDefinitionDetailUsingGET1(@Param("code") Long code, @Param("projectCode") Long projectCode);

    /**
     * queryTaskDefinitionListPaging
     * query task definition list paging
     *
     * @param pageNo             page no (required)
     * @param pageSize           page size (required)
     * @param projectCode        project code (required)
     * @param searchTaskName     search task name (optional)
     * @param searchWorkflowName search workflow name (optional)
     * @param taskExecuteType    task execution type (optional)
     * @param taskType           task type (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/task-definition?pageNo={pageNo}&pageSize={pageSize}&searchTaskName={searchTaskName}&searchWorkflowName={searchWorkflowName}&taskExecuteType={taskExecuteType}&taskType={taskType}")
    @Headers({
            "Accept: */*",
    })
    Result queryTaskDefinitionListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("projectCode") Long projectCode, @Param("searchTaskName") String searchTaskName, @Param("searchWorkflowName") String searchWorkflowName, @Param("taskExecuteType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameTaskExecuteType taskExecuteType, @Param("taskType") String taskType);

    /**
     * queryTaskDefinitionListPaging
     * query task definition list paging
     * Note, this is equivalent to the other <code>queryTaskDefinitionListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryTaskDefinitionListPagingUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>searchTaskName - search task name (optional)</li>
     *                    <li>searchWorkflowName - search workflow name (optional)</li>
     *                    <li>taskExecuteType - task execution type (optional)</li>
     *                    <li>taskType - task type (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/task-definition?pageNo={pageNo}&pageSize={pageSize}&searchTaskName={searchTaskName}&searchWorkflowName={searchWorkflowName}&taskExecuteType={taskExecuteType}&taskType={taskType}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryTaskDefinitionListPagingUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryTaskDefinitionListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryTaskDefinitionListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryTaskDefinitionListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskDefinitionListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskDefinitionListPagingUsingGET1QueryParams searchTaskName(final String value) {
            put("searchTaskName", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskDefinitionListPagingUsingGET1QueryParams searchWorkflowName(final String value) {
            put("searchWorkflowName", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskDefinitionListPagingUsingGET1QueryParams taskExecuteType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameTaskExecuteType value) {
            put("taskExecuteType", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskDefinitionListPagingUsingGET1QueryParams taskType(final String value) {
            put("taskType", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryVersions
     * query task definition version list
     *
     * @param code        task definition code (required)
     * @param pageNo      page no (required)
     * @param pageSize    page size (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/task-definition/{code}/versions?pageNo={pageNo}&pageSize={pageSize}")
    @Headers({
            "Accept: */*",
    })
    Result queryTaskDefinitionVersionsUsingGET1(@Param("code") Long code, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("projectCode") Long projectCode);

    /**
     * queryVersions
     * query task definition version list
     * Note, this is equivalent to the other <code>queryTaskDefinitionVersionsUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryTaskDefinitionVersionsUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param code        task definition code (required)
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/task-definition/{code}/versions?pageNo={pageNo}&pageSize={pageSize}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryTaskDefinitionVersionsUsingGET1(@Param("code") Long code, @Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryTaskDefinitionVersionsUsingGET1</code> method in a fluent style.
     */
    public static class QueryTaskDefinitionVersionsUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryTaskDefinitionVersionsUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskDefinitionVersionsUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * releaseTaskDefinition
     * release task definition
     *
     * @param code         task definition code (required)
     * @param projectCode  process definition name (required)
     * @param releaseState release process definition (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/task-definition/{code}/release?releaseState={releaseState}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result releaseTaskDefinitionUsingPOST1(@Param("code") Long code, @Param("projectCode") Long projectCode, @Param("releaseState") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameReleaseState releaseState);

    /**
     * releaseTaskDefinition
     * release task definition
     * Note, this is equivalent to the other <code>releaseTaskDefinitionUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ReleaseTaskDefinitionUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param code        task definition code (required)
     * @param projectCode process definition name (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>releaseState - release process definition (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/task-definition/{code}/release?releaseState={releaseState}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result releaseTaskDefinitionUsingPOST1(@Param("code") Long code, @Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>releaseTaskDefinitionUsingPOST1</code> method in a fluent style.
     */
    public static class ReleaseTaskDefinitionUsingPOST1QueryParams extends HashMap<String, Object> {
        public ReleaseTaskDefinitionUsingPOST1QueryParams releaseState(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameReleaseState value) {
            put("releaseState", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * switchVersion
     * switch task definition version
     *
     * @param code        task definition code (required)
     * @param projectCode project code (required)
     * @param version     version (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/task-definition/{code}/versions/{version}")
    @Headers({
            "Accept: */*",
    })
    Result switchTaskDefinitionVersionUsingGET1(@Param("code") Long code, @Param("projectCode") Long projectCode, @Param("version") Integer version);

    /**
     * update
     * update task definition
     *
     * @param code                  task definition code (required)
     * @param projectCode           project code (required)
     * @param taskDefinitionJsonObj task definition json (required)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/task-definition/{code}?taskDefinitionJsonObj={taskDefinitionJsonObj}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateTaskDefinitionUsingPUT1(@Param("code") Long code, @Param("projectCode") Long projectCode, @Param("taskDefinitionJsonObj") String taskDefinitionJsonObj);

    /**
     * update
     * update task definition
     * Note, this is equivalent to the other <code>updateTaskDefinitionUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateTaskDefinitionUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param code        task definition code (required)
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>taskDefinitionJsonObj - task definition json (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/task-definition/{code}?taskDefinitionJsonObj={taskDefinitionJsonObj}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateTaskDefinitionUsingPUT1(@Param("code") Long code, @Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateTaskDefinitionUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateTaskDefinitionUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateTaskDefinitionUsingPUT1QueryParams taskDefinitionJsonObj(final String value) {
            put("taskDefinitionJsonObj", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateWithUpstream
     * update task definition
     *
     * @param code                  task definition code (required)
     * @param projectCode           project code (required)
     * @param taskDefinitionJsonObj task definition json (required)
     * @param upstreamCodes         upstream code list (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/task-definition/{code}/with-upstream?taskDefinitionJsonObj={taskDefinitionJsonObj}&upstreamCodes={upstreamCodes}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateTaskWithUpstreamUsingPUT1(@Param("code") Long code, @Param("projectCode") Long projectCode, @Param("taskDefinitionJsonObj") String taskDefinitionJsonObj, @Param("upstreamCodes") String upstreamCodes);

    /**
     * updateWithUpstream
     * update task definition
     * Note, this is equivalent to the other <code>updateTaskWithUpstreamUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateTaskWithUpstreamUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param code        task definition code (required)
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>taskDefinitionJsonObj - task definition json (required)</li>
     *                    <li>upstreamCodes - upstream code list (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/task-definition/{code}/with-upstream?taskDefinitionJsonObj={taskDefinitionJsonObj}&upstreamCodes={upstreamCodes}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateTaskWithUpstreamUsingPUT1(@Param("code") Long code, @Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateTaskWithUpstreamUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateTaskWithUpstreamUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateTaskWithUpstreamUsingPUT1QueryParams taskDefinitionJsonObj(final String value) {
            put("taskDefinitionJsonObj", EncodingUtils.encode(value));
            return this;
        }

        public UpdateTaskWithUpstreamUsingPUT1QueryParams upstreamCodes(final String value) {
            put("upstreamCodes", EncodingUtils.encode(value));
            return this;
        }
    }
}
