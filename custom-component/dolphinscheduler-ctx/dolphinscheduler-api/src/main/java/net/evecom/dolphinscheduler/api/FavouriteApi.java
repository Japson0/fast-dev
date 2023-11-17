package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.model.Result;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface FavouriteApi extends ApiClient.Api {

    /**
     * addTaskType
     * add task type
     *
     * @param taskName taskName (required)
     * @return Result
     */
    @RequestLine("POST /dolphinscheduler/favourite/{taskName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result addFavTaskUsingPOST1(@Param("taskName") String taskName);

    /**
     * deleteTaskType
     * delete task type
     *
     * @param taskName taskName (required)
     * @return Result
     */
    @RequestLine("DELETE /dolphinscheduler/favourite/{taskName}")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    Result deleteFavTaskUsingDELETE1(@Param("taskName") String taskName);

    /**
     * listTaskType
     * query task type list
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/favourite/taskTypes")
    @Headers({
            "Accept: */*",
    })
    Result listTaskTypeUsingGET1();
}
