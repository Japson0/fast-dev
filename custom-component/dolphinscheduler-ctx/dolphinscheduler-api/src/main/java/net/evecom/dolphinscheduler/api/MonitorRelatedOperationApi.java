package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.model.Result;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface MonitorRelatedOperationApi extends ApiClient.Api {

    /**
     * listMaster
     * master server list
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/monitor/masters")
    @Headers({
            "Accept: */*",
    })
    Result listMasterUsingGET1();

    /**
     * listWorker
     * worker server list
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/monitor/workers")
    @Headers({
            "Accept: */*",
    })
    Result listWorkerUsingGET1();

    /**
     * queryDatabaseState
     * query database state
     *
     * @return Result
     */
    @RequestLine("GET /dolphinscheduler/monitor/databases")
    @Headers({
            "Accept: */*",
    })
    Result queryDatabaseStateUsingGET1();
}
