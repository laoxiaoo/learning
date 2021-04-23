package com.xiao.util;

import cn.hutool.json.JSONObject;
import com.xiao.authentication.JwtUserDetails;
import org.springframework.http.server.ServletServerHttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ResultUtil.java
 * @Description
 * @createTime 2021年01月24日 00:52:00
 */
public class ResultUtil {

    public static void ResultOk(String result, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(result);
    }

}
