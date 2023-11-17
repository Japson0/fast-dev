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
public interface EnvironmentRelatedOperationApi extends ApiClient.Api {

    /**
     * createEnvironment
     * create environment
     *
     * @param config       environment configuration (required)
     * @param name         environment name (required)
     * @param description  environment description (optional)
     * @param workerGroups worker group list (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/environment/create?config={config}&description={description}&name={name}&workerGroups={workerGroups}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createEnvironmentUsingPOST1(@Param("config") String config, @Param("name") String name, @Param("description") String description, @Param("workerGroups") String workerGroups);

    /**
     * createEnvironment
     * create environment
     * Note, this is equivalent to the other <code>createEnvironmentUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateEnvironmentUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>config - environment configuration (required)</li>
     *                    <li>description - environment description (optional)</li>
     *                    <li>name - environment name (required)</li>
     *                    <li>workerGroups - worker group list (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/environment/create?config={config}&description={description}&name={name}&workerGroups={workerGroups}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createEnvironmentUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createEnvironmentUsingPOST1</code> method in a fluent style.
     */
    public static class CreateEnvironmentUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateEnvironmentUsingPOST1QueryParams config(final String value) {
            put("config", EncodingUtils.encode(value));
            return this;
        }

        public CreateEnvironmentUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public CreateEnvironmentUsingPOST1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }

        public CreateEnvironmentUsingPOST1QueryParams workerGroups(final String value) {
            put("workerGroups", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * deleteEnvironmentByCode
     * delete environment by code
     *
     * @param environmentCode environment code (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/environment/delete?environmentCode={environmentCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteEnvironmentUsingPOST1(@Param("environmentCode") Long environmentCode);

    /**
     * deleteEnvironmentByCode
     * delete environment by code
     * Note, this is equivalent to the other <code>deleteEnvironmentUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link DeleteEnvironmentUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>environmentCode - environment code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/environment/delete?environmentCode={environmentCode}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteEnvironmentUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>deleteEnvironmentUsingPOST1</code> method in a fluent style.
     */
    public static class DeleteEnvironmentUsingPOST1QueryParams extends HashMap<String, Object> {
        public DeleteEnvironmentUsingPOST1QueryParams environmentCode(final Long value) {
            put("environmentCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryAllEnvironmentList
     * query all environment list
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/environment/query-environment-list")
    @Headers({
            "Accept: */*",
    })
    Result queryAllEnvironmentListUsingGET1();

    /**
     * queryEnvironmentByCode
     * query environment by code
     *
     * @param environmentCode environment code (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/environment/query-by-code?environmentCode={environmentCode}")
    @Headers({
            "Accept: */*",
    })
    Result queryEnvironmentByCodeUsingGET1(@Param("environmentCode") Long environmentCode);

    /**
     * queryEnvironmentByCode
     * query environment by code
     * Note, this is equivalent to the other <code>queryEnvironmentByCodeUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryEnvironmentByCodeUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>environmentCode - environment code (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/environment/query-by-code?environmentCode={environmentCode}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryEnvironmentByCodeUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryEnvironmentByCodeUsingGET1</code> method in a fluent style.
     */
    public static class QueryEnvironmentByCodeUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryEnvironmentByCodeUsingGET1QueryParams environmentCode(final Long value) {
            put("environmentCode", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryEnvironmentListPaging
     * query environment list paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/environment/list-paging?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Result queryEnvironmentListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryEnvironmentListPaging
     * query environment list paging
     * Note, this is equivalent to the other <code>queryEnvironmentListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryEnvironmentListPagingUsingGET1QueryParams} class that allows for
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
    @RequestLine("GET /dolphinscheduler/environment/list-paging?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryEnvironmentListPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryEnvironmentListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryEnvironmentListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryEnvironmentListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryEnvironmentListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryEnvironmentListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateEnvironment
     * UPDATE_ENVIRONMENT_NOTES
     *
     * @param code         environment code (required)
     * @param config       environment configuration (required)
     * @param name         environment name (required)
     * @param description  environment description (optional)
     * @param workerGroups worker group list (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/environment/update?code={code}&config={config}&description={description}&name={name}&workerGroups={workerGroups}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateEnvironmentUsingPOST1(@Param("code") Long code, @Param("config") String config, @Param("name") String name, @Param("description") String description, @Param("workerGroups") String workerGroups);

    /**
     * updateEnvironment
     * UPDATE_ENVIRONMENT_NOTES
     * Note, this is equivalent to the other <code>updateEnvironmentUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateEnvironmentUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>code - environment code (required)</li>
     *                    <li>config - environment configuration (required)</li>
     *                    <li>description - environment description (optional)</li>
     *                    <li>name - environment name (required)</li>
     *                    <li>workerGroups - worker group list (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/environment/update?code={code}&config={config}&description={description}&name={name}&workerGroups={workerGroups}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateEnvironmentUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateEnvironmentUsingPOST1</code> method in a fluent style.
     */
    public static class UpdateEnvironmentUsingPOST1QueryParams extends HashMap<String, Object> {
        public UpdateEnvironmentUsingPOST1QueryParams code(final Long value) {
            put("code", EncodingUtils.encode(value));
            return this;
        }

        public UpdateEnvironmentUsingPOST1QueryParams config(final String value) {
            put("config", EncodingUtils.encode(value));
            return this;
        }

        public UpdateEnvironmentUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public UpdateEnvironmentUsingPOST1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }

        public UpdateEnvironmentUsingPOST1QueryParams workerGroups(final String value) {
            put("workerGroups", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * verifyEnvironment
     * verify environment
     *
     * @param environmentName environment name (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/environment/verify-environment?environmentName={environmentName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result verifyEnvironmentUsingPOST1(@Param("environmentName") String environmentName);

    /**
     * verifyEnvironment
     * verify environment
     * Note, this is equivalent to the other <code>verifyEnvironmentUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyEnvironmentUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>environmentName - environment name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/environment/verify-environment?environmentName={environmentName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result verifyEnvironmentUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyEnvironmentUsingPOST1</code> method in a fluent style.
     */
    public static class VerifyEnvironmentUsingPOST1QueryParams extends HashMap<String, Object> {
        public VerifyEnvironmentUsingPOST1QueryParams environmentName(final String value) {
            put("environmentName", EncodingUtils.encode(value));
            return this;
        }
    }
}
