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
public interface ProcessTaskRelationRelatedOperationApi extends ApiClient.Api {

    /**
     * save
     * create process task relation
     *
     * @param postTaskCode          POST_TASK_CODE (required)
     * @param preTaskCode           PRE_TASK_CODE (required)
     * @param processDefinitionCode process definition code (required)
     * @param projectCode           project code (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-task-relation?postTaskCode={postTaskCode}&preTaskCode={preTaskCode}&processDefinitionCode={processDefinitionCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createProcessTaskRelationUsingPOST1(@Param("postTaskCode") Long postTaskCode, @Param("preTaskCode") Long preTaskCode, @Param("processDefinitionCode") Long processDefinitionCode, @Param("projectCode") Long projectCode);

    /**
     * save
     * create process task relation
     * Note, this is equivalent to the other <code>createProcessTaskRelationUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateProcessTaskRelationUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>postTaskCode - POST_TASK_CODE (required)</li>
     *                    <li>preTaskCode - PRE_TASK_CODE (required)</li>
     *                    <li>processDefinitionCode - process definition code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/process-task-relation?postTaskCode={postTaskCode}&preTaskCode={preTaskCode}&processDefinitionCode={processDefinitionCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createProcessTaskRelationUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createProcessTaskRelationUsingPOST1</code> method in a fluent style.
     */
    public static class CreateProcessTaskRelationUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateProcessTaskRelationUsingPOST1QueryParams postTaskCode(final Long value) {
            put("postTaskCode", EncodingUtils.encode(value));
            return this;
        }

        public CreateProcessTaskRelationUsingPOST1QueryParams preTaskCode(final Long value) {
            put("preTaskCode", EncodingUtils.encode(value));
            return this;
        }

        public CreateProcessTaskRelationUsingPOST1QueryParams processDefinitionCode(final Long value) {
            put("processDefinitionCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * deleteDownstreamRelation
     * delete downstream task relation
     *
     * @param postTaskCodes POST_TASK_CODES (required)
     * @param projectCode   project code (required)
     * @param taskCode      TASK_CODE (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/process-task-relation/{taskCode}/downstream?postTaskCodes={postTaskCodes}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteDownstreamRelationUsingDELETE1(@Param("postTaskCodes") String postTaskCodes, @Param("projectCode") Long projectCode, @Param("taskCode") Long taskCode);

    /**
     * deleteDownstreamRelation
     * delete downstream task relation
     * Note, this is equivalent to the other <code>deleteDownstreamRelationUsingDELETE1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link DeleteDownstreamRelationUsingDELETE1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param taskCode    TASK_CODE (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>postTaskCodes - POST_TASK_CODES (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/process-task-relation/{taskCode}/downstream?postTaskCodes={postTaskCodes}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteDownstreamRelationUsingDELETE1(@Param("projectCode") Long projectCode, @Param("taskCode") Long taskCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>deleteDownstreamRelationUsingDELETE1</code> method in a fluent style.
     */
    public static class DeleteDownstreamRelationUsingDELETE1QueryParams extends HashMap<String, Object> {
        public DeleteDownstreamRelationUsingDELETE1QueryParams postTaskCodes(final String value) {
            put("postTaskCodes", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * deleteEdge
     * delete edge
     *
     * @param postTaskCode          POST_TASK_CODE (required)
     * @param preTaskCode           PRE_TASK_CODE (required)
     * @param processDefinitionCode process definition code (required)
     * @param projectCode           project code (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/process-task-relation/{processDefinitionCode}/{preTaskCode}/{postTaskCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteEdgeUsingDELETE1(@Param("postTaskCode") Long postTaskCode, @Param("preTaskCode") Long preTaskCode, @Param("processDefinitionCode") Long processDefinitionCode, @Param("projectCode") Long projectCode);

    /**
     * deleteRelation
     * delete process task relation
     *
     * @param processDefinitionCode process definition code (required)
     * @param projectCode           project code (required)
     * @param taskCode              TASK_CODE (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/process-task-relation/{taskCode}?processDefinitionCode={processDefinitionCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteTaskProcessRelationUsingDELETE1(@Param("processDefinitionCode") Long processDefinitionCode, @Param("projectCode") Long projectCode, @Param("taskCode") Long taskCode);

    /**
     * deleteRelation
     * delete process task relation
     * Note, this is equivalent to the other <code>deleteTaskProcessRelationUsingDELETE1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link DeleteTaskProcessRelationUsingDELETE1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param taskCode    TASK_CODE (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>processDefinitionCode - process definition code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/process-task-relation/{taskCode}?processDefinitionCode={processDefinitionCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteTaskProcessRelationUsingDELETE1(@Param("projectCode") Long projectCode, @Param("taskCode") Long taskCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>deleteTaskProcessRelationUsingDELETE1</code> method in a fluent style.
     */
    public static class DeleteTaskProcessRelationUsingDELETE1QueryParams extends HashMap<String, Object> {
        public DeleteTaskProcessRelationUsingDELETE1QueryParams processDefinitionCode(final Long value) {
            put("processDefinitionCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * deleteUpstreamRelation
     * delete upstream task relation
     *
     * @param preTaskCodes PRE_TASK_CODES (required)
     * @param projectCode  project code (required)
     * @param taskCode     TASK_CODE (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/process-task-relation/{taskCode}/upstream?preTaskCodes={preTaskCodes}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteUpstreamRelationUsingDELETE1(@Param("preTaskCodes") String preTaskCodes, @Param("projectCode") Long projectCode, @Param("taskCode") Long taskCode);

    /**
     * deleteUpstreamRelation
     * delete upstream task relation
     * Note, this is equivalent to the other <code>deleteUpstreamRelationUsingDELETE1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link DeleteUpstreamRelationUsingDELETE1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param taskCode    TASK_CODE (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>preTaskCodes - PRE_TASK_CODES (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{projectCode}/process-task-relation/{taskCode}/upstream?preTaskCodes={preTaskCodes}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteUpstreamRelationUsingDELETE1(@Param("projectCode") Long projectCode, @Param("taskCode") Long taskCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>deleteUpstreamRelationUsingDELETE1</code> method in a fluent style.
     */
    public static class DeleteUpstreamRelationUsingDELETE1QueryParams extends HashMap<String, Object> {
        public DeleteUpstreamRelationUsingDELETE1QueryParams preTaskCodes(final String value) {
            put("preTaskCodes", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryDownstreamRelation
     * query downstream task relation
     *
     * @param projectCode project code (required)
     * @param taskCode    TASK_CODE (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-task-relation/{taskCode}/downstream")
    @Headers({
            "Accept: */*",
    })
    Result queryDownstreamRelationUsingGET1(@Param("projectCode") Long projectCode, @Param("taskCode") Long taskCode);

    /**
     * queryUpstreamRelation
     * query upstream task relation
     *
     * @param projectCode project code (required)
     * @param taskCode    TASK_CODE (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/process-task-relation/{taskCode}/upstream")
    @Headers({
            "Accept: */*",
    })
    Result queryUpstreamRelationUsingGET1(@Param("projectCode") Long projectCode, @Param("taskCode") Long taskCode);
}
