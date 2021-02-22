package com.xiao.in.spring.environment;

import com.xiao.pojo.Person;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.validation.Valid;

/**
 * @author 肖杰
 * @version 1.
 * @ClassName PropertyPlaceholderConfigurerDemo.java
 * @Description TODO
 * @createTime 2021年02月22日 15:36:00
 */
public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PropertyPlaceholderConfigurerDemo.class);
        context.refresh();
        Person bean = context.getBean(Person.class);
        System.out.println(bean);
        context.close();
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(@Value("classpath:/META-INF/person.properties") Resource resource) {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setFileEncoding("UTF-8");
        configurer.setLocation(resource);
        return configurer;
    }

    @ToString
    @Setter
    static class Person {
        @Value("${name}")
        private String name;
    }

    @Bean
    public Person person() {
        return new Person();
    }
}
