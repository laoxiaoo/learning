package com.xiao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName AuthorizationServerConfig.java
 * @Description 开启授权服务器服务端
 * @createTime 2021年01月18日 22:07:00
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //clientid
                .withClient("alonelyxiao")
                //秘钥
                .secret("1233")
                //授权范围
                .scopes("all")
                //重定向地址
                .redirectUris("http://www.baidu.com")
                //授权模式
                .authorizedGrantTypes("authorization_code");
    }
}
