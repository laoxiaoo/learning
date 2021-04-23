package com.xiao.authentication;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.entity.TRole;
import com.xiao.entity.TUser;
import com.xiao.service.TRoleService;
import com.xiao.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName JwtUserDetailsService.java
 * @Description
 * @createTime 2021年01月21日 22:13:00
 */
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TUserService userService;

    @Autowired
    private TRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(userService.list());
        TUser user = userService.getOne(new QueryWrapper<TUser>().lambda().eq(TUser::getLoginAccount, username));
        Optional.ofNullable(user).orElseThrow(() -> new RuntimeException("用户不存在"));
        List<TRole> roles = roleService.getRoleByUserId(user.getId());
        List<SimpleGrantedAuthority> authorityList = roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleCode())).collect(Collectors.toList());
        return new JwtUserDetails(user.getLoginAccount(), user.getId(),  passwordEncoder.encode(user.getPassword()), authorityList);
    }
}
