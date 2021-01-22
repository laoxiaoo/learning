package com.xiao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName IndexController.java
 * @Description
 * @createTime 2021年01月22日 21:01:00
 */
@RestController
@Slf4j
public class IndexController {


    @PostMapping("/index")
    public String index() {
        log.debug("==> 进入index");
        return "get index";
    }

}
