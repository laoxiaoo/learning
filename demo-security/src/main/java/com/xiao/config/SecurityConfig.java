package com.xiao.config;

import com.xiao.handler.MyAccessDeniedHandler;
import com.xiao.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName SecurityConfig.java
 * @Description
 * @createTime 2021年01月06日 23:42:00
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;
    /*@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123");
        //设置用户名test,密码123，角色为admin
        auth.inMemoryAuthentication().withUser("test").password(password).roles("admin");
    }*/

   /* @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
*/
    /**
     * 密码加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")  //自定义登录页面
                .loginProcessingUrl("/demo/login") //登录访问路径
                .defaultSuccessUrl("/demo/index") //登录成功后的路径
                .permitAll() //这/demo/index路径忽略权限访问
                .and().authorizeRequests() //指定下面的方法哪些需要认证
                .antMatchers("/demo/hello").permitAll()//这个路径不需要认证
                .anyRequest().authenticated().and().csrf().disable();

    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //.usernameParameter("username123")
                //.passwordParameter("password123")
                .loginPage("/login.html")  //自定义登录页面
                .loginProcessingUrl("/demo/login") //登录提交的地址
                .successForwardUrl("/demo/login")//登录成功访问
                //.successHandler(new MyAuthenticationSuccessHandler("www.baidu.com"))
                .failureForwardUrl("/demo/error"); //登录失败
        http.authorizeRequests()
                //不需要认证的页面
                .antMatchers("/login.html").permitAll()
                //.antMatchers("/demo/index.html").hasAnyAuthority("index")
                //.antMatchers("/demo/index.html").access()
                //所有请求都必须通过认证
                .anyRequest().access("@myServiceImpl.hasPermission(request, authentication)");

<<<<<<< HEAD
        //http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
=======
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
>>>>>>> 951f37b8b40436690eb062c35e676b31884acd93
        http.csrf().disable();
    }
}
