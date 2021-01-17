package com.xiao.service.impl;

import com.xiao.service.MyService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName MyServiceImpl.java
 * @Description
 * @createTime 2021年01月10日 11:25:00
 */
@Component
public class MyServiceImpl implements MyService {

    @Override
    public Boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.contains(new SimpleGrantedAuthority("role1"));
    }
}
