package com.xiao.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiao jie
 * @create 2022年01月06日 19:20:00
 */
@RestController
@RequestMapping("/laoxiao")
@Slf4j
public class LaoxiaoController {

    @GetMapping("/hello")
    public void hello() {
        log.info("hello");
    }

}
