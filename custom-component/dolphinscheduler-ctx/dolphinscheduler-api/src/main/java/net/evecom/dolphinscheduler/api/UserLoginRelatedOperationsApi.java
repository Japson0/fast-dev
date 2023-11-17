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
public interface UserLoginRelatedOperationsApi extends ApiClient.Api {

    /**
     * login
     * user login
     *
     * @param userName     user name (required)
     * @param userPassword user password (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/login?userName={userName}&userPassword={userPassword}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result loginUsingPOST1(@Param("userName") String userName, @Param("userPassword") String userPassword);

    /**
     * login
     * user login
     * Note, this is equivalent to the other <code>loginUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link LoginUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>userName - user name (required)</li>
     *                    <li>userPassword - user password (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/login?userName={userName}&userPassword={userPassword}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result loginUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>loginUsingPOST1</code> method in a fluent style.
     */
    public static class LoginUsingPOST1QueryParams extends HashMap<String, Object> {
        public LoginUsingPOST1QueryParams userName(final String value) {
            put("userName", EncodingUtils.encode(value));
            return this;
        }

        public LoginUsingPOST1QueryParams userPassword(final String value) {
            put("userPassword", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * signOut
     * logout
     *
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/signOut")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result signOutUsingPOST1();
}
