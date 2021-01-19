package com.xiao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: learning
 * @description: TODO
 * @author: lonely xiao
 * @create: 2020-10-08 15:07
 */
@Controller
public class IndexController {

    @RequestMapping({"/", "/index"})
    public String  index() {
        return "index";
    }
}