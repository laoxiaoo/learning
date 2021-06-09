package com.xiao.controller;

import com.xiao.manager.DemoManager;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author malone xiao
 * @create 2021年06月08日 11:52:00
 */
@RestController
public class DemoController {

    @DubboReference(version = "1.0.0")
    public DemoManager demoManager;


    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return demoManager.sayHello(name);
    }

}
