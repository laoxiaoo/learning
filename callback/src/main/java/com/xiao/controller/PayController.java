package com.xiao.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author lao xiao
 * @version 1.2.8
 * @ClassName payController.java
 * @Description TODO
 * @createTime 2020年11月04日 12:08:00
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @PostMapping("/payBackUrl")
    public void  payBackUrl(@RequestBody String body) {
        System.out.println("============payBackUrl  end==============");
        System.out.println(body);
        System.out.println("============payBackUrl  end==============");
    }

    @PostMapping("/setCompanyBackUrl")
    public void  setCompanyBackUrl(@RequestBody String body) {
        System.out.println("============setCompany  end==============");
        System.out.println(body);
        System.out.println("============setCompany  end==============");
    }

    @PostMapping("/withdrawBack")
    public void  withdrawBack(@RequestBody String body) {
        System.out.println("============withdrawBack  end==============");
        System.out.println(body);
        System.out.println("============withdrawBack  end==============");
    }


}
