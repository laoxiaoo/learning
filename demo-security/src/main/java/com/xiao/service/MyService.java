package com.xiao.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName MyService.java
 * @Description
 * @createTime 2021年01月10日 11:25:00
 */
public interface MyService {

    Boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
