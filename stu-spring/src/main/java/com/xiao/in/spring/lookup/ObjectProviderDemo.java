package com.xiao.in.spring.lookup;

import com.xiao.pojo.Person;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ObjectProviderDemo.java
 * @Description
 * @createTime 2021年02月02日 23:14:00
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ObjectProviderDemo.class);
        lookupIfAvailable(applicationContext);
        lookupIterable(applicationContext);
        applicationContext.close();
    }

    private static void lookupIterable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        for(String bean : beanProvider) {
            System.out.println(bean);
        }
    }

    /**
     * 如果bean不存在则创建
     * @param applicationContext
     */
    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<Person> beanProvider = applicationContext.getBeanProvider(Person.class);
        Person person = beanProvider.getIfAvailable(Person::new);
        System.out.println(person);
    }

    @Bean
    public String getMessage() {
        return "message";
    }

    @Bean
    public String getHello() {
        return "hello";
    }

}
