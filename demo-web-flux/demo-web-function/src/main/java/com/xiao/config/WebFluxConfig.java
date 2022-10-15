package com.xiao.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.WebHandler;

/**
 * webflux配置类
 * 创建一个WebHandler并使用@EnableWebFlux打开相关功能
 * @author lao xiao
 * @date 2022年09月29日 15:12
 */
@Configuration
@EnableWebFlux
@ComponentScan("com.xiao")
public class WebFluxConfig implements WebFluxConfigurer {

    @Bean
    public WebHandler webHandler(ApplicationContext applicationContext) {
        DispatcherHandler dispatcherHandler = new DispatcherHandler(applicationContext);
        return dispatcherHandler;
    }

}
