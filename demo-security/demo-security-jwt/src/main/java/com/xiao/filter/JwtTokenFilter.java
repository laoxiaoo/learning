package com.xiao.filter;

import com.xiao.authentication.JwtLoginToken;
import com.xiao.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName JwtTokenFilter.java
 * @Description
 * @createTime 2021年01月22日 21:29:00
 */
public class JwtTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("authorization");
        Claims claims = JwtUtils.verifyJwt(token);
        JwtLoginToken jwtLoginToken = new JwtLoginToken("user", null, null);
        jwtLoginToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(jwtLoginToken);
        filterChain.doFilter(request, response);
    }
}
