package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.Result;
import io.swagger.client.model.ResultListWorkFlowLineage;
import io.swagger.client.model.ResultMapstringobject;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface WorkFlowLineageRelatedOperationApi extends ApiClient.Api {

    /**
     * queryLineageByWorkFlowCode
     * query workflow lineage by code list
     *
     * @param projectCode  project code (required)
     * @param workFlowCode workFlowCode (required)
     * @return ResultMapstringobject
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/lineages/{workFlowCode}")
    @Headers({
            "Accept: */*",
    })
    ResultMapstringobject queryWorkFlowLineageByCodeUsingGET1(@Param("projectCode") Long projectCode, @Param("workFlowCode") Long workFlowCode);

    /**
     * queryLineageByWorkFlowName
     * query workflow lineage by name
     *
     * @param projectCode  project code (required)
     * @param workFlowName workFlowName (optional)
     * @return ResultListWorkFlowLineage
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/lineages/query-by-name?workFlowName={workFlowName}")
    @Headers({
            "Accept: */*",
    })
    ResultListWorkFlowLineage queryWorkFlowLineageByNameUsingGET1(@Param("projectCode") Long projectCode, @Param("workFlowName") String workFlowName);

    /**
     * queryLineageByWorkFlowName
     * query workflow lineage by name
     * Note, this is equivalent to the other <code>queryWorkFlowLineageByNameUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryWorkFlowLineageByNameUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>workFlowName - workFlowName (optional)</li>
     *                    </ul>
     * @return ResultListWorkFlowLineage
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/lineages/query-by-name?workFlowName={workFlowName}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    ResultListWorkFlowLineage queryWorkFlowLineageByNameUsingGET1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryWorkFlowLineageByNameUsingGET1</code> method in a fluent style.
     */
    public static class QueryWorkFlowLineageByNameUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryWorkFlowLineageByNameUsingGET1QueryParams workFlowName(final String value) {
            put("workFlowName", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryWorkFlowList
     * query workflow lineage
     *
     * @param projectCode project code (required)
     * @return ResultMapstringobject
     */
    @RequestLine("GET /dolphinscheduler/projects/{projectCode}/lineages/list")
    @Headers({
            "Accept: */*",
    })
    ResultMapstringobject queryWorkFlowLineageUsingGET1(@Param("projectCode") Long projectCode);

    /**
     * verifyTaskCanDelete
     * verify if task can be deleted
     *
     * @param processDefinitionCode process definition code (required)
     * @param projectCode           process definition name (required)
     * @param taskCode              task definition code (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/lineages/tasks/verify-delete?processDefinitionCode={processDefinitionCode}&taskCode={taskCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result verifyTaskCanDeleteUsingPOST1(@Param("processDefinitionCode") Long processDefinitionCode, @Param("projectCode") Long projectCode, @Param("taskCode") Long taskCode);

    /**
     * verifyTaskCanDelete
     * verify if task can be deleted
     * Note, this is equivalent to the other <code>verifyTaskCanDeleteUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyTaskCanDeleteUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param projectCode process definition name (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>processDefinitionCode - process definition code (required)</li>
     *                    <li>taskCode - task definition code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects/{projectCode}/lineages/tasks/verify-delete?processDefinitionCode={processDefinitionCode}&taskCode={taskCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result verifyTaskCanDeleteUsingPOST1(@Param("projectCode") Long projectCode, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyTaskCanDeleteUsingPOST1</code> method in a fluent style.
     */
    public static class VerifyTaskCanDeleteUsingPOST1QueryParams extends HashMap<String, Object> {
        public VerifyTaskCanDeleteUsingPOST1QueryParams processDefinitionCode(final Long value) {
            put("processDefinitionCode", EncodingUtils.encode(value));
            return this;
        }

        public VerifyTaskCanDeleteUsingPOST1QueryParams taskCode(final Long value) {
            put("taskCode", EncodingUtils.encode(value));
            return this;
        }
    }
}
