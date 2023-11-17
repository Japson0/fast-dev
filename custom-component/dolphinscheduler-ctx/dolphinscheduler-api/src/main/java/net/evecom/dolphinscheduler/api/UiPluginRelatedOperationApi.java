package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePluginType;

import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface UiPluginRelatedOperationApi extends ApiClient.Api {

    /**
     * queryUiPluginDetailById
     * query ui plugin detail by id
     *
     * @param id plugin id (required)
     */
    @RequestLine("GET /dolphinscheduler/ui-plugins/{id}")
    @Headers({
            "Accept: */*",
    })
    void queryUiPluginDetailByIdUsingGET1(@Param("id") Integer id);

    /**
     * queryUiPluginsByType
     * query ui plugins by type
     *
     * @param pluginType pluginType (required)
     */
    @RequestLine("GET /dolphinscheduler/ui-plugins/query-by-type?pluginType={pluginType}")
    @Headers({
            "Accept: */*",
    })
    void queryUiPluginsByTypeUsingGET1(@Param("pluginType") ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePluginType pluginType);

    /**
     * queryUiPluginsByType
     * query ui plugins by type
     * Note, this is equivalent to the other <code>queryUiPluginsByTypeUsingGET1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link QueryUiPluginsByTypeUsingGET1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>pluginType - pluginType (required)</li>
     *                    </ul>
     */
    @RequestLine("GET /dolphinscheduler/ui-plugins/query-by-type?pluginType={pluginType}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    void queryUiPluginsByTypeUsingGET1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>queryUiPluginsByTypeUsingGET1</code> method in a fluent style.
     */
    public static class QueryUiPluginsByTypeUsingGET1QueryParams extends HashMap<String, Object> {
        public QueryUiPluginsByTypeUsingGET1QueryParams pluginType(final ErrorModelNamenamespaceorgApacheDolphinschedulerCommonEnumsNamePluginType value) {
            put("pluginType", EncodingUtils.encode(value));
            return this;
        }
    }
}
