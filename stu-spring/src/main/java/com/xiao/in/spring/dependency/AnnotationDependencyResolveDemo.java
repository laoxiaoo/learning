package com.xiao.in.spring.dependency;

import com.xiao.aop.cglib.Person;
import com.xiao.in.spring.dependency.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName AnnotationDependencyResolveDemo.java
 * @Description 注解注入 注入过程
 * @createTime 2021年02月09日 18:34:00
 */
public class AnnotationDependencyResolveDemo {
    @Autowired
    private Person person;

    @InjectPerson
    private Person injectPerson;

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectPerson.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyResolveDemo.class);
        applicationContext.refresh();
        AnnotationDependencyResolveDemo resolveDemo = applicationContext.getBean(AnnotationDependencyResolveDemo.class);
        System.out.println(resolveDemo.person);
        System.out.println(resolveDemo.injectPerson);
        applicationContext.close();
    }


    @Bean
    public Person person() {
        return new Person(1);
    }
}
