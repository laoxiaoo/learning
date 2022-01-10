package com.xiao.web;

import com.xiao.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xiao ji hao
 * @create 2022年01月07日 21:38:00
 */
@Controller
public class LoginController {


    @PostMapping("/login")
    public String login(LoginDTO loginDTO, HttpSession session, HttpServletResponse response) {
        session.setAttribute("admin", loginDTO.getUsername());
        return "redirect:http://laoxiao.com";
    }


}
