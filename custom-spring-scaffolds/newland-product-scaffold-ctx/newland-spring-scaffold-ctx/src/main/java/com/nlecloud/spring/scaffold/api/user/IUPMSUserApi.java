package com.nlecloud.spring.scaffold.api.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "http://nlecloud-upms-user-center") // 使用服务名称
public interface IUPMSUserApi {

    @GetExchange("/api/user/detail/{id}")
    UPMSUserDTO getUserDetailById(@PathVariable("id") String id);
}