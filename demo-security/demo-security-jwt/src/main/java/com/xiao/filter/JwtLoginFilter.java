package com.xiao.filter;

import com.xiao.authentication.JwtLoginToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName JwtLoginFilter.java
 * @Description 用户登录验证拦截器
 * @createTime 2021年01月20日 23:28:00
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = super.obtainUsername(request);
        String password = super.obtainPassword(request);
        //创建未认证的凭证
        JwtLoginToken jwtLoginToken = new JwtLoginToken(username, password);

        jwtLoginToken.setDetails(new WebAuthenticationDetails(request));
        //生成已认证的凭证
        Authentication authenticate = super.getAuthenticationManager().authenticate(jwtLoginToken);
        return authenticate;
    }
}
