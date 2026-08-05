package com.nlecloud.spring.scaffold.api.user;

import com.nlecloud.spring.annotation.api.UserInfoDetail;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface IUPMSUserApi {

    @GetExchange("/api/user/detail/{id}")
    UserInfoDetail getUserDetailById(@PathVariable("id") String id);
}