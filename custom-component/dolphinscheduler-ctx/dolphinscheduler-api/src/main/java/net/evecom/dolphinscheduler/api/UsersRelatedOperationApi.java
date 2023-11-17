package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.Result;
import io.swagger.client.model.Resultobject;
import org.threeten.bp.OffsetDateTime;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface UsersRelatedOperationApi extends ApiClient.Api {

    /**
     * activateUser
     * active user
     *
     * @param userName user name (required)
     * @return Resultobject
     */
    @RequestLine("POST /dolphinscheduler/users/activate?userName={userName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject activateUserUsingPOST1(@Param("userName") String userName);

    /**
     * activateUser
     * active user
     * Note, this is equivalent to the other <code>activateUserUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ActivateUserUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userName - user name (required)</li>
     *                    </ul>
     * @return Resultobject
     */
    @RequestLine("POST /dolphinscheduler/users/activate?userName={userName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject activateUserUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>activateUserUsingPOST1</code> method in a fluent style.
     */
    public static class ActivateUserUsingPOST1QueryParams extends HashMap<String, Object> {
        public ActivateUserUsingPOST1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * authorizedUser
     * authorized user
     *
     * @param alertgroupId alert group id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/users/authed-user?alertgroupId={alertgroupId}")
    @Headers({
            "Accept: */*",
    })
    Result authorizedUserUsingGET1(@Param("alertgroupId") String alertgroupId);

    /**
     * authorizedUser
     * authorized user
     * Note, this is equivalent to the other <code>authorizedUserUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link AuthorizedUserUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>alertgroupId - alert group id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/users/authed-user?alertgroupId={alertgroupId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result authorizedUserUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>authorizedUserUsingGET1</code> method in a fluent style.
     */
    public static class AuthorizedUserUsingGET1QueryParams extends HashMap<String, Object> {
        public AuthorizedUserUsingGET1QueryParams alertgroupId(final String value) {
            put("alertgroupId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * batchActivateUser
     * batch active user
     *
     * @return Resultobject
     */
    @RequestLine("POST /dolphinscheduler/users/batch/activate")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject batchActivateUserUsingPOST1();

    /**
     * createUser
     * create user
     *
     * @param email        email (required)
     * @param tenantId     tenant id (required)
     * @param userName     user name (required)
     * @param userPassword user password (required)
     * @param phone        phone (optional)
     * @param queue        queue (optional)
     * @param state        state (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/create?email={email}&phone={phone}&queue={queue}&state={state}&tenantId={tenantId}&userName={userName}&userPassword={userPassword}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createUserUsingPOST1(@Param("email") String email, @Param("tenantId") Integer tenantId, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("phone") String phone, @Param("queue") String queue, @Param("state") Integer state);

    /**
     * createUser
     * create user
     * Note, this is equivalent to the other <code>createUserUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateUserUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>email - email (required)</li>
     *                    <li>phone - phone (optional)</li>
     *                    <li>queue - queue (optional)</li>
     *                    <li>state - state (optional)</li>
     *                    <li>tenantId - tenant id (required)</li>
     *                    <li>userName - user name (required)</li>
     *                    <li>userPassword - user password (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/create?email={email}&phone={phone}&queue={queue}&state={state}&tenantId={tenantId}&userName={userName}&userPassword={userPassword}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createUserUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createUserUsingPOST1</code> method in a fluent style.
     */
    public static class CreateUserUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateUserUsingPOST1QueryParams email(final String value) {
            put("email", EncodingUtils.encode(value));
            return this;
        }

        public CreateUserUsingPOST1QueryParams phone(final String value) {
            put("phone", EncodingUtils.encode(value));
            return this;
        }

        public CreateUserUsingPOST1QueryParams queue(final String value) {
            put("queue", EncodingUtils.encode(value));
            return this;
        }

        public CreateUserUsingPOST1QueryParams state(final Integer value) {
            put("state", EncodingUtils.encode(value));
            return this;
        }

        public CreateUserUsingPOST1QueryParams tenantId(final Integer value) {
            put("tenantId", EncodingUtils.encode(value));
            return this;
        }

        public CreateUserUsingPOST1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }

        public CreateUserUsingPOST1QueryParams userPassword(final String value) {
            put("userPassword", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * delUserById
     * delete user by id
     *
     * @param id user id (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/delete?id={id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result delUserByIdUsingPOST1(@Param("id") Integer id);

    /**
     * delUserById
     * delete user by id
     * Note, this is equivalent to the other <code>delUserByIdUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link DelUserByIdUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>id - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/delete?id={id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result delUserByIdUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>delUserByIdUsingPOST1</code> method in a fluent style.
     */
    public static class DelUserByIdUsingPOST1QueryParams extends HashMap<String, Object> {
        public DelUserByIdUsingPOST1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * getUserInfo
     * get user info
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/users/get-user-info")
    @Headers({
            "Accept: */*",
    })
    Result getUserInfoUsingGET1();

    /**
     * grantDataSource
     * grant datasource
     *
     * @param datasourceIds datasource ids(string format, multiple datasources separated by \&quot;,\&quot;) (required)
     * @param userId        user id (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/grant-datasource?datasourceIds={datasourceIds}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result grantDataSourceUsingPOST1(@Param("datasourceIds") String datasourceIds, @Param("userId") Integer userId);

    /**
     * grantDataSource
     * grant datasource
     * Note, this is equivalent to the other <code>grantDataSourceUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GrantDataSourceUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>datasourceIds - datasource ids(string format, multiple datasources separated by \&quot;,\&quot;) (required)</li>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/grant-datasource?datasourceIds={datasourceIds}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result grantDataSourceUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>grantDataSourceUsingPOST1</code> method in a fluent style.
     */
    public static class GrantDataSourceUsingPOST1QueryParams extends HashMap<String, Object> {
        public GrantDataSourceUsingPOST1QueryParams datasourceIds(final String value) {
            put("datasourceIds", EncodingUtils.encode(value));
            return this;
        }

        public GrantDataSourceUsingPOST1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * grantNamespace
     * GRANT_NAMESPACE_NOTES
     *
     * @param namespaceIds NAMESPACE_IDS (required)
     * @param userId       user id (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/grant-namespace?namespaceIds={namespaceIds}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result grantNamespaceUsingPOST1(@Param("namespaceIds") String namespaceIds, @Param("userId") Integer userId);

    /**
     * grantNamespace
     * GRANT_NAMESPACE_NOTES
     * Note, this is equivalent to the other <code>grantNamespaceUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GrantNamespaceUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>namespaceIds - NAMESPACE_IDS (required)</li>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/grant-namespace?namespaceIds={namespaceIds}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result grantNamespaceUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>grantNamespaceUsingPOST1</code> method in a fluent style.
     */
    public static class GrantNamespaceUsingPOST1QueryParams extends HashMap<String, Object> {
        public GrantNamespaceUsingPOST1QueryParams namespaceIds(final String value) {
            put("namespaceIds", EncodingUtils.encode(value));
            return this;
        }

        public GrantNamespaceUsingPOST1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * grantProjectByCode
     * GRANT PROJECT BY CODE
     *
     * @param projectCode project code (required)
     * @param userId      user id (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/grant-project-by-code?projectCode={projectCode}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result grantProjectByCodeUsingPOST1(@Param("projectCode") Long projectCode, @Param("userId") Integer userId);

    /**
     * grantProjectByCode
     * GRANT PROJECT BY CODE
     * Note, this is equivalent to the other <code>grantProjectByCodeUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GrantProjectByCodeUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>projectCode - project code (required)</li>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/grant-project-by-code?projectCode={projectCode}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result grantProjectByCodeUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>grantProjectByCodeUsingPOST1</code> method in a fluent style.
     */
    public static class GrantProjectByCodeUsingPOST1QueryParams extends HashMap<String, Object> {
        public GrantProjectByCodeUsingPOST1QueryParams projectCode(final Long value) {
            put("projectCode", EncodingUtils.encode(value));
            return this;
        }

        public GrantProjectByCodeUsingPOST1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * grantProject
     * GRANT PROJECT
     *
     * @param projectIds project ids(string format, multiple projects separated by \&quot;,\&quot;) (required)
     * @param userId     user id (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/grant-project?projectIds={projectIds}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result grantProjectUsingPOST1(@Param("projectIds") String projectIds, @Param("userId") Integer userId);

    /**
     * grantProject
     * GRANT PROJECT
     * Note, this is equivalent to the other <code>grantProjectUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GrantProjectUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>projectIds - project ids(string format, multiple projects separated by \&quot;,\&quot;) (required)</li>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/grant-project?projectIds={projectIds}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result grantProjectUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>grantProjectUsingPOST1</code> method in a fluent style.
     */
    public static class GrantProjectUsingPOST1QueryParams extends HashMap<String, Object> {
        public GrantProjectUsingPOST1QueryParams projectIds(final String value) {
            put("projectIds", EncodingUtils.encode(value));
            return this;
        }

        public GrantProjectUsingPOST1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * grantResource
     * grant resource file
     *
     * @param resourceIds resource ids(string format, multiple resources separated by \&quot;,\&quot;) (required)
     * @param userId      user id (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/grant-file?resourceIds={resourceIds}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result grantResourceUsingPOST1(@Param("resourceIds") String resourceIds, @Param("userId") Integer userId);

    /**
     * grantResource
     * grant resource file
     * Note, this is equivalent to the other <code>grantResourceUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GrantResourceUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>resourceIds - resource ids(string format, multiple resources separated by \&quot;,\&quot;) (required)</li>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/grant-file?resourceIds={resourceIds}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result grantResourceUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>grantResourceUsingPOST1</code> method in a fluent style.
     */
    public static class GrantResourceUsingPOST1QueryParams extends HashMap<String, Object> {
        public GrantResourceUsingPOST1QueryParams resourceIds(final String value) {
            put("resourceIds", EncodingUtils.encode(value));
            return this;
        }

        public GrantResourceUsingPOST1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * grantUDFFunc
     * grant udf function
     *
     * @param udfIds udf ids(string format, multiple udf functions separated by \&quot;,\&quot;) (required)
     * @param userId user id (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/grant-udf-func?udfIds={udfIds}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result grantUDFFuncUsingPOST1(@Param("udfIds") String udfIds, @Param("userId") Integer userId);

    /**
     * grantUDFFunc
     * grant udf function
     * Note, this is equivalent to the other <code>grantUDFFuncUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link GrantUDFFuncUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>udfIds - udf ids(string format, multiple udf functions separated by \&quot;,\&quot;) (required)</li>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/grant-udf-func?udfIds={udfIds}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result grantUDFFuncUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>grantUDFFuncUsingPOST1</code> method in a fluent style.
     */
    public static class GrantUDFFuncUsingPOST1QueryParams extends HashMap<String, Object> {
        public GrantUDFFuncUsingPOST1QueryParams udfIds(final String value) {
            put("udfIds", EncodingUtils.encode(value));
            return this;
        }

        public GrantUDFFuncUsingPOST1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * listAll
     *
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
    @RequestLine("GET /dolphinscheduler/users/list-all?alertGroup={alertGroup}&createTime={createTime}&email={email}&id={id}&phone={phone}&queue={queue}&queueName={queueName}&state={state}&tenantCode={tenantCode}&tenantId={tenantId}&timeZone={timeZone}&updateTime={updateTime}&userName={userName}&userPassword={userPassword}&userType={userType}")
    @Headers({
            "Accept: */*",
    })
    Result listAllUsingGET1(@Param("alertGroup") String alertGroup, @Param("createTime") OffsetDateTime createTime, @Param("email") String email, @Param("id") Integer id, @Param("phone") String phone, @Param("queue") String queue, @Param("queueName") String queueName, @Param("state") Integer state, @Param("tenantCode") String tenantCode, @Param("tenantId") Integer tenantId, @Param("timeZone") String timeZone, @Param("updateTime") OffsetDateTime updateTime, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userType") String userType);

    /**
     * listAll
     * <p>
     * Note, this is equivalent to the other <code>listAllUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ListAllUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
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
    @RequestLine("GET /dolphinscheduler/users/list-all?alertGroup={alertGroup}&createTime={createTime}&email={email}&id={id}&phone={phone}&queue={queue}&queueName={queueName}&state={state}&tenantCode={tenantCode}&tenantId={tenantId}&timeZone={timeZone}&updateTime={updateTime}&userName={userName}&userPassword={userPassword}&userType={userType}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result listAllUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>listAllUsingGET1</code> method in a fluent style.
     */
    public static class ListAllUsingGET1QueryParams extends HashMap<String, Object> {
        public ListAllUsingGET1QueryParams alertGroup(final String value) {
            put("alertGroup", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams createTime(final OffsetDateTime value) {
            put("createTime", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams email(final String value) {
            put("email", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams phone(final String value) {
            put("phone", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams queue(final String value) {
            put("queue", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams queueName(final String value) {
            put("queueName", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams state(final Integer value) {
            put("state", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams tenantCode(final String value) {
            put("tenantCode", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams tenantId(final Integer value) {
            put("tenantId", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams timeZone(final String value) {
            put("timeZone", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams updateTime(final OffsetDateTime value) {
            put("updateTime", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams userPassword(final String value) {
            put("userPassword", EncodingUtils.encode(value));
            return this;
        }

        public ListAllUsingGET1QueryParams userType(final String value) {
            put("userType", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * listUser
     * list user
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/users/list")
    @Headers({
            "Accept: */*",
    })
    Result listUserUsingGET1();

    /**
     * queryUserList
     * query user list
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/users/list-paging?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Result queryUserListUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryUserList
     * query user list
     * Note, this is equivalent to the other <code>queryUserListUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryUserListUsingGET1QueryParams} class that allows for
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
    @RequestLine("GET /dolphinscheduler/users/list-paging?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryUserListUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryUserListUsingGET1</code> method in a fluent style.
     */
    public static class QueryUserListUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryUserListUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryUserListUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryUserListUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * registerUser
     * register user
     *
     * @param email          email (required)
     * @param repeatPassword repeat password (required)
     * @param userName       user name (required)
     * @param userPassword   user password (required)
     * @return Resultobject
     */
    @RequestLine("POST /dolphinscheduler/users/register?email={email}&repeatPassword={repeatPassword}&userName={userName}&userPassword={userPassword}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject registerUserUsingPOST1(@Param("email") String email, @Param("repeatPassword") String repeatPassword, @Param("userName") String userName, @Param("userPassword") String userPassword);

    /**
     * registerUser
     * register user
     * Note, this is equivalent to the other <code>registerUserUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link RegisterUserUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>email - email (required)</li>
     *                    <li>repeatPassword - repeat password (required)</li>
     *                    <li>userName - user name (required)</li>
     *                    <li>userPassword - user password (required)</li>
     *                    </ul>
     * @return Resultobject
     */
    @RequestLine("POST /dolphinscheduler/users/register?email={email}&repeatPassword={repeatPassword}&userName={userName}&userPassword={userPassword}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Resultobject registerUserUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>registerUserUsingPOST1</code> method in a fluent style.
     */
    public static class RegisterUserUsingPOST1QueryParams extends HashMap<String, Object> {
        public RegisterUserUsingPOST1QueryParams email(final String value) {
            put("email", EncodingUtils.encode(value));
            return this;
        }

        public RegisterUserUsingPOST1QueryParams repeatPassword(final String value) {
            put("repeatPassword", EncodingUtils.encode(value));
            return this;
        }

        public RegisterUserUsingPOST1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }

        public RegisterUserUsingPOST1QueryParams userPassword(final String value) {
            put("userPassword", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * revokeProject
     * REVOKE PROJECT FOR USER
     *
     * @param projectCode project code (required)
     * @param userId      user id (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/revoke-project?projectCode={projectCode}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result revokeProjectUsingPOST1(@Param("projectCode") Long projectCode, @Param("userId") Integer userId);

    /**
     * revokeProject
     * REVOKE PROJECT FOR USER
     * Note, this is equivalent to the other <code>revokeProjectUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link RevokeProjectUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>projectCode - project code (required)</li>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/revoke-project?projectCode={projectCode}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result revokeProjectUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>revokeProjectUsingPOST1</code> method in a fluent style.
     */
    public static class RevokeProjectUsingPOST1QueryParams extends HashMap<String, Object> {
        public RevokeProjectUsingPOST1QueryParams projectCode(final Long value) {
            put("projectCode", EncodingUtils.encode(value));
            return this;
        }

        public RevokeProjectUsingPOST1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * unauthorizedUser
     * cancel authorization
     *
     * @param alertgroupId alert group id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/users/unauth-user?alertgroupId={alertgroupId}")
    @Headers({
            "Accept: */*",
    })
    Result unauthorizedUserUsingGET1(@Param("alertgroupId") String alertgroupId);

    /**
     * unauthorizedUser
     * cancel authorization
     * Note, this is equivalent to the other <code>unauthorizedUserUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UnauthorizedUserUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>alertgroupId - alert group id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/users/unauth-user?alertgroupId={alertgroupId}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result unauthorizedUserUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>unauthorizedUserUsingGET1</code> method in a fluent style.
     */
    public static class UnauthorizedUserUsingGET1QueryParams extends HashMap<String, Object> {
        public UnauthorizedUserUsingGET1QueryParams alertgroupId(final String value) {
            put("alertgroupId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateUser
     * update user
     *
     * @param email        email (required)
     * @param id           user id (required)
     * @param tenantId     tenant id (required)
     * @param userName     user name (required)
     * @param userPassword user password (required)
     * @param phone        phone (optional)
     * @param queue        queue (optional)
     * @param state        state (optional)
     * @param timeZone     timeZone (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/update?email={email}&id={id}&phone={phone}&queue={queue}&state={state}&tenantId={tenantId}&userName={userName}&userPassword={userPassword}&timeZone={timeZone}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateUserUsingPOST1(@Param("email") String email, @Param("id") Integer id, @Param("tenantId") Integer tenantId, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("phone") String phone, @Param("queue") String queue, @Param("state") Integer state, @Param("timeZone") String timeZone);

    /**
     * updateUser
     * update user
     * Note, this is equivalent to the other <code>updateUserUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateUserUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>email - email (required)</li>
     *                    <li>id - user id (required)</li>
     *                    <li>phone - phone (optional)</li>
     *                    <li>queue - queue (optional)</li>
     *                    <li>state - state (optional)</li>
     *                    <li>tenantId - tenant id (required)</li>
     *                    <li>userName - user name (required)</li>
     *                    <li>userPassword - user password (required)</li>
     *                    <li>timeZone - timeZone (optional)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/users/update?email={email}&id={id}&phone={phone}&queue={queue}&state={state}&tenantId={tenantId}&userName={userName}&userPassword={userPassword}&timeZone={timeZone}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateUserUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateUserUsingPOST1</code> method in a fluent style.
     */
    public static class UpdateUserUsingPOST1QueryParams extends HashMap<String, Object> {
        public UpdateUserUsingPOST1QueryParams email(final String value) {
            put("email", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUserUsingPOST1QueryParams id(final Integer value) {
            put("id", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUserUsingPOST1QueryParams phone(final String value) {
            put("phone", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUserUsingPOST1QueryParams queue(final String value) {
            put("queue", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUserUsingPOST1QueryParams state(final Integer value) {
            put("state", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUserUsingPOST1QueryParams tenantId(final Integer value) {
            put("tenantId", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUserUsingPOST1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUserUsingPOST1QueryParams userPassword(final String value) {
            put("userPassword", EncodingUtils.encode(value));
            return this;
        }

        public UpdateUserUsingPOST1QueryParams timeZone(final String value) {
            put("timeZone", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * verifyUserName
     * verify user name
     *
     * @param userName user name (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/users/verify-user-name?userName={userName}")
    @Headers({
            "Accept: */*",
    })
    Result verifyUserNameUsingGET1(@Param("userName") String userName);

    /**
     * verifyUserName
     * verify user name
     * Note, this is equivalent to the other <code>verifyUserNameUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyUserNameUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userName - user name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/users/verify-user-name?userName={userName}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result verifyUserNameUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyUserNameUsingGET1</code> method in a fluent style.
     */
    public static class VerifyUserNameUsingGET1QueryParams extends HashMap<String, Object> {
        public VerifyUserNameUsingGET1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }
    }
}
