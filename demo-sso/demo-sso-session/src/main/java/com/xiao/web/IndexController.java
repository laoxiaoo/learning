package com.xiao.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiao ji hao
 * @create 2022年01月07日 20:05:00
 */
@Controller
public class IndexController {

    @GetMapping(value = {"/","index.html"})
    public String index(Model model) {
        //1、查出所有的一级分类
        return "index";
    }

    @GetMapping(value = {"login.html"})
    public String login(Model model) {
        return "login/index";
    }

}
