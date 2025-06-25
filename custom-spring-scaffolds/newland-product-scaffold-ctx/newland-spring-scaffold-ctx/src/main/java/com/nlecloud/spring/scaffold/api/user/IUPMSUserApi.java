package com.nlecloud.spring.scaffold.api.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nlecloud-upms-user-center",contextId = "getUserId")
public interface IUPMSUserApi {

    @GetMapping("/api/user/detail/{id}")
    UPMSUserDTO getUserDetailById(@PathVariable("id") String id);
}