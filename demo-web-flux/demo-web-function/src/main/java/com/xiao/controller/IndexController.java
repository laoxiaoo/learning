package com.xiao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lao xiao
 * @date 2022年09月29日 15:23
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
