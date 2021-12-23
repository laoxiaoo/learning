package com.xiao.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;

/**
 * 读取指定地址的配置文件
 *
 * @author xiao jie
 * @create 2021年12月15日 11:24:00
 */
@Configuration
public class PropertiesConfig {

    @Bean("propertiesFactoryBean")
    public PropertiesFactoryBean propertiesFactoryBean(@Value("classpath:/META-INF/test-config.properties")
                                                            Resource resource) {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(resource);
        return propertiesFactoryBean;
    }

    @Value("#{propertiesFactoryBean['name']}")
    public String name;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(PropertiesConfig.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean(PropertiesConfig.class).name);
        applicationContext.close();
    }

}
