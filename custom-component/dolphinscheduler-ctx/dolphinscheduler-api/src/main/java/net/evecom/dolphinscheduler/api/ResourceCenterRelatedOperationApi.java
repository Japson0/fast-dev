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
public interface ResourceCenterRelatedOperationApi extends ApiClient.Api {

    /**
     * authorizeResourceTree
     * authorize resource tree
     *
     * @param userId user id (required)
     */
    @RequestLine("GET /dolphinscheduler/resources/authed-resource-tree?userId={userId}")
    @Headers({
            "Accept: */*",
    })
    void authorizeResourceTreeUsingGET1(@Param("userId") Integer userId);

    /**
     * authorizeResourceTree
     * authorize resource tree
     * Note, this is equivalent to the other <code>authorizeResourceTreeUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link AuthorizeResourceTreeUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     */
    @RequestLine("GET /dolphinscheduler/resources/authed-resource-tree?userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    void authorizeResourceTreeUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>authorizeResourceTreeUsingGET1</code> method in a fluent style.
     */
    public static class AuthorizeResourceTreeUsingGET1QueryParams extends HashMap<String, Object> {
        public AuthorizeResourceTreeUsingGET1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * authorizedFile
     * authorized file
     *
     * @param userId user id (required)
     */
    @RequestLine("GET /dolphinscheduler/resources/authed-file?userId={userId}")
    @Headers({
            "Accept: */*",
    })
    void authorizedFileUsingGET1(@Param("userId") Integer userId);

    /**
     * authorizedFile
     * authorized file
     * Note, this is equivalent to the other <code>authorizedFileUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link AuthorizedFileUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     */
    @RequestLine("GET /dolphinscheduler/resources/authed-file?userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    void authorizedFileUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>authorizedFileUsingGET1</code> method in a fluent style.
     */
    public static class AuthorizedFileUsingGET1QueryParams extends HashMap<String, Object> {
        public AuthorizedFileUsingGET1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * authUDFFunc
     * authorized udf func
     *
     * @param userId user id (required)
     */
    @RequestLine("GET /dolphinscheduler/resources/authed-udf-func?userId={userId}")
    @Headers({
            "Accept: */*",
    })
    void authorizedUDFFunctionUsingGET1(@Param("userId") Integer userId);

    /**
     * authUDFFunc
     * authorized udf func
     * Note, this is equivalent to the other <code>authorizedUDFFunctionUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link AuthorizedUDFFunctionUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     */
    @RequestLine("GET /dolphinscheduler/resources/authed-udf-func?userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    void authorizedUDFFunctionUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>authorizedUDFFunctionUsingGET1</code> method in a fluent style.
     */
    public static class AuthorizedUDFFunctionUsingGET1QueryParams extends HashMap<String, Object> {
        public AuthorizedUDFFunctionUsingGET1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * createDirectory
     * create resource
     *
     * @param currentDir  RESOURCE_CURRENT_DIR (required)
     * @param name        resource name (required)
     * @param pid         parent directory ID of the current resource (required)
     * @param type        resource file type (required)
     * @param description resource file desc (optional)
     * @return Resultobject
     */
    @RequestLine("POST /dolphinscheduler/resources/directory?currentDir={currentDir}&description={description}&name={name}&pid={pid}&type={type}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject createDirectoryUsingPOST1(@Param("currentDir") String currentDir, @Param("name") String name, @Param("pid") Integer pid, @Param("type") ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType type, @Param("description") String description);

    /**
     * createDirectory
     * create resource
     * Note, this is equivalent to the other <code>createDirectoryUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateDirectoryUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>currentDir - RESOURCE_CURRENT_DIR (required)</li>
     *                    <li>description - resource file desc (optional)</li>
     *                    <li>name - resource name (required)</li>
     *                    <li>pid - parent directory ID of the current resource (required)</li>
     *                    <li>type - resource file type (required)</li>
     *                    </ul>
     * @return Resultobject
     */
    @RequestLine("POST /dolphinscheduler/resources/directory?currentDir={currentDir}&description={description}&name={name}&pid={pid}&type={type}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject createDirectoryUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createDirectoryUsingPOST1</code> method in a fluent style.
     */
    public static class CreateDirectoryUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateDirectoryUsingPOST1QueryParams currentDir(final String value) {
            put("currentDir", EncodingUtils.encode(value));
            return this;
        }

        public CreateDirectoryUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public CreateDirectoryUsingPOST1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }

        public CreateDirectoryUsingPOST1QueryParams pid(final Integer value) {
            put("pid", EncodingUtils.encode(value));
            return this;
        }

        public CreateDirectoryUsingPOST1QueryParams type(final ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * createResource
     * create resource
     *
     * @param currentDir  RESOURCE_CURRENT_DIR (required)
     * @param name        resource name (required)
     * @param pid         parent directory ID of the current resource (required)
     * @param type        resource file type (required)
     * @param body        (optional)
     * @param description resource file desc (optional)
     * @return Resultobject
     */
    @RequestLine("POST /dolphinscheduler/resources?currentDir={currentDir}&description={description}&name={name}&pid={pid}&type={type}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject createResourceUsingPOST1(@Param("currentDir") String currentDir, @Param("name") String name, @Param("pid") Integer pid, @Param("type") ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType type, Object body, @Param("description") String description);

    /**
     * createResource
     * create resource
     * Note, this is equivalent to the other <code>createResourceUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateResourceUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param body        (optional)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>currentDir - RESOURCE_CURRENT_DIR (required)</li>
     *                    <li>description - resource file desc (optional)</li>
     *                    <li>name - resource name (required)</li>
     *                    <li>pid - parent directory ID of the current resource (required)</li>
     *                    <li>type - resource file type (required)</li>
     *                    </ul>
     * @return Resultobject
     */
    @RequestLine("POST /dolphinscheduler/resources?currentDir={currentDir}&description={description}&name={name}&pid={pid}&type={type}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject createResourceUsingPOST1(Object body, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createResourceUsingPOST1</code> method in a fluent style.
     */
    public static class CreateResourceUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateResourceUsingPOST1QueryParams currentDir(final String value) {
            put("currentDir", EncodingUtils.encode(value));
            return this;
        }

        public CreateResourceUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public CreateResourceUsingPOST1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }

        public CreateResourceUsingPOST1QueryParams pid(final Integer value) {
            put("pid", EncodingUtils.encode(value));
            return this;
        }

        public CreateResourceUsingPOST1QueryParams type(final ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * createUdfFunc
     * create udf function
     *
     * @param className   package and class name (required)
     * @param funcName    function name (required)
     * @param resourceId  resource id (required)
     * @param type        UDF type (required)
     * @param argTypes    arguments (optional)
     * @param database    database name (optional)
     * @param description udf desc (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/resources/{resourceId}/udf-func?argTypes={argTypes}&className={className}&database={database}&description={description}&funcName={funcName}&type={type}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createUdfFuncUsingPOST1(@Param("className") String className, @Param("funcName") String funcName, @Param("resourceId") Integer resourceId, @Param("type") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameUdfType type, @Param("argTypes") String argTypes, @Param("database") String database, @Param("description") String description);

    /**
     * createUdfFunc
     * create udf function
     * Note, this is equivalent to the other <code>createUdfFuncUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateUdfFuncUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param resourceId  resource id (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>argTypes - arguments (optional)</li>
     *                    <li>className - package and class name (required)</li>
     *                    <li>database - database name (optional)</li>
     *                    <li>description - udf desc (optional)</li>
     *                    <li>funcName - function name (required)</li>
     *                    <li>type - UDF type (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/resources/{resourceId}/udf-func?argTypes={argTypes}&className={className}&database={database}&description={description}&funcName={funcName}&type={type}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createUdfFuncUsingPOST1(@Param("resourceId") Integer resourceId, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createUdfFuncUsingPOST1</code> method in a fluent style.
     */
    public static class CreateUdfFuncUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateUdfFuncUsingPOST1QueryParams argTypes(final String value) {
            put("argTypes", EncodingUtils.encode(value));
            return this;
        }

        public CreateUdfFuncUsingPOST1QueryParams className(final String value) {
            put("className", EncodingUtils.encode(value));
            return this;
        }

        public CreateUdfFuncUsingPOST1QueryParams database(final String value) {
            put("database", EncodingUtils.encode(value));
            return this;
        }

        public CreateUdfFuncUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public CreateUdfFuncUsingPOST1QueryParams funcName(final String value) {
            put("funcName", EncodingUtils.encode(value));
            return this;
        }

        public CreateUdfFuncUsingPOST1QueryParams type(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameUdfType value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * deleteResource
     * delete resource by id
     *
     * @param id resource id (required)
     * @return Resultobject
     */
    @RequestLine("DELETE /dolphinscheduler/resources/{id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject deleteResourceUsingDELETE1(@Param("id") Integer id);

    /**
     * deleteUdfFunc
     * delete udf function
     *
     * @param id UDF_FUNC_ID (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/resources/udf-func/{id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteUdfFuncUsingDELETE1(@Param("id") Integer id);

    /**
     * downloadResource
     * download resource file
     *
     * @param id resource id (required)
     * @return ResponseEntity
     */
    @RequestLine("GET /dolphinscheduler/resources/{id}/download")
    @Headers({
            "Accept: */*",
    })
    ResponseEntity downloadResourceUsingGET1(@Param("id") Integer id);

    /**
     * onlineCreateResource
     * online create resource
     *
     * @param content     resource file content (required)
     * @param currentDir  dir of the current resource (required)
     * @param fileName    resource name (required)
     * @param pid         parent directory ID of the current resource (required)
     * @param suffix      resource file suffix (required)
     * @param type        resource file type (required)
     * @param description resource file desc (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/resources/online-create?content={content}&currentDir={currentDir}&description={description}&fileName={fileName}&pid={pid}&suffix={suffix}&type={type}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result onlineCreateResourceUsingPOST1(@Param("content") String content, @Param("currentDir") String currentDir, @Param("fileName") String fileName, @Param("pid") Integer pid, @Param("suffix") String suffix, @Param("type") ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType type, @Param("description") String description);

    /**
     * onlineCreateResource
     * online create resource
     * Note, this is equivalent to the other <code>onlineCreateResourceUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link OnlineCreateResourceUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>content - resource file content (required)</li>
     *                    <li>currentDir - dir of the current resource (required)</li>
     *                    <li>description - resource file desc (optional)</li>
     *                    <li>fileName - resource name (required)</li>
     *                    <li>pid - parent directory ID of the current resource (required)</li>
     *                    <li>suffix - resource file suffix (required)</li>
     *                    <li>type - resource file type (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/resources/online-create?content={content}&currentDir={currentDir}&description={description}&fileName={fileName}&pid={pid}&suffix={suffix}&type={type}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result onlineCreateResourceUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>onlineCreateResourceUsingPOST1</code> method in a fluent style.
     */
    public static class OnlineCreateResourceUsingPOST1QueryParams extends HashMap<String, Object> {
        public OnlineCreateResourceUsingPOST1QueryParams content(final String value) {
            put("content", EncodingUtils.encode(value));
            return this;
        }

        public OnlineCreateResourceUsingPOST1QueryParams currentDir(final String value) {
            put("currentDir", EncodingUtils.encode(value));
            return this;
        }

        public OnlineCreateResourceUsingPOST1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public OnlineCreateResourceUsingPOST1QueryParams fileName(final String value) {
            put("fileName", EncodingUtils.encode(value));
            return this;
        }

        public OnlineCreateResourceUsingPOST1QueryParams pid(final Integer value) {
            put("pid", EncodingUtils.encode(value));
            return this;
        }

        public OnlineCreateResourceUsingPOST1QueryParams suffix(final String value) {
            put("suffix", EncodingUtils.encode(value));
            return this;
        }

        public OnlineCreateResourceUsingPOST1QueryParams type(final ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryResourceById
     * query by resource name
     *
     * @param id resource id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/resources/{id}/query")
    @Headers({
            "Accept: */*",
    })
    Result queryResourceByIdUsingGET1(@Param("id") Integer id);

    /**
     * queryResourceByProgramType
     * query resource list
     *
     * @param type        resource file type (required)
     * @param programType programType (optional)
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources/query-by-type?type={type}&programType={programType}")
    @Headers({
            "Accept: */*",
    })
    Resultobject queryResourceJarListUsingGET1(@Param("type") ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType type, @Param("programType") String programType);

    /**
     * queryResourceByProgramType
     * query resource list
     * Note, this is equivalent to the other <code>queryResourceJarListUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryResourceJarListUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>type - resource file type (required)</li>
     *                    <li>programType - programType (optional)</li>
     *                    </ul>
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources/query-by-type?type={type}&programType={programType}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Resultobject queryResourceJarListUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryResourceJarListUsingGET1</code> method in a fluent style.
     */
    public static class QueryResourceJarListUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryResourceJarListUsingGET1QueryParams type(final ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }

        public QueryResourceJarListUsingGET1QueryParams programType(final String value) {
            put("programType", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryResourceListPaging
     * query resource list paging
     *
     * @param id        resource id (required)
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param type      resource file type (required)
     * @param searchVal search val (optional)
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources?id={id}&pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}&type={type}")
    @Headers({
            "Accept: */*",
    })
    Resultobject queryResourceListPagingUsingGET1(@Param("id") Integer id, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("type") ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType type, @Param("searchVal") String searchVal);

    /**
     * queryResourceListPaging
     * query resource list paging
     * Note, this is equivalent to the other <code>queryResourceListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryResourceListPagingUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>id - resource id (required)</li>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>searchVal - search val (optional)</li>
     *                    <li>type - resource file type (required)</li>
     *                    </ul>
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources?id={id}&pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}&type={type}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Resultobject queryResourceListPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryResourceListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryResourceListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryResourceListPagingUsingGET1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }

        public QueryResourceListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryResourceListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryResourceListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }

        public QueryResourceListPagingUsingGET1QueryParams type(final ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryResourceList
     * query resource list
     *
     * @param type resource file type (required)
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources/list?type={type}")
    @Headers({
            "Accept: */*",
    })
    Resultobject queryResourceListUsingGET1(@Param("type") ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType type);

    /**
     * queryResourceList
     * query resource list
     * Note, this is equivalent to the other <code>queryResourceListUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryResourceListUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>type - resource file type (required)</li>
     *                    </ul>
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources/list?type={type}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Resultobject queryResourceListUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryResourceListUsingGET1</code> method in a fluent style.
     */
    public static class QueryResourceListUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryResourceListUsingGET1QueryParams type(final ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryResource
     * query by resource name
     *
     * @param fullName resource full name (required)
     * @param id       resource id (required)
     * @param type     resource file type (required)
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources/{id}?fullName={fullName}&type={type}")
    @Headers({
            "Accept: */*",
    })
    Resultobject queryResourceUsingGET1(@Param("fullName") String fullName, @Param("id") Integer id, @Param("type") ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType type);

    /**
     * queryResource
     * query by resource name
     * Note, this is equivalent to the other <code>queryResourceUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryResourceUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          resource id (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>fullName - resource full name (required)</li>
     *                    <li>type - resource file type (required)</li>
     *                    </ul>
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources/{id}?fullName={fullName}&type={type}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Resultobject queryResourceUsingGET1(@Param("id") Integer id, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryResourceUsingGET1</code> method in a fluent style.
     */
    public static class QueryResourceUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryResourceUsingGET1QueryParams fullName(final String value) {
            put("fullName", EncodingUtils.encode(value));
            return this;
        }

        public QueryResourceUsingGET1QueryParams type(final ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryUdfFuncListPaging
     * query udf function list paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources/udf-func?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Resultobject queryUdfFuncListPagingUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryUdfFuncListPaging
     * query udf function list paging
     * Note, this is equivalent to the other <code>queryUdfFuncListPagingUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryUdfFuncListPagingUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>pageNo - page no (required)</li>
     *                    <li>pageSize - page size (required)</li>
     *                    <li>searchVal - search val (optional)</li>
     *                    </ul>
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources/udf-func?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Resultobject queryUdfFuncListPagingUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryUdfFuncListPagingUsingGET1</code> method in a fluent style.
     */
    public static class QueryUdfFuncListPagingUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryUdfFuncListPagingUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryUdfFuncListPagingUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryUdfFuncListPagingUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * queryUdfFuncList
     * query udf funciton list
     *
     * @param type UDF type (required)
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources/udf-func/list?type={type}")
    @Headers({
            "Accept: */*",
    })
    Resultobject queryUdfFuncListUsingGET1(@Param("type") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameUdfType type);

    /**
     * queryUdfFuncList
     * query udf funciton list
     * Note, this is equivalent to the other <code>queryUdfFuncListUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryUdfFuncListUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>type - UDF type (required)</li>
     *                    </ul>
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources/udf-func/list?type={type}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Resultobject queryUdfFuncListUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryUdfFuncListUsingGET1</code> method in a fluent style.
     */
    public static class QueryUdfFuncListUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryUdfFuncListUsingGET1QueryParams type(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameUdfType value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * unauthUDFFunc
     * unauthorized udf func
     *
     * @param userId user id (required)
     */
    @RequestLine("GET /dolphinscheduler/resources/unauth-udf-func?userId={userId}")
    @Headers({
            "Accept: */*",
    })
    void unauthUDFFuncUsingGET1(@Param("userId") Integer userId);

    /**
     * unauthUDFFunc
     * unauthorized udf func
     * Note, this is equivalent to the other <code>unauthUDFFuncUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UnauthUDFFuncUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     */
    @RequestLine("GET /dolphinscheduler/resources/unauth-udf-func?userId={userId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    void unauthUDFFuncUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>unauthUDFFuncUsingGET1</code> method in a fluent style.
     */
    public static class UnauthUDFFuncUsingGET1QueryParams extends HashMap<String, Object> {
        public UnauthUDFFuncUsingGET1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateResourceContent
     * edit resource file online
     *
     * @param content resource file content (required)
     * @param id      resource id (required)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/resources/{id}/update-content?content={content}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateResourceContentUsingPUT1(@Param("content") String content, @Param("id") Integer id);

    /**
     * updateResourceContent
     * edit resource file online
     * Note, this is equivalent to the other <code>updateResourceContentUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateResourceContentUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          resource id (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>content - resource file content (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/resources/{id}/update-content?content={content}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateResourceContentUsingPUT1(@Param("id") Integer id, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateResourceContentUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateResourceContentUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateResourceContentUsingPUT1QueryParams content(final String value) {
            put("content", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateResource
     * edit resource file online
     *
     * @param name        resource name (required)
     * @param type        resource file type (required)
     * @param id          resource id (required)
     * @param body        (optional)
     * @param description resource file desc (optional)
     * @return Resultobject
     */
    @RequestLine("PUT /dolphinscheduler/resources/{id}?description={description}&name={name}&type={type}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject updateResourceUsingPUT1(@Param("name") String name, @Param("type") ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType type, @Param("id") Integer id, Object body, @Param("description") String description);

    /**
     * updateResource
     * edit resource file online
     * Note, this is equivalent to the other <code>updateResourceUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateResourceUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          resource id (required)
     * @param body        (optional)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>description - resource file desc (optional)</li>
     *                    <li>name - resource name (required)</li>
     *                    <li>type - resource file type (required)</li>
     *                    </ul>
     * @return Resultobject
     */
    @RequestLine("PUT /dolphinscheduler/resources/{id}?description={description}&name={name}&type={type}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject updateResourceUsingPUT1(@Param("id") Integer id, Object body, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateResourceUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateResourceUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateResourceUsingPUT1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public UpdateResourceUsingPUT1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }

        public UpdateResourceUsingPUT1QueryParams type(final ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateUdfFunc
     * update udf function
     *
     * @param className   package and class name (required)
     * @param funcName    function name (required)
     * @param id          udf id (required)
     * @param resourceId  resource id (required)
     * @param type        UDF type (required)
     * @param argTypes    arguments (optional)
     * @param database    database name (optional)
     * @param description udf desc (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/resources/{resourceId}/udf-func/{id}?argTypes={argTypes}&className={className}&database={database}&description={description}&funcName={funcName}&type={type}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateUdfFuncUsingPUT1(@Param("className") String className, @Param("funcName") String funcName, @Param("id") Integer id, @Param("resourceId") Integer resourceId, @Param("type") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameUdfType type, @Param("argTypes") String argTypes, @Param("database") String database, @Param("description") String description);

    /**
     * updateUdfFunc
     * update udf function
     * Note, this is equivalent to the other <code>updateUdfFuncUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateUdfFuncUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          udf id (required)
     * @param resourceId  resource id (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>argTypes - arguments (optional)</li>
     *                    <li>className - package and class name (required)</li>
     *                    <li>database - database name (optional)</li>
     *                    <li>description - udf desc (optional)</li>
     *                    <li>funcName - function name (required)</li>
     *                    <li>type - UDF type (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/resources/{resourceId}/udf-func/{id}?argTypes={argTypes}&className={className}&database={database}&description={description}&funcName={funcName}&type={type}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateUdfFuncUsingPUT1(@Param("id") Integer id, @Param("resourceId") Integer resourceId, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateUdfFuncUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateUdfFuncUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateUdfFuncUsingPUT1QueryParams argTypes(final String value) {
            put("argTypes", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUdfFuncUsingPUT1QueryParams className(final String value) {
            put("className", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUdfFuncUsingPUT1QueryParams database(final String value) {
            put("database", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUdfFuncUsingPUT1QueryParams description(final String value) {
            put("description", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUdfFuncUsingPUT1QueryParams funcName(final String value) {
            put("funcName", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUdfFuncUsingPUT1QueryParams type(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNameUdfType value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * verifyResourceName
     * verify resource name
     *
     * @param fullName resource full name (required)
     * @param type     resource file type (required)
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources/verify-name?fullName={fullName}&type={type}")
    @Headers({
            "Accept: */*",
    })
    Resultobject verifyResourceNameUsingGET1(@Param("fullName") String fullName, @Param("type") ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType type);

    /**
     * verifyResourceName
     * verify resource name
     * Note, this is equivalent to the other <code>verifyResourceNameUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyResourceNameUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>fullName - resource full name (required)</li>
     *                    <li>type - resource file type (required)</li>
     *                    </ul>
     * @return Resultobject
     */
    @RequestLine("GET /dolphinscheduler/resources/verify-name?fullName={fullName}&type={type}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Resultobject verifyResourceNameUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyResourceNameUsingGET1</code> method in a fluent style.
     */
    public static class VerifyResourceNameUsingGET1QueryParams extends HashMap<String, Object> {
        public VerifyResourceNameUsingGET1QueryParams fullName(final String value) {
            put("fullName", EncodingUtils.encode(value));
            return this;
        }

        public VerifyResourceNameUsingGET1QueryParams type(final ErrorModelNamenamespaceorgApacheDolphinschedulerSpiEnumsNameResourceType value) {
            put("type", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * verifyUdfFuncName
     * verify udf function name
     *
     * @param name function name (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/resources/udf-func/verify-name?name={name}")
    @Headers({
            "Accept: */*",
    })
    Result verifyUdfFuncNameUsingGET1(@Param("name") String name);

    /**
     * verifyUdfFuncName
     * verify udf function name
     * Note, this is equivalent to the other <code>verifyUdfFuncNameUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyUdfFuncNameUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>name - function name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/resources/udf-func/verify-name?name={name}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result verifyUdfFuncNameUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyUdfFuncNameUsingGET1</code> method in a fluent style.
     */
    public static class VerifyUdfFuncNameUsingGET1QueryParams extends HashMap<String, Object> {
        public VerifyUdfFuncNameUsingGET1QueryParams name(final String value) {
            put("name", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * viewResource
     * view resource by id
     *
     * @param id          resource id (required)
     * @param limit       limit (required)
     * @param skipLineNum skip line num (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/resources/{id}/view?limit={limit}&skipLineNum={skipLineNum}")
    @Headers({
            "Accept: */*",
    })
    Result viewResourceUsingGET1(@Param("id") Integer id, @Param("limit") Integer limit, @Param("skipLineNum") Integer skipLineNum);

    /**
     * viewResource
     * view resource by id
     * Note, this is equivalent to the other <code>viewResourceUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ViewResourceUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          resource id (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>limit - limit (required)</li>
     *                    <li>skipLineNum - skip line num (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/resources/{id}/view?limit={limit}&skipLineNum={skipLineNum}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result viewResourceUsingGET1(@Param("id") Integer id, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>viewResourceUsingGET1</code> method in a fluent style.
     */
    public static class ViewResourceUsingGET1QueryParams extends HashMap<String, Object> {
        public ViewResourceUsingGET1QueryParams limit(final Integer value) {
            put("limit", EncodingUtils.encode(value));
            return this;
        }

        public ViewResourceUsingGET1QueryParams skipLineNum(final Integer value) {
            put("skipLineNum", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * viewUIUdfFunction
     * view udf function
     *
     * @param id resource id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/resources/{id}/udf-func")
    @Headers({
            "Accept: */*",
    })
    Result viewUIUdfFunctionUsingGET1(@Param("id") Integer id);
}
