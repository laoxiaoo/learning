package com.xiao.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiao ji hao
 * @create 2022年01月09日 10:26:00
 */
public class LoginController {


    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        if("admin".equals(username)) {
            return "500";
        }
        return "/404";
    }

}
