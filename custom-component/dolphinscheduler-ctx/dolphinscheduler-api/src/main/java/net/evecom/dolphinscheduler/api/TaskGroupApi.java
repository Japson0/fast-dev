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
public interface TaskGroupApi extends ApiClient.Api {

    /**
     * closeTaskGroup
     * close task group
     *
     * @param id ID (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/task-group/close-task-group?id={id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result closeTaskGroupUsingPOST1(@Param("id") Integer id);

    /**
     * closeTaskGroup
     * close task group
     * Note, this is equivalent to the other <code>closeTaskGroupUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CloseTaskGroupUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>id - ID (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/task-group/close-task-group?id={id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result closeTaskGroupUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>closeTaskGroupUsingPOST1</code> method in a fluent style.
     */
    public static class CloseTaskGroupUsingPOST1QueryParams extends HashMap<String, Object> {
        public CloseTaskGroupUsingPOST1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * create
     * create task group
     *
     * @param description task group description (required)
     * @param groupSize   task group size (required)
     * @param name        NAME (required)
     * @param projectCode project code (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/task-group/create?description={description}&groupSize={groupSize}&name={name}&projectCode={projectCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createTaskGroupUsingPOST1(@Param("description") String description, @Param("groupSize") Integer groupSize, @Param("name") String name, @Param("projectCode") Long projectCode);

    /**
     * create
     * create task group
     * Note, this is equivalent to the other <code>createTaskGroupUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateTaskGroupUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>description - task group description (required)</li>
     *                    <li>groupSize - task group size (required)</li>
     *                    <li>name - NAME (required)</li>
     *                    <li>projectCode - project code (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/task-group/create?description={description}&groupSize={groupSize}&name={name}&projectCode={projectCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createTaskGroupUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createTaskGroupUsingPOST1</code> method in a fluent style.
     */
    public static class CreateTaskGroupUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateTaskGroupUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public CreateTaskGroupUsingPOST1QueryParams groupSize(final Integer value) {
            put("groupSize", EncodingUtils.encode(value));
            return this;
        }

        public CreateTaskGroupUsingPOST1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }

        public CreateTaskGroupUsingPOST1QueryParams projectCode(final Long value) {
            put("projectCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * forceStart
     * force start task group
     *
     * @param queueId task group queue id (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/task-group/forceStart?queueId={queueId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result forceStartUsingPOST1(@Param("queueId") Integer queueId);

    /**
     * forceStart
     * force start task group
     * Note, this is equivalent to the other <code>forceStartUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ForceStartUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>queueId - task group queue id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/task-group/forceStart?queueId={queueId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result forceStartUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>forceStartUsingPOST1</code> method in a fluent style.
     */
    public static class ForceStartUsingPOST1QueryParams extends HashMap<String, Object> {
        public ForceStartUsingPOST1QueryParams queueId(final Integer value) {
            put("queueId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * modifyPriority
     * modify task group priority
     *
     * @param priority task group queue priority (required)
     * @param queueId  task group queue id (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/task-group/modifyPriority?priority={priority}&queueId={queueId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result modifyPriorityUsingPOST1(@Param("priority") Integer priority, @Param("queueId") Integer queueId);

    /**
     * modifyPriority
     * modify task group priority
     * Note, this is equivalent to the other <code>modifyPriorityUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ModifyPriorityUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>priority - task group queue priority (required)</li>
     *                    <li>queueId - task group queue id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/task-group/modifyPriority?priority={priority}&queueId={queueId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result modifyPriorityUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>modifyPriorityUsingPOST1</code> method in a fluent style.
     */
    public static class ModifyPriorityUsingPOST1QueryParams extends HashMap<String, Object> {
        public ModifyPriorityUsingPOST1QueryParams priority(final Integer value) {
            put("priority", EncodingUtils.encode(value));
            return this;
        }

        public ModifyPriorityUsingPOST1QueryParams queueId(final Integer value) {
            put("queueId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * list-paging
     * query all task group
     *
     * @param pageNo   page no (required)
     * @param pageSize page size (required)
     * @param name     task group name (optional)
     * @param status   status (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/task-group/list-paging?name={name}&pageNo={pageNo}&pageSize={pageSize}&status={status}")
    @Headers({
            "Accept: */*",
    })
    Result queryAllTaskGroupUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("name") String name, @Param("status") Integer status);

    /**
     * list-paging
     * query all task group
     * Note, this is equivalent to the other <code>queryAllTaskGroupUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryAllTaskGroupUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>name - task group name (optional)</li>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>status - status (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/task-group/list-paging?name={name}&pageNo={pageNo}&pageSize={pageSize}&status={status}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryAllTaskGroupUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryAllTaskGroupUsingGET1</code> method in a fluent style.
     */
    public static class QueryAllTaskGroupUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryAllTaskGroupUsingGET1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }

        public QueryAllTaskGroupUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryAllTaskGroupUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryAllTaskGroupUsingGET1QueryParams status(final Integer value) {
            put("status", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryTaskGroupByName
     * query task group list by project id
     *
     * @param pageNo      page no (required)
     * @param pageSize    page size (required)
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/task-group/query-list-by-projectCode?pageNo={pageNo}&pageSize={pageSize}&projectCode={projectCode}")
    @Headers({
            "Accept: */*",
    })
    Result queryTaskGroupByCodeUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("projectCode") String projectCode);

    /**
     * queryTaskGroupByName
     * query task group list by project id
     * Note, this is equivalent to the other <code>queryTaskGroupByCodeUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryTaskGroupByCodeUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>projectCode - project code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/task-group/query-list-by-projectCode?pageNo={pageNo}&pageSize={pageSize}&projectCode={projectCode}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryTaskGroupByCodeUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryTaskGroupByCodeUsingGET1</code> method in a fluent style.
     */
    public static class QueryTaskGroupByCodeUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryTaskGroupByCodeUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskGroupByCodeUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskGroupByCodeUsingGET1QueryParams projectCode(final String value) {
            put("projectCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryTaskGroupByStatus
     * query task group list by status
     *
     * @param pageNo   page no (required)
     * @param pageSize page size (required)
     * @param status   task group status (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/task-group/query-list-by-status?pageNo={pageNo}&pageSize={pageSize}&status={status}")
    @Headers({
            "Accept: */*",
    })
    Result queryTaskGroupByStatusUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("status") Integer status);

    /**
     * queryTaskGroupByStatus
     * query task group list by status
     * Note, this is equivalent to the other <code>queryTaskGroupByStatusUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryTaskGroupByStatusUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>status - task group status (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/task-group/query-list-by-status?pageNo={pageNo}&pageSize={pageSize}&status={status}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryTaskGroupByStatusUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryTaskGroupByStatusUsingGET1</code> method in a fluent style.
     */
    public static class QueryTaskGroupByStatusUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryTaskGroupByStatusUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskGroupByStatusUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryTaskGroupByStatusUsingGET1QueryParams status(final Integer value) {
            put("status", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryTasksByGroupId
     * query all task groups
     *
     * @param pageNo              page no (required)
     * @param pageSize            page size (required)
     * @param groupId             GROUP_ID (optional)
     * @param processInstanceName process instance name (optional)
     * @param status              task group status (optional)
     * @param taskInstanceName    task instance name (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/task-group/query-list-by-group-id?groupId={groupId}&pageNo={pageNo}&pageSize={pageSize}&processInstanceName={processInstanceName}&status={status}&taskInstanceName={taskInstanceName}")
    @Headers({
            "Accept: */*",
    })
    Result queryTasksByGroupIdUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("groupId") Integer groupId, @Param("processInstanceName") String processInstanceName, @Param("status") Integer status, @Param("taskInstanceName") String taskInstanceName);

    /**
     * queryTasksByGroupId
     * query all task groups
     * Note, this is equivalent to the other <code>queryTasksByGroupIdUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryTasksByGroupIdUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>groupId - GROUP_ID (optional)</li>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>processInstanceName - process instance name (optional)</li>
     *                    <li>status - task group status (optional)</li>
     *                    <li>taskInstanceName - task instance name (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/task-group/query-list-by-group-id?groupId={groupId}&pageNo={pageNo}&pageSize={pageSize}&processInstanceName={processInstanceName}&status={status}&taskInstanceName={taskInstanceName}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryTasksByGroupIdUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryTasksByGroupIdUsingGET1</code> method in a fluent style.
     */
    public static class QueryTasksByGroupIdUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryTasksByGroupIdUsingGET1QueryParams groupId(final Integer value) {
            put("groupId", EncodingUtils.encode(value));
            return this;
        }

        public QueryTasksByGroupIdUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryTasksByGroupIdUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryTasksByGroupIdUsingGET1QueryParams processInstanceName(final String value) {
            put("processInstanceName", EncodingUtils.encode(value));
            return this;
        }

        public QueryTasksByGroupIdUsingGET1QueryParams status(final Integer value) {
            put("status", EncodingUtils.encode(value));
            return this;
        }

        public QueryTasksByGroupIdUsingGET1QueryParams taskInstanceName(final String value) {
            put("taskInstanceName", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * startTaskGroup
     * start task group
     *
     * @param id task group id (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/task-group/start-task-group?id={id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result startTaskGroupUsingPOST1(@Param("id") Integer id);

    /**
     * startTaskGroup
     * start task group
     * Note, this is equivalent to the other <code>startTaskGroupUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link StartTaskGroupUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>id - task group id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/task-group/start-task-group?id={id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result startTaskGroupUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>startTaskGroupUsingPOST1</code> method in a fluent style.
     */
    public static class StartTaskGroupUsingPOST1QueryParams extends HashMap<String, Object> {
        public StartTaskGroupUsingPOST1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * update
     * update task group
     *
     * @param description task group description (required)
     * @param groupSize   task group size (required)
     * @param id          task group id (required)
     * @param name        task group name (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/task-group/update?description={description}&groupSize={groupSize}&id={id}&name={name}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateTaskGroupUsingPOST1(@Param("description") String description, @Param("groupSize") Integer groupSize, @Param("id") Integer id, @Param("name") String name);

    /**
     * update
     * update task group
     * Note, this is equivalent to the other <code>updateTaskGroupUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateTaskGroupUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>description - task group description (required)</li>
     *                    <li>groupSize - task group size (required)</li>
     *                    <li>id - task group id (required)</li>
     *                    <li>name - task group name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/task-group/update?description={description}&groupSize={groupSize}&id={id}&name={name}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateTaskGroupUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateTaskGroupUsingPOST1</code> method in a fluent style.
     */
    public static class UpdateTaskGroupUsingPOST1QueryParams extends HashMap<String, Object> {
        public UpdateTaskGroupUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public UpdateTaskGroupUsingPOST1QueryParams groupSize(final Integer value) {
            put("groupSize", EncodingUtils.encode(value));
            return this;
        }

        public UpdateTaskGroupUsingPOST1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }

        public UpdateTaskGroupUsingPOST1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }
    }
}
