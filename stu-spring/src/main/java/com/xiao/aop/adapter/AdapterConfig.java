package com.xiao.aop.adapter;


import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author xiao ji hao
 * @create 2021年10月21日 08:17:00
 */
@Configuration
public class AdapterConfig {

    @Bean("proxyBean")
    public ProxyFactoryBean proxyBean(HelloService helloService) throws Exception {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(helloService);
        proxyFactoryBean.setInterceptorNames("helloAdapter");
        return proxyFactoryBean;
    }
}
