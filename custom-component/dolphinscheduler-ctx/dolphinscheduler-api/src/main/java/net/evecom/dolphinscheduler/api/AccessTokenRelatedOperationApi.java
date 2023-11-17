package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.CreateTokenRequest;
import io.swagger.client.model.CreateTokenResponse;
import io.swagger.client.model.Result;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface AccessTokenRelatedOperationApi extends ApiClient.Api {

    /**
     * createToken
     * create access token for specified user
     *
     * @param expireTime expire time for the token (required)
     * @param userId     user id (required)
     * @param token      access token string, it will be automatically generated when it absent (optional)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/access-tokens?expireTime={expireTime}&token={token}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createTokenUsingPOST1(@Param("expireTime") String expireTime, @Param("userId") Integer userId, @Param("token") String token);

    /**
     * createToken
     * create access token for specified user
     * Note, this is equivalent to the other <code>createTokenUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateTokenUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>expireTime - expire time for the token (required)</li>
     *                    <li>token - access token string, it will be automatically generated when it absent (optional)</li>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/access-tokens?expireTime={expireTime}&token={token}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createTokenUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createTokenUsingPOST1</code> method in a fluent style.
     */
    public static class CreateTokenUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateTokenUsingPOST1QueryParams expireTime(final String value) {
            put("expireTime", EncodingUtils.encode(value));
            return this;
        }

        public CreateTokenUsingPOST1QueryParams token(final String value) {
            put("token", EncodingUtils.encode(value));
            return this;
        }

        public CreateTokenUsingPOST1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * createTokenV2
     * create access token for specified user (V2)
     *
     * @param body (optional)
     * @return CreateTokenResponse
     */
    @RequestLine("POST /dolphinscheduler/v2/access-tokens")
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json",
    })
    CreateTokenResponse createTokenUsingPOST3(CreateTokenRequest body);

    /**
     * queryAccessTokenByUser
     * query access token for specified user
     *
     * @param userId user id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/access-tokens/user/{userId}")
    @Headers({
            "Accept: */*",
    })
    Result queryAccessTokenByUserUsingGET1(@Param("userId") Integer userId);

    /**
     * queryAccessTokenList
     * query access token list paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/access-tokens?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Result queryAccessTokenListUsingGET1(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryAccessTokenList
     * query access token list paging
     * Note, this is equivalent to the other <code>queryAccessTokenListUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryAccessTokenListUsingGET1QueryParams} class that allows for
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
    @RequestLine("GET /dolphinscheduler/access-tokens?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result queryAccessTokenListUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryAccessTokenListUsingGET1</code> method in a fluent style.
     */
    public static class QueryAccessTokenListUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryAccessTokenListUsingGET1QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public QueryAccessTokenListUsingGET1QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public QueryAccessTokenListUsingGET1QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateToken
     * update access token for specified user
     *
     * @param expireTime expire time for the token (required)
     * @param id         access token id (required)
     * @param userId     user id (required)
     * @param token      access token string, it will be automatically generated when it absent (optional)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/access-tokens/{id}?expireTime={expireTime}&token={token}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateTokenUsingPUT1(@Param("expireTime") String expireTime, @Param("id") Integer id, @Param("userId") Integer userId, @Param("token") String token);

    /**
     * updateToken
     * update access token for specified user
     * Note, this is equivalent to the other <code>updateTokenUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateTokenUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          access token id (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>expireTime - expire time for the token (required)</li>
     *                    <li>token - access token string, it will be automatically generated when it absent (optional)</li>
     *                    <li>userId - user id (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/access-tokens/{id}?expireTime={expireTime}&token={token}&userId={userId}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateTokenUsingPUT1(@Param("id") Integer id, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateTokenUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateTokenUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateTokenUsingPUT1QueryParams expireTime(final String value) {
            put("expireTime", EncodingUtils.encode(value));
            return this;
        }

        public UpdateTokenUsingPUT1QueryParams token(final String value) {
            put("token", EncodingUtils.encode(value));
            return this;
        }

        public UpdateTokenUsingPUT1QueryParams userId(final Integer value) {
            put("userId", EncodingUtils.encode(value));
            return this;
        }
    }
}
