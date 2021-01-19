package com.xiao.service;

import com.xiao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName UserService.java
 * @Description
 * @createTime 2021年01月17日 23:22:00
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("123");
        return new User("lonely", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
