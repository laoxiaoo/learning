package com.xiao.controller;

import com.xiao.openfeign.UserFeign;
import com.xiao.vo.UserVO;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiao ji hao
 * @create 2021年07月04日 10:48:00
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/getOrderUser")
    public UserVO getOrderUser() {
        return userFeign.getUser(1L);
    }


}
