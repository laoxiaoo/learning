package com.xiao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ReSourceConfig.java
 * @Description
 * @createTime 2021年04月26日 21:07:00
 */
@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

       @Override
       public void configure(HttpSecurity http) throws Exception {
           http.authorizeRequests().anyRequest().authenticated()
                   .and().requestMatchers().antMatchers("/user/**");
       }

}
