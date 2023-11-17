package net.evecom.dolphinscheduler.api;

import feign.Headers;
import feign.RequestLine;
import io.swagger.client.ApiClient;
import io.swagger.client.model.ModelAndView;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-08-23T15:58:12.359+08:00[GMT+08:00]")
public interface DsErrorControllerApi extends ApiClient.Api {

    /**
     * handleError
     *
     * @return ModelAndView
     */
    @RequestLine("DELETE /dolphinscheduler/error")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    ModelAndView handleErrorUsingDELETE1();

    /**
     * handleError
     *
     * @return ModelAndView
     */
    @RequestLine("GET /dolphinscheduler/error")
    @Headers({
            "Accept: */*",
    })
    ModelAndView handleErrorUsingGET1();

    /**
     * handleError
     *
     * @return ModelAndView
     */
    @RequestLine("HEAD /dolphinscheduler/error")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    ModelAndView handleErrorUsingHEAD1();

    /**
     * handleError
     *
     * @return ModelAndView
     */
    @RequestLine("OPTIONS /dolphinscheduler/error")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    ModelAndView handleErrorUsingOPTIONS1();

    /**
     * handleError
     *
     * @return ModelAndView
     */
    @RequestLine("PATCH /dolphinscheduler/error")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    ModelAndView handleErrorUsingPATCH1();

    /**
     * handleError
     *
     * @return ModelAndView
     */
    @RequestLine("POST /dolphinscheduler/error")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    ModelAndView handleErrorUsingPOST1();

    /**
     * handleError
     *
     * @return ModelAndView
     */
    @RequestLine("PUT /dolphinscheduler/error")
    @Headers({
            "Content-Type: application/json",
            "Accept: */*",
    })
    ModelAndView handleErrorUsingPUT1();
}
