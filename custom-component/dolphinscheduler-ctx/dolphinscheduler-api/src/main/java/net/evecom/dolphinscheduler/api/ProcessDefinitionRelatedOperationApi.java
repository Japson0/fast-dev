package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameReleaseState;
import io.swagger.client.model.Result;
import io.swagger.client.model.ResultPageInfoProcessDefinition;
import io.swagger.client.model.ResultProcessDefinition;
import org.threeten.bp.OffsetDateTime;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface ProcessDefinitionRelatedOperationApi extends ApiClient.Api {

    /**
     * batchDeleteByCodes
     * batch delete process definition by process definition ids
     *
     * @param codes       process definition code (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/batch-delete?codes={codes}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result batchDeleteProcessDefinitionByCodesUsingPOST1(@Param("codes") String codes, @Param("projectCode") Long projectCode);

    /**
     * batchDeleteByCodes
     * batch delete process definition by process definition ids
     * Note, this is equivalent to the other <code>batchDeleteProcessDefinitionByCodesUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link BatchDeleteProcessDefinitionByCodesUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>codes - process definition code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/batch-delete?codes={codes}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result batchDeleteProcessDefinitionByCodesUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>batchDeleteProcessDefinitionByCodesUsingPOST1</code> method in a fluent style.
     */
    public static class BatchDeleteProcessDefinitionByCodesUsingPOST1QueryParams extends HashMap<String, Object> {
        public BatchDeleteProcessDefinitionByCodesUsingPOST1QueryParams codes(final String value) {
            put("codes", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * batchExportByCodes
     * batch export process definition by code list
     *
     * @param codes       process definition code (required)
     * @param projectCode project code (required)
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/batch-export?codes={codes}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    void batchExportProcessDefinitionByCodesUsingPOST1(@Param("codes") String codes, @Param("projectCode") Long projectCode);

    /**
     * batchExportByCodes
     * batch export process definition by code list
     * Note, this is equivalent to the other <code>batchExportProcessDefinitionByCodesUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link BatchExportProcessDefinitionByCodesUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>codes - process definition code (required)</li>
     *                    </ul>
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/batch-export?codes={codes}")
    @Headers({
            "Content-Type: application/json",
    })
    void batchExportProcessDefinitionByCodesUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>batchExportProcessDefinitionByCodesUsingPOST1</code> method in a fluent style.
     */
    public static class BatchExportProcessDefinitionByCodesUsingPOST1QueryParams extends HashMap<String, Object> {
        public BatchExportProcessDefinitionByCodesUsingPOST1QueryParams codes(final String value) {
            put("codes", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * batchCopyByCodes
     * copy process definition notes
     *
     * @param codes             PROCESS_DEFINITION_CODES (required)
     * @param projectCode       project code (required)
     * @param targetProjectCode TARGET_PROJECT_CODE (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/batch-copy?codes={codes}&targetProjectCode={targetProjectCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result copyProcessDefinitionUsingPOST1(@Param("codes") String codes, @Param("projectCode") Long projectCode, @Param("targetProjectCode") Long targetProjectCode);

    /**
     * batchCopyByCodes
     * copy process definition notes
     * Note, this is equivalent to the other <code>copyProcessDefinitionUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CopyProcessDefinitionUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>codes - PROCESS_DEFINITION_CODES (required)</li>
     *                    <li>targetProjectCode - TARGET_PROJECT_CODE (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/batch-copy?codes={codes}&targetProjectCode={targetProjectCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result copyProcessDefinitionUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>copyProcessDefinitionUsingPOST1</code> method in a fluent style.
     */
    public static class CopyProcessDefinitionUsingPOST1QueryParams extends HashMap<String, Object> {
        public CopyProcessDefinitionUsingPOST1QueryParams codes(final String value) {
            put("codes", EncodingUtils.encode(value));
            return this;
        }

        public CopyProcessDefinitionUsingPOST1QueryParams targetProjectCode(final Long value) {
            put("targetProjectCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * createEmptyProcessDefinition
     * create empty process
     *
     * @param name          process definition name (required)
     * @param projectCode   project code (required)
     * @param tenantCode    tenantCode (required)
     * @param description   process definition desc (optional)
     * @param globalParams  globalParams (optional)
     * @param timeout       timeout (optional)
     * @param scheduleJson  scheduleJson (optional)
     * @param executionType executionType (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/empty?description={description}&name={name}&globalParams={globalParams}&timeout={timeout}&tenantCode={tenantCode}&scheduleJson={scheduleJson}&executionType={executionType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createEmptyProcessDefinitionUsingPOST1(@Param("name") String name, @Param("projectCode") Long projectCode, @Param("tenantCode") String tenantCode, @Param("description") String description, @Param("globalParams") String globalParams, @Param("timeout") Integer timeout, @Param("scheduleJson") String scheduleJson, @Param("executionType") String executionType);

    /**
     * createEmptyProcessDefinition
     * create empty process
     * Note, this is equivalent to the other <code>createEmptyProcessDefinitionUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateEmptyProcessDefinitionUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>description - process definition desc (optional)</li>
     *                    <li>name - process definition name (required)</li>
     *                    <li>globalParams - globalParams (optional)</li>
     *                    <li>timeout - timeout (optional)</li>
     *                    <li>tenantCode - tenantCode (required)</li>
     *                    <li>scheduleJson - scheduleJson (optional)</li>
     *                    <li>executionType - executionType (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/empty?description={description}&name={name}&globalParams={globalParams}&timeout={timeout}&tenantCode={tenantCode}&scheduleJson={scheduleJson}&executionType={executionType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createEmptyProcessDefinitionUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createEmptyProcessDefinitionUsingPOST1</code> method in a fluent style.
     */
    public static class CreateEmptyProcessDefinitionUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateEmptyProcessDefinitionUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public CreateEmptyProcessDefinitionUsingPOST1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }

        public CreateEmptyProcessDefinitionUsingPOST1QueryParams globalParams(final String value) {
            put("globalParams", EncodingUtils.encode(value));
            return this;
        }

        public CreateEmptyProcessDefinitionUsingPOST1QueryParams timeout(final Integer value) {
            put("timeout", EncodingUtils.encode(value));
            return this;
        }

        public CreateEmptyProcessDefinitionUsingPOST1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }

        public CreateEmptyProcessDefinitionUsingPOST1QueryParams scheduleJson(final String value) {
            put("scheduleJson", EncodingUtils.encode(value));
            return this;
        }

        public CreateEmptyProcessDefinitionUsingPOST1QueryParams executionType(final String value) {
            put("executionType", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * createProcessDefinition
     * create process definition
     *
     * @param locations          process definition node locations info (json format) (required)
     * @param name               process definition name (required)
     * @param projectCode        project code (required)
     * @param tenantCode         tenantCode (required)
     * @param taskRelationJson   taskRelationJson (required)
     * @param taskDefinitionJson taskDefinitionJson (required)
     * @param description        process definition desc (optional)
     * @param otherParamsJson    OTHER_PARAMS_JSON (optional)
     * @param globalParams       globalParams (optional)
     * @param timeout            timeout (optional)
     * @param executionType      executionType (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition?description={description}&locations={locations}&name={name}&otherParamsJson={otherParamsJson}&globalParams={globalParams}&timeout={timeout}&tenantCode={tenantCode}&taskRelationJson={taskRelationJson}&taskDefinitionJson={taskDefinitionJson}&executionType={executionType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createProcessDefinitionUsingPOST1(@Param("locations") String locations, @Param("name") String name, @Param("projectCode") Long projectCode, @Param("tenantCode") String tenantCode, @Param("taskRelationJson") String taskRelationJson, @Param("taskDefinitionJson") String taskDefinitionJson, @Param("description") String description, @Param("otherParamsJson") String otherParamsJson, @Param("globalParams") String globalParams, @Param("timeout") Integer timeout, @Param("executionType") String executionType);

    /**
     * createProcessDefinition
     * create process definition
     * Note, this is equivalent to the other <code>createProcessDefinitionUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateProcessDefinitionUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>description - process definition desc (optional)</li>
     *                    <li>locations - process definition node locations info (json format) (required)</li>
     *                    <li>name - process definition name (required)</li>
     *                    <li>otherParamsJson - OTHER_PARAMS_JSON (optional)</li>
     *                    <li>globalParams - globalParams (optional)</li>
     *                    <li>timeout - timeout (optional)</li>
     *                    <li>tenantCode - tenantCode (required)</li>
     *                    <li>taskRelationJson - taskRelationJson (required)</li>
     *                    <li>taskDefinitionJson - taskDefinitionJson (required)</li>
     *                    <li>executionType - executionType (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition?description={description}&locations={locations}&name={name}&otherParamsJson={otherParamsJson}&globalParams={globalParams}&timeout={timeout}&tenantCode={tenantCode}&taskRelationJson={taskRelationJson}&taskDefinitionJson={taskDefinitionJson}&executionType={executionType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createProcessDefinitionUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createProcessDefinitionUsingPOST1</code> method in a fluent style.
     */
    public static class CreateProcessDefinitionUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateProcessDefinitionUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public CreateProcessDefinitionUsingPOST1QueryParams locations(final String value) {
            put("locations", EncodingUtils.encode(value));
            return this;
        }

        public CreateProcessDefinitionUsingPOST1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }

        public CreateProcessDefinitionUsingPOST1QueryParams otherParamsJson(final String value) {
            put("otherParamsJson", EncodingUtils.encode(value));
            return this;
        }

        public CreateProcessDefinitionUsingPOST1QueryParams globalParams(final String value) {
            put("globalParams", EncodingUtils.encode(value));
            return this;
        }

        public CreateProcessDefinitionUsingPOST1QueryParams timeout(final Integer value) {
            put("timeout", EncodingUtils.encode(value));
            return this;
        }

        public CreateProcessDefinitionUsingPOST1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }

        public CreateProcessDefinitionUsingPOST1QueryParams taskRelationJson(final String value) {
            put("taskRelationJson", EncodingUtils.encode(value));
            return this;
        }

        public CreateProcessDefinitionUsingPOST1QueryParams taskDefinitionJson(final String value) {
            put("taskDefinitionJson", EncodingUtils.encode(value));
            return this;
        }

        public CreateProcessDefinitionUsingPOST1QueryParams executionType(final String value) {
            put("executionType", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * deleteByCode
     * delete process definition by process definition id
     *
     * @param code        process definition code (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/process-definition/{code}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteProcessDefinitionByCodeUsingDELETE1(@Param("code") Integer code, @Param("projectCode") Long projectCode);

    /**
     * deleteVersion
     * delete process definition version
     *
     * @param code        process definition code (required)
     * @param projectCode project code (required)
     * @param version     version (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/process-definition/{code}/versions/{version}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteProcessDefinitionVersionUsingDELETE1(@Param("code") Long code, @Param("projectCode") Long projectCode, @Param("version") Integer version);

    /**
     * getTasksByDefinitionCode
     * GET_TASK_LIST_BY_DEFINITION_CODE_NOTES
     *
     * @param code        process definition code (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/{code}/tasks")
    @Headers({
            "Accept: */*",
    })
    Result getNodeListByDefinitionCodeUsingGET1(@Param("code") Long code, @Param("projectCode") Long projectCode);

    /**
     * getTaskListByDefinitionCodes
     * GET_TASK_LIST_BY_DEFINITION_CODE_NOTES
     *
     * @param codes       PROCESS_DEFINITION_CODES (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/batch-query-tasks?codes={codes}")
    @Headers({
            "Accept: */*",
    })
    Result getNodeListMapByDefinitionCodesUsingGET1(@Param("codes") String codes, @Param("projectCode") Long projectCode);

    /**
     * getTaskListByDefinitionCodes
     * GET_TASK_LIST_BY_DEFINITION_CODE_NOTES
     * Note, this is equivalent to the other <code>getNodeListMapByDefinitionCodesUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GetNodeListMapByDefinitionCodesUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>codes - PROCESS_DEFINITION_CODES (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/batch-query-tasks?codes={codes}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result getNodeListMapByDefinitionCodesUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>getNodeListMapByDefinitionCodesUsingGET1</code> method in a fluent style.
     */
    public static class GetNodeListMapByDefinitionCodesUsingGET1QueryParams extends HashMap<String, Object> {
        public GetNodeListMapByDefinitionCodesUsingGET1QueryParams codes(final String value) {
            put("codes", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * getProcessListByProjectCode
     * query process definition list by project code
     *
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/query-process-definition-list")
    @Headers({
            "Accept: */*",
    })
    Result getProcessListByProjectCodesUsingGET1(@Param("projectCode") Long projectCode);

    /**
     * getTaskListByProcessDefinitionCode
     * query task definition list by process definition code
     *
     * @param processDefinitionCode process definition code (required)
     * @param projectCode           project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/query-task-definition-list?processDefinitionCode={processDefinitionCode}")
    @Headers({
            "Accept: */*",
    })
    Result getTaskListByProcessDefinitionCodeUsingGET1(@Param("processDefinitionCode") Long processDefinitionCode, @Param("projectCode") Long projectCode);

    /**
     * getTaskListByProcessDefinitionCode
     * query task definition list by process definition code
     * Note, this is equivalent to the other <code>getTaskListByProcessDefinitionCodeUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GetTaskListByProcessDefinitionCodeUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>processDefinitionCode - process definition code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/query-task-definition-list?processDefinitionCode={processDefinitionCode}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result getTaskListByProcessDefinitionCodeUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>getTaskListByProcessDefinitionCodeUsingGET1</code> method in a fluent style.
     */
    public static class GetTaskListByProcessDefinitionCodeUsingGET1QueryParams extends HashMap<String, Object> {
        public GetTaskListByProcessDefinitionCodeUsingGET1QueryParams processDefinitionCode(final Long value) {
            put("processDefinitionCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * importProcessDefinition
     * import process definition
     *
     * @param projectCode project code (required)
     * @param body        (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/import")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result importProcessDefinitionUsingPOST1(@Param("projectCode") Long projectCode, Object body);

    /**
     * batchMoveByCodes
     * move process definition notes
     *
     * @param codes             PROCESS_DEFINITION_CODES (required)
     * @param projectCode       project code (required)
     * @param targetProjectCode TARGET_PROJECT_CODE (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/batch-move?codes={codes}&targetProjectCode={targetProjectCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result moveProcessDefinitionUsingPOST1(@Param("codes") String codes, @Param("projectCode") Long projectCode, @Param("targetProjectCode") Long targetProjectCode);

    /**
     * batchMoveByCodes
     * move process definition notes
     * Note, this is equivalent to the other <code>moveProcessDefinitionUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link MoveProcessDefinitionUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>codes - PROCESS_DEFINITION_CODES (required)</li>
     *                    <li>targetProjectCode - TARGET_PROJECT_CODE (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/batch-move?codes={codes}&targetProjectCode={targetProjectCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result moveProcessDefinitionUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>moveProcessDefinitionUsingPOST1</code> method in a fluent style.
     */
    public static class MoveProcessDefinitionUsingPOST1QueryParams extends HashMap<String, Object> {
        public MoveProcessDefinitionUsingPOST1QueryParams codes(final String value) {
            put("codes", EncodingUtils.encode(value));
            return this;
        }

        public MoveProcessDefinitionUsingPOST1QueryParams targetProjectCode(final Long value) {
            put("targetProjectCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryAllByProjectCode
     * query all process definition by project code
     *
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/all")
    @Headers({
            "Accept: */*",
    })
    Result queryAllProcessDefinitionByProjectCodeUsingGET1(@Param("projectCode") Long projectCode);

    /**
     * queryProcessDefinitionByCode
     * QUERY_PROCESS_DEFINITION_BY_CODE_NOTES
     *
     * @param code        process definition code (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/{code}")
    @Headers({
            "Accept: */*",
    })
    Result queryProcessDefinitionByCodeUsingGET1(@Param("code") Long code, @Param("projectCode") Long projectCode);

    /**
     * queryProcessDefinitionByName
     * query process definition by name
     *
     * @param name        process definition name (required)
     * @param projectCode project code (required)
     * @return ResultProcessDefinition
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/query-by-name?name={name}")
    @Headers({
            "Accept: */*",
    })
    ResultProcessDefinition queryProcessDefinitionByNameUsingGET1(@Param("name") String name, @Param("projectCode") Long projectCode);

    /**
     * queryProcessDefinitionByName
     * query process definition by name
     * Note, this is equivalent to the other <code>queryProcessDefinitionByNameUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryProcessDefinitionByNameUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>name - process definition name (required)</li>
     *                    </ul>
     * @return ResultProcessDefinition
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/query-by-name?name={name}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    ResultProcessDefinition queryProcessDefinitionByNameUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryProcessDefinitionByNameUsingGET1</code> method in a fluent style.
     */
    public static class QueryProcessDefinitionByNameUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryProcessDefinitionByNameUsingGET1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryListPaging
     * query process definition list paging
     *
     * @param pageNo          page no (required)
     * @param pageSize        page size (required)
     * @param projectCode     project code (required)
     * @param otherParamsJson OTHER_PARAMS_JSON (optional)
     * @param searchVal       search val (optional)
     * @param userId          user id (optional)
     * @return ResultPageInfoProcessDefinition
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition?otherParamsJson={otherParamsJson}&pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}&userId={userId}")
    @Headers({
            "Accept: */*",
    })
    ResultPageInfoProcessDefinition queryProcessDefinitionListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("projectCode") Long projectCode, @Param("otherParamsJson") String otherParamsJson, @Param("searchVal") String searchVal, @Param("userId") Integer userId);

    /**
     * queryListPaging
     * query process definition list paging
     * Note, this is equivalent to the other <code>queryProcessDefinitionListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryProcessDefinitionListPagingUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>otherParamsJson - OTHER_PARAMS_JSON (optional)</li>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>searchVal - search val (optional)</li>
     *                    <li>userId - user id (optional)</li>
     *                    </ul>
     * @return ResultPageInfoProcessDefinition
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition?otherParamsJson={otherParamsJson}&pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}&userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    ResultPageInfoProcessDefinition queryProcessDefinitionListPagingUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryProcessDefinitionListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryProcessDefinitionListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryProcessDefinitionListPagingUsingGET1QueryParams otherParamsJson(final String value) {
            put("otherParamsJson", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessDefinitionListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessDefinitionListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessDefinitionListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessDefinitionListPagingUsingGET1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryList
     * query process definition list
     *
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/list")
    @Headers({
            "Accept: */*",
    })
    Result queryProcessDefinitionListUsingGET1(@Param("projectCode") Long projectCode);

    /**
     * querySimpleList
     * QUERY_PROCESS_DEFINITION_SIMPLE_LIST_NOTES
     *
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/simple-list")
    @Headers({
            "Accept: */*",
    })
    Result queryProcessDefinitionSimpleListUsingGET1(@Param("projectCode") Long projectCode);

    /**
     * queryVersions
     * query process definition versions
     *
     * @param code        process definition code (required)
     * @param pageNo      page no (required)
     * @param pageSize    page size (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/{code}/versions?pageNo={pageNo}&pageSize={pageSize}")
    @Headers({
            "Accept: */*",
    })
    Result queryProcessDefinitionVersionsUsingGET1(@Param("code") Long code, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("projectCode") Long projectCode);

    /**
     * queryVersions
     * query process definition versions
     * Note, this is equivalent to the other <code>queryProcessDefinitionVersionsUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryProcessDefinitionVersionsUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param code        process definition code (required)
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/{code}/versions?pageNo={pageNo}&pageSize={pageSize}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryProcessDefinitionVersionsUsingGET1(@Param("code") Long code, @Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryProcessDefinitionVersionsUsingGET1</code> method in a fluent style.
     */
    public static class QueryProcessDefinitionVersionsUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryProcessDefinitionVersionsUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryProcessDefinitionVersionsUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * release
     * release process definition
     *
     * @param code         process definition code (required)
     * @param projectCode  project code (required)
     * @param releaseState PROCESS_DEFINITION_RELEASE (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/{code}/release?releaseState={releaseState}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result releaseProcessDefinitionUsingPOST1(@Param("code") Long code, @Param("projectCode") Long projectCode, @Param("releaseState") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameReleaseState releaseState);

    /**
     * release
     * release process definition
     * Note, this is equivalent to the other <code>releaseProcessDefinitionUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ReleaseProcessDefinitionUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param code        process definition code (required)
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>releaseState - PROCESS_DEFINITION_RELEASE (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/{code}/release?releaseState={releaseState}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result releaseProcessDefinitionUsingPOST1(@Param("code") Long code, @Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>releaseProcessDefinitionUsingPOST1</code> method in a fluent style.
     */
    public static class ReleaseProcessDefinitionUsingPOST1QueryParams extends HashMap<String, Object> {
        public ReleaseProcessDefinitionUsingPOST1QueryParams releaseState(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameReleaseState value) {
            put("releaseState", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * releaseWorkflowAndSchedule
     * release workflow scheduled
     *
     * @param code         process definition code (required)
     * @param projectCode  process definition name (required)
     * @param releaseState release process definition (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/{code}/release-workflow?releaseState={releaseState}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result releaseWorkflowAndScheduleUsingPOST1(@Param("code") Long code, @Param("projectCode") Long projectCode, @Param("releaseState") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameReleaseState releaseState);

    /**
     * releaseWorkflowAndSchedule
     * release workflow scheduled
     * Note, this is equivalent to the other <code>releaseWorkflowAndScheduleUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ReleaseWorkflowAndScheduleUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param code        process definition code (required)
     * @param projectCode process definition name (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>releaseState - release process definition (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-definition/{code}/release-workflow?releaseState={releaseState}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result releaseWorkflowAndScheduleUsingPOST1(@Param("code") Long code, @Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>releaseWorkflowAndScheduleUsingPOST1</code> method in a fluent style.
     */
    public static class ReleaseWorkflowAndScheduleUsingPOST1QueryParams extends HashMap<String, Object> {
        public ReleaseWorkflowAndScheduleUsingPOST1QueryParams releaseState(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameReleaseState value) {
            put("releaseState", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * switchVersion
     * switch process definition version
     *
     * @param code        process definition code (required)
     * @param projectCode project code (required)
     * @param version     version (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/{code}/versions/{version}")
    @Headers({
            "Accept: */*",
    })
    Result switchProcessDefinitionVersionUsingGET1(@Param("code") Long code, @Param("projectCode") Long projectCode, @Param("version") Integer version);

    /**
     * updateBasicInfo
     * update process definition&#x27;s base information
     *
     * @param code            process definition code (required)
     * @param name            process definition name (required)
     * @param projectCode     project code (required)
     * @param tenantCode      tenantCode (required)
     * @param description     process definition desc (optional)
     * @param otherParamsJson OTHER_PARAMS_JSON (optional)
     * @param releaseState    release process definition (optional)
     * @param globalParams    globalParams (optional)
     * @param timeout         timeout (optional)
     * @param scheduleJson    scheduleJson (optional)
     * @param executionType   executionType (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/process-definition/{code}/basic-info?description={description}&name={name}&otherParamsJson={otherParamsJson}&releaseState={releaseState}&globalParams={globalParams}&timeout={timeout}&tenantCode={tenantCode}&scheduleJson={scheduleJson}&executionType={executionType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateProcessDefinitionBasicInfoUsingPUT1(@Param("code") Long code, @Param("name") String name, @Param("projectCode") Long projectCode, @Param("tenantCode") String tenantCode, @Param("description") String description, @Param("otherParamsJson") String otherParamsJson, @Param("releaseState") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameReleaseState releaseState, @Param("globalParams") String globalParams, @Param("timeout") Integer timeout, @Param("scheduleJson") String scheduleJson, @Param("executionType") String executionType);

    /**
     * updateBasicInfo
     * update process definition&#x27;s base information
     * Note, this is equivalent to the other <code>updateProcessDefinitionBasicInfoUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateProcessDefinitionBasicInfoUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param code        process definition code (required)
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>description - process definition desc (optional)</li>
     *                    <li>name - process definition name (required)</li>
     *                    <li>otherParamsJson - OTHER_PARAMS_JSON (optional)</li>
     *                    <li>releaseState - release process definition (optional)</li>
     *                    <li>globalParams - globalParams (optional)</li>
     *                    <li>timeout - timeout (optional)</li>
     *                    <li>tenantCode - tenantCode (required)</li>
     *                    <li>scheduleJson - scheduleJson (optional)</li>
     *                    <li>executionType - executionType (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/process-definition/{code}/basic-info?description={description}&name={name}&otherParamsJson={otherParamsJson}&releaseState={releaseState}&globalParams={globalParams}&timeout={timeout}&tenantCode={tenantCode}&scheduleJson={scheduleJson}&executionType={executionType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateProcessDefinitionBasicInfoUsingPUT1(@Param("code") Long code, @Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateProcessDefinitionBasicInfoUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateProcessDefinitionBasicInfoUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateProcessDefinitionBasicInfoUsingPUT1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionBasicInfoUsingPUT1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionBasicInfoUsingPUT1QueryParams otherParamsJson(final String value) {
            put("otherParamsJson", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionBasicInfoUsingPUT1QueryParams releaseState(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameReleaseState value) {
            put("releaseState", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionBasicInfoUsingPUT1QueryParams globalParams(final String value) {
            put("globalParams", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionBasicInfoUsingPUT1QueryParams timeout(final Integer value) {
            put("timeout", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionBasicInfoUsingPUT1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionBasicInfoUsingPUT1QueryParams scheduleJson(final String value) {
            put("scheduleJson", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionBasicInfoUsingPUT1QueryParams executionType(final String value) {
            put("executionType", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * update
     * update process definition
     *
     * @param code               process definition code (required)
     * @param locations          process definition node locations info (json format) (required)
     * @param name               process definition name (required)
     * @param projectCode        project code (required)
     * @param tenantCode         tenantCode (required)
     * @param taskRelationJson   taskRelationJson (required)
     * @param taskDefinitionJson taskDefinitionJson (required)
     * @param description        process definition desc (optional)
     * @param otherParamsJson    OTHER_PARAMS_JSON (optional)
     * @param releaseState       release process definition (optional)
     * @param globalParams       globalParams (optional)
     * @param timeout            timeout (optional)
     * @param executionType      executionType (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/process-definition/{code}?description={description}&locations={locations}&name={name}&otherParamsJson={otherParamsJson}&releaseState={releaseState}&globalParams={globalParams}&timeout={timeout}&tenantCode={tenantCode}&taskRelationJson={taskRelationJson}&taskDefinitionJson={taskDefinitionJson}&executionType={executionType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateProcessDefinitionUsingPUT1(@Param("code") Long code, @Param("locations") String locations, @Param("name") String name, @Param("projectCode") Long projectCode, @Param("tenantCode") String tenantCode, @Param("taskRelationJson") String taskRelationJson, @Param("taskDefinitionJson") String taskDefinitionJson, @Param("description") String description, @Param("otherParamsJson") String otherParamsJson, @Param("releaseState") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameReleaseState releaseState, @Param("globalParams") String globalParams, @Param("timeout") Integer timeout, @Param("executionType") String executionType);

    /**
     * update
     * update process definition
     * Note, this is equivalent to the other <code>updateProcessDefinitionUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateProcessDefinitionUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param code        process definition code (required)
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>description - process definition desc (optional)</li>
     *                    <li>locations - process definition node locations info (json format) (required)</li>
     *                    <li>name - process definition name (required)</li>
     *                    <li>otherParamsJson - OTHER_PARAMS_JSON (optional)</li>
     *                    <li>releaseState - release process definition (optional)</li>
     *                    <li>globalParams - globalParams (optional)</li>
     *                    <li>timeout - timeout (optional)</li>
     *                    <li>tenantCode - tenantCode (required)</li>
     *                    <li>taskRelationJson - taskRelationJson (required)</li>
     *                    <li>taskDefinitionJson - taskDefinitionJson (required)</li>
     *                    <li>executionType - executionType (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{projectCode}/process-definition/{code}?description={description}&locations={locations}&name={name}&otherParamsJson={otherParamsJson}&releaseState={releaseState}&globalParams={globalParams}&timeout={timeout}&tenantCode={tenantCode}&taskRelationJson={taskRelationJson}&taskDefinitionJson={taskDefinitionJson}&executionType={executionType}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateProcessDefinitionUsingPUT1(@Param("code") Long code, @Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateProcessDefinitionUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateProcessDefinitionUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateProcessDefinitionUsingPUT1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionUsingPUT1QueryParams locations(final String value) {
            put("locations", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionUsingPUT1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionUsingPUT1QueryParams otherParamsJson(final String value) {
            put("otherParamsJson", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionUsingPUT1QueryParams releaseState(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameReleaseState value) {
            put("releaseState", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionUsingPUT1QueryParams globalParams(final String value) {
            put("globalParams", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionUsingPUT1QueryParams timeout(final Integer value) {
            put("timeout", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionUsingPUT1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionUsingPUT1QueryParams taskRelationJson(final String value) {
            put("taskRelationJson", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionUsingPUT1QueryParams taskDefinitionJson(final String value) {
            put("taskDefinitionJson", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProcessDefinitionUsingPUT1QueryParams executionType(final String value) {
            put("executionType", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * verify-name
     * verify process definition name
     *
     * @param name        process definition name (required)
     * @param projectCode project code (required)
     * @param code        process definition code (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/verify-name?code={code}&name={name}")
    @Headers({
            "Accept: */*",
    })
    Result verifyProcessDefinitionNameUsingGET1(@Param("name") String name, @Param("projectCode") Long projectCode, @Param("code") Long code);

    /**
     * verify-name
     * verify process definition name
     * Note, this is equivalent to the other <code>verifyProcessDefinitionNameUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyProcessDefinitionNameUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>code - process definition code (optional)</li>
     *                    <li>name - process definition name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/verify-name?code={code}&name={name}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result verifyProcessDefinitionNameUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyProcessDefinitionNameUsingGET1</code> method in a fluent style.
     */
    public static class VerifyProcessDefinitionNameUsingGET1QueryParams extends HashMap<String, Object> {
        public VerifyProcessDefinitionNameUsingGET1QueryParams code(final Long value) {
            put("code", EncodingUtils.encode(value));
            return this;
        }

        public VerifyProcessDefinitionNameUsingGET1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * viewTree
     * view tree
     *
     * @param code        process definition code (required)
     * @param limit       limit (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/{code}/view-tree?limit={limit}")
    @Headers({
            "Accept: */*",
    })
    Result viewTreeUsingGET1(@Param("code") Long code, @Param("limit") Integer limit, @Param("projectCode") Long projectCode);

    /**
     * viewTree
     * view tree
     * Note, this is equivalent to the other <code>viewTreeUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ViewTreeUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param code        process definition code (required)
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>limit - limit (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/{code}/view-tree?limit={limit}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result viewTreeUsingGET1(@Param("code") Long code, @Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>viewTreeUsingGET1</code> method in a fluent style.
     */
    public static class ViewTreeUsingGET1QueryParams extends HashMap<String, Object> {
        public ViewTreeUsingGET1QueryParams limit(final Integer value) {
            put("limit", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * viewVariables
     * QUERY_PROCESS_DEFINITION_GLOBAL_VARIABLES_AND_LOCAL_VARIABLES_NOTES
     *
     * @param projectCode  project code (required)
     * @param code         code (required)
     * @param alertGroup   (optional)
     * @param createTime   (optional)
     * @param email        (optional)
     * @param id           (optional)
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
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/{code}/view-variables?alertGroup={alertGroup}&createTime={createTime}&email={email}&id={id}&phone={phone}&queue={queue}&queueName={queueName}&state={state}&tenantCode={tenantCode}&tenantId={tenantId}&timeZone={timeZone}&updateTime={updateTime}&userName={userName}&userPassword={userPassword}&userType={userType}")
    @Headers({
            "Accept: */*",
    })
    Result viewVariablesUsingGET1(@Param("projectCode") Long projectCode, @Param("code") Long code, @Param("alertGroup") String alertGroup, @Param("createTime") OffsetDateTime createTime, @Param("email") String email, @Param("id") Integer id, @Param("phone") String phone, @Param("queue") String queue, @Param("queueName") String queueName, @Param("state") Integer state, @Param("tenantCode") String tenantCode, @Param("tenantId") Integer tenantId, @Param("timeZone") String timeZone, @Param("updateTime") OffsetDateTime updateTime, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userType") String userType);

    /**
     * viewVariables
     * QUERY_PROCESS_DEFINITION_GLOBAL_VARIABLES_AND_LOCAL_VARIABLES_NOTES
     * Note, this is equivalent to the other <code>viewVariablesUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ViewVariablesUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param code        code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>alertGroup -  (optional)</li>
     *                    <li>createTime -  (optional)</li>
     *                    <li>email -  (optional)</li>
     *                    <li>id -  (optional)</li>
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
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-definition/{code}/view-variables?alertGroup={alertGroup}&createTime={createTime}&email={email}&id={id}&phone={phone}&queue={queue}&queueName={queueName}&state={state}&tenantCode={tenantCode}&tenantId={tenantId}&timeZone={timeZone}&updateTime={updateTime}&userName={userName}&userPassword={userPassword}&userType={userType}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result viewVariablesUsingGET1(@Param("projectCode") Long projectCode, @Param("code") Long code, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>viewVariablesUsingGET1</code> method in a fluent style.
     */
    public static class ViewVariablesUsingGET1QueryParams extends HashMap<String, Object> {
        public ViewVariablesUsingGET1QueryParams alertGroup(final String value) {
            put("alertGroup", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams createTime(final OffsetDateTime value) {
            put("createTime", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams email(final String value) {
            put("email", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams phone(final String value) {
            put("phone", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams queue(final String value) {
            put("queue", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams queueName(final String value) {
            put("queueName", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams state(final Integer value) {
            put("state", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams tenantId(final Integer value) {
            put("tenantId", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams timeZone(final String value) {
            put("timeZone", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams updateTime(final OffsetDateTime value) {
            put("updateTime", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams userPassword(final String value) {
            put("userPassword", EncodingUtils.encode(value));
            return this;
        }

        public ViewVariablesUsingGET1QueryParams userType(final String value) {
            put("userType", EncodingUtils.encode(value));
            return this;
        }
    }
}
