package com.xiao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName SecurityConfig.java
 * @Description
 * @createTime 2021年01月17日 23:23:00
 */
@EnableGlobalMethodSecurity //全局方法拦截
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/**", "/login/**", "/logout/**")
                .permitAll() //不拦截这些地址
                .anyRequest()
                .authenticated() //其他的都要认证
                .and()
                .formLogin()
                .loginPage("/login.html")  //自定义登录页面
                .permitAll() //表单请求放行
                .and()
                .csrf().disable();
    }
}
