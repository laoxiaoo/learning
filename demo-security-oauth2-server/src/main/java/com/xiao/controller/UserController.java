package com.xiao.controller;

import com.xiao.config.ApiTag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName UserController.java
 * @Description
 * @createTime 2021年04月26日 21:05:00
 */
@RestController
@RequestMapping("/user")
@Api(tags = ApiTag.COMMON)
public class UserController {

    @PostMapping("/getUserInfo")
    @Operation(summary = "获取用户信息", description = "获取用户基本信息")
    public String getUserInfo() {
        return "User";
    }

}
