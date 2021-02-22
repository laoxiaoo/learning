package com.xiao.in.spring.dependency;

import com.xiao.pojo.Person;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.annotation.Bean;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName DependencyLookUpConfig.java
 * @Description
 * @createTime 2021年01月27日 22:47:00
 */
@Configurable
public class DependencyLookUpConfig {
    @Bean
    public Person person() {
        return new Person();
    }

    @Bean
    public SuperPerson superPerson() {
        return new SuperPerson();
    }


    @Bean
    public ObjectFactoryCreatingFactoryBean objectFactory() {
        ObjectFactoryCreatingFactoryBean objectFactory = new ObjectFactoryCreatingFactoryBean();
        objectFactory.setTargetBeanName("person");
        return objectFactory;
    }
}
