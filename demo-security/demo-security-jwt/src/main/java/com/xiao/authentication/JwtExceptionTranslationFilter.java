package com.xiao.authentication;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName JwtExceptionTranslationFilter.java
 * @Description
 * @createTime 2021年01月24日 13:27:00
 */
public class JwtExceptionTranslationFilter extends ExceptionTranslationFilter {
    public JwtExceptionTranslationFilter(AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationEntryPoint);
    }
}
