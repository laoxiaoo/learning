package com.xiao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
<<<<<<< HEAD
        List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
=======
        List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
>>>>>>> 951f37b8b40436690eb062c35e676b31884acd93
        return new User("test", passwordEncoder.encode("1234"), role);
    }
}