package com.xiao.authentication;

import cn.hutool.json.JSONObject;
import com.xiao.util.JwtUtils;
import com.xiao.util.ResultUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName JwtLoginSuccessHandler.java
 * @Description
 * @createTime 2021年01月22日 00:05:00
 */
@Component
public class JwtLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        //签发token
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("data", JwtUtils.generateToken(userDetails.getUserId(), userDetails.getUsername()));
        jsonObject.set("status", 200);
        ResultUtil.ResultOk(jsonObject.toString(), response);
    }

}
