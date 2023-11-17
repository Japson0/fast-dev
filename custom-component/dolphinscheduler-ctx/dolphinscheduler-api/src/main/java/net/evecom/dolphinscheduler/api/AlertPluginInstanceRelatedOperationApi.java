package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.EncodingUtils;
import io.swagger.client.model.Result;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.HashMap;
import java.util.Map;

@FeignClient(path = "alert-plugin-instances")
public interface AlertPluginInstanceRelatedOperationApi {

    /**
     * createAlertPluginInstance
     * create alert plugin instance operation
     *
     * @param instanceName         alert plugin instance name (required)
     * @param pluginDefineId       alert plugin define id (required)
     * @param pluginInstanceParams alert plugin instance parameters (required)
     * @return Result
     */
    @RequestLine("POST /alert-plugin-instances?instanceName={instanceName}&pluginDefineId={pluginDefineId}&pluginInstanceParams={pluginInstanceParams}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createAlertPluginInstanceUsingPOST1(@Param("instanceName") String instanceName, @Param("pluginDefineId") Integer pluginDefineId, @Param("pluginInstanceParams") String pluginInstanceParams);

    /**
     * createAlertPluginInstance
     * create alert plugin instance operation
     * Note, this is equivalent to the other <code>createAlertPluginInstanceUsingPOST1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link CreateAlertPluginInstanceUsingPOST1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>instanceName - alert plugin instance name (required)</li>
     *                    <li>pluginDefineId - alert plugin define id (required)</li>
     *                    <li>pluginInstanceParams - alert plugin instance parameters (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/alert-plugin-instances?instanceName={instanceName}&pluginDefineId={pluginDefineId}&pluginInstanceParams={pluginInstanceParams}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result createAlertPluginInstanceUsingPOST1(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>createAlertPluginInstanceUsingPOST1</code> method in a fluent style.
     */
    public static class CreateAlertPluginInstanceUsingPOST1QueryParams extends HashMap<String, Object> {
        public CreateAlertPluginInstanceUsingPOST1QueryParams instanceName(final String value) {
            put("instanceName", EncodingUtils.encode(value));
            return this;
        }

        public CreateAlertPluginInstanceUsingPOST1QueryParams pluginDefineId(final Integer value) {
            put("pluginDefineId", EncodingUtils.encode(value));
            return this;
        }

        public CreateAlertPluginInstanceUsingPOST1QueryParams pluginInstanceParams(final String value) {
            put("pluginInstanceParams", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * deleteAlertPluginInstance
     * delete alert plugin instance operation
     *
     * @param id alert plugin id (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/alert-plugin-instances/{id}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteAlertPluginInstanceUsingDELETE1(@Param("id") Integer id);

    /**
     * getAlertPluginInstance
     * get alert plugin instance operation
     *
     * @param id id (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/alert-plugin-instances/{id}")
    @Headers({
            "Accept: */*",
    })
    Result getAlertPluginInstanceUsingGET1(@Param("id") Integer id);

    /**
     * queryAlertPluginInstanceList
     * query all alert plugin instances
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/alert-plugin-instances/list")
    @Headers({
            "Accept: */*",
    })
    Result getAlertPluginInstanceUsingGET3();

    /**
     * queryAlertPluginInstanceListPaging
     * query alert plugin instance paging
     *
     * @param pageNo    page no (required)
     * @param pageSize  page size (required)
     * @param searchVal search val (optional)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/alert-plugin-instances?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Accept: */*",
    })
    Result listPagingUsingGET3(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("searchVal") String searchVal);

    /**
     * queryAlertPluginInstanceListPaging
     * query alert plugin instance paging
     * Note, this is equivalent to the other <code>listPagingUsingGET3</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link ListPagingUsingGET3QueryParams} class that allows for
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
    @RequestLine("GET /dolphinscheduler/alert-plugin-instances?pageNo={pageNo}&pageSize={pageSize}&searchVal={searchVal}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result listPagingUsingGET3(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>listPagingUsingGET3</code> method in a fluent style.
     */
    public static class ListPagingUsingGET3QueryParams extends HashMap<String, Object> {
        public ListPagingUsingGET3QueryParams pageNo(final Integer value) {
            put("pageNo", EncodingUtils.encode(value));
            return this;
        }

        public ListPagingUsingGET3QueryParams pageSize(final Integer value) {
            put("pageSize", EncodingUtils.encode(value));
            return this;
        }

        public ListPagingUsingGET3QueryParams searchVal(final String value) {
            put("searchVal", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * updateAlertPluginInstance
     * update alert plugin instance operation
     *
     * @param id                   id (required)
     * @param instanceName         alert plugin instance name (required)
     * @param pluginInstanceParams alert plugin instance parameters (required)
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/alert-plugin-instances/{id}?instanceName={instanceName}&pluginInstanceParams={pluginInstanceParams}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateAlertPluginInstanceUsingPUT1(@Param("id") Integer id, @Param("instanceName") String instanceName, @Param("pluginInstanceParams") String pluginInstanceParams);

    /**
     * updateAlertPluginInstance
     * update alert plugin instance operation
     * Note, this is equivalent to the other <code>updateAlertPluginInstanceUsingPUT1</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link UpdateAlertPluginInstanceUsingPUT1QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param id          id (required)
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>instanceName - alert plugin instance name (required)</li>
     *                    <li>pluginInstanceParams - alert plugin instance parameters (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("PUT /dolphinscheduler/alert-plugin-instances/{id}?instanceName={instanceName}&pluginInstanceParams={pluginInstanceParams}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result updateAlertPluginInstanceUsingPUT1(@Param("id") Integer id, @QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>updateAlertPluginInstanceUsingPUT1</code> method in a fluent style.
     */
    public static class UpdateAlertPluginInstanceUsingPUT1QueryParams extends HashMap<String, Object> {
        public UpdateAlertPluginInstanceUsingPUT1QueryParams instanceName(final String value) {
            put("instanceName", EncodingUtils.encode(value));
            return this;
        }

        public UpdateAlertPluginInstanceUsingPUT1QueryParams pluginInstanceParams(final String value) {
            put("pluginInstanceParams", EncodingUtils.encode(value));
            return this;
        }
    }

    /**
     * verifyAlertInstanceName
     * verify alert instance name
     *
     * @param alertInstanceName alert instance name (required)
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/alert-plugin-instances/verify-name?alertInstanceName={alertInstanceName}")
    @Headers({
            "Accept: */*",
    })
    Result verifyGroupNameUsingGET3(@Param("alertInstanceName") String alertInstanceName);

    /**
     * verifyAlertInstanceName
     * verify alert instance name
     * Note, this is equivalent to the other <code>verifyGroupNameUsingGET3</code> method,
     * but with the query parameters collected into a single Map parameter. This
     * is convenient for services with optional query parameters, especially when
     * used with the {@link VerifyGroupNameUsingGET3QueryParams} class that allows for
     * building up this map in a fluent style.
     *
     * @param queryParams Map of query parameters as name-value pairs
     *                    <p>The following elements may be specified in the query map:</p>
     *                    <ul>
     *                    <li>alertInstanceName - alert instance name (required)</li>
     *                    </ul>
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/alert-plugin-instances/verify-name?alertInstanceName={alertInstanceName}")
    @Headers({
            "Content-Type: */*",
            "Accept: */*",
    })
    Result verifyGroupNameUsingGET3(@QueryMap(encoded = true) Map<String, Object> queryParams);

    /**
     * A convenience class for generating query parameters for the
     * <code>verifyGroupNameUsingGET3</code> method in a fluent style.
     */
    public static class VerifyGroupNameUsingGET3QueryParams extends HashMap<String, Object> {
        public VerifyGroupNameUsingGET3QueryParams alertInstanceName(final String value) {
            put("alertInstanceName", EncodingUtils.encode(value));
            return this;
        }
    }
}
