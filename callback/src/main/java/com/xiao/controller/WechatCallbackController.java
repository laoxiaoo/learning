package com.xiao.controller;

import com.xiao.wechat.WorkWechatCallbackUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lao xiao
 * @version 1.
 * @ClassName WechatCallbackController.java
 * @Description TODO
 * @createTime 2021年03月11日 14:33:00
 */
@RestController
@RequestMapping("/wechat")
public class WechatCallbackController {

    @RequestMapping("/workWechatBack")
    public String workWechatBack(HttpServletRequest request) {
        if(HttpMethod.POST.toString().equals(request.getMethod())) {
            System.out.println("work wechat callback: "+request.getQueryString());
            return "success";
        } else if(HttpMethod.GET.toString().equals(request.getMethod())) {
            return WorkWechatCallbackUtils.checkCallbackUrl(request);
        }
        return null;
    }
}
