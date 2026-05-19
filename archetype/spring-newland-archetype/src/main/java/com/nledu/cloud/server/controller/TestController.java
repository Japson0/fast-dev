package com.nledu.cloud.server.controller;

import com.nlecloud.spring.annotation.ApiName;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.common.RestResult;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nledu.cloud.server.domain.dto.UserDTO;
import com.nledu.cloud.server.domain.entity.UserEntity;
import com.nledu.cloud.server.domain.query.UserQuery;
import com.nledu.cloud.server.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.github.fastdev.boot.template.BaseController4DTO;
import net.github.fastdev.common.exception.CommonError;
import net.github.fastdev.common.model.RestResponse;
import net.github.fastdev.mybatis.annotation.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月02日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@RestController
@RequestMapping("test")
@Tag(name = "测试类")
public class TestController extends BaseController4DTO<Long, UserEntity, UserDTO> {

    private final UserService userService;


    public TestController(UserService baseService) {
        super(baseService);
        this.userService=baseService;
    }

    @PostMapping("page")
    @Operation(description = "分页查询")
    @ApiName("page")
    public RestResponse page(@RequestBody PageRequest<UserQuery> pageRequest){
        return RestResult.renderSuccess(userService.getPage(pageRequest));
    }


    @PostMapping("customPage")
    @Operation(description = "自定义分页查询")
    public RestResponse customPage(@RequestBody PageRequest<UserQuery> pageRequest){

        return RestResult.renderSuccess(userService.getPage(pageRequest));
    }


    @GetMapping("currentUser")
    @Operation(description = "获取当前用户")
    public RestResponse getCurrentUser(){

        Long userId = UserContext.getUserId();
        UserInfo userInfo = UserContext.getUserInfo();
        return RestResponse.renderSuccess(userInfo);
    }


    @GetMapping("i18n")
    @Operation(description = "测试内置国际化")
    public RestResult i18n(){
        return RestResult.renderError(CommonError.CHECK_CODE_INPUT_ERROR);
    }

    @GetMapping("custom/i18n")
    @Operation(description = "测试自定义国际化")
    public RestResult customI18n(){

        return RestResult.renderError("teaching.notFound");
    }


    @GetMapping("exception")
    @Operation(description = "测试业务异常")
    public RestResult customException(){

        return RestResult.renderSuccess(userService.customException());
    }

}
