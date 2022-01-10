package com.xiao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiao ji hao
 * @create 2022年01月09日 22:18:00
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(String name) {
        return "hello"+name;
    }

}
