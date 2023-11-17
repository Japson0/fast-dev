package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.*;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface ProjectRelatedOperationApi extends ApiClient.Api {

    /**
     * create
     * create project
     *
     * @param projectName project name (required)
     * @param description project description (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects?description={description}&projectName={projectName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createProjectUsingPOST3(@Param("projectName") String projectName, @Param("description") String description);

    /**
     * create
     * create project
     * Note, this is equivalent to the other <code>createProjectUsingPOST3</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateProjectUsingPOST3QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>description - project description (optional)</li>
     *                    <li>projectName - project name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/projects?description={description}&projectName={projectName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createProjectUsingPOST3(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createProjectUsingPOST3</code> method in a fluent style.
     */
    public static class CreateProjectUsingPOST3QueryParams extends HashMap<String, Object> {
        public CreateProjectUsingPOST3QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public CreateProjectUsingPOST3QueryParams projectName(final String value) {
            put("projectName", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * create
     * create project
     *
     * @param body (optional)
     * @return ProjectCreateResponse
     */
    @RequestLine("POST /dolphinscheduler/v2/projects")
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json",
    })
    ProjectCreateResponse createProjectUsingPOST5(ProjectCreateRequest body);

    /**
     * delete
     * delete project by id
     *
     * @param code project code (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/projects/{code}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteProjectUsingDELETE1(@Param("code") Long code);

    /**
     * delete
     * delete project by id
     *
     * @param code project code (required)
     * @return ProjectDeleteResponse
     */
    @RequestLine("DELETE /dolphinscheduler/v2/projects/{code}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    ProjectDeleteResponse deleteProjectUsingDELETE3(@Param("code") Long code);

    /**
     * queryAllProjectListForDependent
     * query all project list for dependent
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/list-dependent")
    @Headers({
            "Accept: */*",
    })
    Result queryAllProjectListForDependentUsingGET1();

    /**
     * queryAllProjectListForDependent
     * query all project list for dependent
     *
     * @return ProjectListResponse
     */
    @RequestLine("GET /dolphinscheduler/v2/projects/list-dependent")
    @Headers({
            "Accept: */*",
    })
    ProjectListResponse queryAllProjectListForDependentUsingGET3();

    /**
     * queryAllProjectList
     * query all project list
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/list")
    @Headers({
            "Accept: */*",
    })
    Result queryAllProjectListUsingGET1();

    /**
     * queryAllProjectList
     * query all project list
     *
     * @return ProjectListResponse
     */
    @RequestLine("GET /dolphinscheduler/v2/projects/list")
    @Headers({
            "Accept: */*",
    })
    ProjectListResponse queryAllProjectListUsingGET3();

    /**
     * queryAuthorizedProject
     * query authorized project
     *
     * @param userId user id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/authed-project?userId={userId}")
    @Headers({
            "Accept: */*",
    })
    Result queryAuthorizedProjectUsingGET1(@Param("userId") Integer userId);

    /**
     * queryAuthorizedProject
     * query authorized project
     * Note, this is equivalent to the other <code>queryAuthorizedProjectUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryAuthorizedProjectUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/authed-project?userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryAuthorizedProjectUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryAuthorizedProjectUsingGET1</code> method in a fluent style.
     */
    public static class QueryAuthorizedProjectUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryAuthorizedProjectUsingGET1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryAuthorizedProject
     * query authorized project
     *
     * @param userId user id (required)
     * @return ProjectListResponse
     */
    @RequestLine("GET /dolphinscheduler/v2/projects/authed-project?userId={userId}")
    @Headers({
            "Accept: */*",
    })
    ProjectListResponse queryAuthorizedProjectUsingGET3(@Param("userId") Integer userId);

    /**
     * queryAuthorizedProject
     * query authorized project
     * Note, this is equivalent to the other <code>queryAuthorizedProjectUsingGET3</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryAuthorizedProjectUsingGET3QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return ProjectListResponse
     */
    @RequestLine("GET /dolphinscheduler/v2/projects/authed-project?userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    ProjectListResponse queryAuthorizedProjectUsingGET3(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryAuthorizedProjectUsingGET3</code> method in a fluent style.
     */
    public static class QueryAuthorizedProjectUsingGET3QueryParams extends HashMap<String, Object> {
        public QueryAuthorizedProjectUsingGET3QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryAuthorizedUser
     * query authorized user
     *
     * @param projectCode project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/authed-user?projectCode={projectCode}")
    @Headers({
            "Accept: */*",
    })
    Result queryAuthorizedUserUsingGET1(@Param("projectCode") Long projectCode);

    /**
     * queryAuthorizedUser
     * query authorized user
     * Note, this is equivalent to the other <code>queryAuthorizedUserUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryAuthorizedUserUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>projectCode - project code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/authed-user?projectCode={projectCode}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryAuthorizedUserUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryAuthorizedUserUsingGET1</code> method in a fluent style.
     */
    public static class QueryAuthorizedUserUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryAuthorizedUserUsingGET1QueryParams projectCode(final Long value) {
            put("projectCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryAuthorizedUser
     * query authorized user
     *
     * @param projectCode project code (required)
     * @return UserListResponse
     */
    @RequestLine("GET /dolphinscheduler/v2/projects/authed-user?projectCode={projectCode}")
    @Headers({
            "Accept: */*",
    })
    UserListResponse queryAuthorizedUserUsingGET3(@Param("projectCode") Long projectCode);

    /**
     * queryAuthorizedUser
     * query authorized user
     * Note, this is equivalent to the other <code>queryAuthorizedUserUsingGET3</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryAuthorizedUserUsingGET3QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>projectCode - project code (required)</li>
     *                    </ul>
     * @return UserListResponse
     */
    @RequestLine("GET /dolphinscheduler/v2/projects/authed-user?projectCode={projectCode}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    UserListResponse queryAuthorizedUserUsingGET3(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryAuthorizedUserUsingGET3</code> method in a fluent style.
     */
    public static class QueryAuthorizedUserUsingGET3QueryParams extends HashMap<String, Object> {
        public QueryAuthorizedUserUsingGET3QueryParams projectCode(final Long value) {
            put("projectCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryProjectByCode
     * query project info by project id
     *
     * @param code project code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/{code}")
    @Headers({
            "Accept: */*",
    })
    Result queryProjectByCodeUsingGET1(@Param("code") Long code);

    /**
     * queryProjectByCode
     * query project info by project id
     *
     * @param code project code (required)
     * @return ProjectQueryResponse
     */
    @RequestLine("GET /dolphinscheduler/v2/projects/{code}")
    @Headers({
            "Accept: */*",
    })
    ProjectQueryResponse queryProjectByCodeUsingGET3(@Param("code") Long code);

    /**
     * queryProjectCreatedAndAuthorizedByUser
     * query authorized and user created project
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/created-and-authed")
    @Headers({
            "Accept: */*",
    })
    Result queryProjectCreatedAndAuthorizedByUserUsingGET1();

    /**
     * queryProjectCreatedAndAuthorizedByUser
     * query authorized and user created project
     *
     * @return ProjectListResponse
     */
    @RequestLine("GET /dolphinscheduler/v2/projects/created-and-authed")
    @Headers({
            "Accept: */*",
    })
    ProjectListResponse queryProjectCreatedAndAuthorizedByUserUsingGET3();

    /**
     * queryProjectListPaging
     * QUERY PROJECT LIST PAGING
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Result queryProjectListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryProjectListPaging
     * QUERY PROJECT LIST PAGING
     * Note, this is equivalent to the other <code>queryProjectListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryProjectListPagingUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>searchVal - search val (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryProjectListPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryProjectListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryProjectListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryProjectListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryProjectListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryProjectListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryProjectListPaging
     * QUERY PROJECT LIST PAGING
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return ProjectListPagingResponse
     */
    @RequestLine("GET /dolphinscheduler/v2/projects?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: application/json",
    })
    ProjectListPagingResponse queryProjectListPagingUsingGET3(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryProjectListPaging
     * QUERY PROJECT LIST PAGING
     * Note, this is equivalent to the other <code>queryProjectListPagingUsingGET3</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryProjectListPagingUsingGET3QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>searchVal - search val (optional)</li>
     *                    </ul>
     * @return ProjectListPagingResponse
     */
    @RequestLine("GET /dolphinscheduler/v2/projects?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: application/json",
    })
    ProjectListPagingResponse queryProjectListPagingUsingGET3(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryProjectListPagingUsingGET3</code> method in a fluent style.
     */
    public static class QueryProjectListPagingUsingGET3QueryParams extends HashMap<String, Object> {
        public QueryProjectListPagingUsingGET3QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryProjectListPagingUsingGET3QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryProjectListPagingUsingGET3QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryUnauthorizedProject
     * query unauthorized project
     *
     * @param userId user id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/unauth-project?userId={userId}")
    @Headers({
            "Accept: */*",
    })
    Result queryUnauthorizedProjectUsingGET1(@Param("userId") Integer userId);

    /**
     * queryUnauthorizedProject
     * query unauthorized project
     * Note, this is equivalent to the other <code>queryUnauthorizedProjectUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryUnauthorizedProjectUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/projects/unauth-project?userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryUnauthorizedProjectUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryUnauthorizedProjectUsingGET1</code> method in a fluent style.
     */
    public static class QueryUnauthorizedProjectUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryUnauthorizedProjectUsingGET1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryUnauthorizedProject
     * query unauthorized project
     *
     * @param userId user id (required)
     * @return ProjectListResponse
     */
    @RequestLine("GET /dolphinscheduler/v2/projects/unauth-project?userId={userId}")
    @Headers({
            "Accept: */*",
    })
    ProjectListResponse queryUnauthorizedProjectUsingGET3(@Param("userId") Integer userId);

    /**
     * queryUnauthorizedProject
     * query unauthorized project
     * Note, this is equivalent to the other <code>queryUnauthorizedProjectUsingGET3</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryUnauthorizedProjectUsingGET3QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return ProjectListResponse
     */
    @RequestLine("GET /dolphinscheduler/v2/projects/unauth-project?userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    ProjectListResponse queryUnauthorizedProjectUsingGET3(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryUnauthorizedProjectUsingGET3</code> method in a fluent style.
     */
    public static class QueryUnauthorizedProjectUsingGET3QueryParams extends HashMap<String, Object> {
        public QueryUnauthorizedProjectUsingGET3QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * update
     * update project
     *
     * @param code        project code (required)
     * @param projectName project name (required)
     * @param userName    user name (required)
     * @param description project description (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{code}?description={description}&projectName={projectName}&userName={userName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateProjectUsingPUT1(@Param("code") Long code, @Param("projectName") String projectName, @Param("userName") String userName, @Param("description") String description);

    /**
     * update
     * update project
     * Note, this is equivalent to the other <code>updateProjectUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateProjectUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param code        project code (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>description - project description (optional)</li>
     *                    <li>projectName - project name (required)</li>
     *                    <li>userName - user name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/projects/{code}?description={description}&projectName={projectName}&userName={userName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateProjectUsingPUT1(@Param("code") Long code, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateProjectUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateProjectUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateProjectUsingPUT1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProjectUsingPUT1QueryParams projectName(final String value) {
            put("projectName", EncodingUtils.encode(value));
            return this;
        }

        public UpdateProjectUsingPUT1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * update
     * update project
     *
     * @param code code (required)
     * @param body (optional)
     * @return ProjectUpdateResponse
     */
    @RequestLine("PUT /dolphinscheduler/v2/projects/{code}")
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json",
    })
    ProjectUpdateResponse updateProjectUsingPUT3(@Param("code") Long code, ProjectUpdateRequest body);
}
