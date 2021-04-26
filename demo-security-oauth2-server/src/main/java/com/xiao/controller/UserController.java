package com.xiao.controller;

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
public class UserController {

    @PostMapping("/getUserInfo")
    public String getUserInfo() {
        return "User";
    }

}
