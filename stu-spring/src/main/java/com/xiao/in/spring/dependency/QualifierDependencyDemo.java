package com.xiao.in.spring.dependency;

import com.xiao.in.spring.dependency.annotation.GroupBean;
import com.xiao.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName QualifireDependencyDemo.java
 * @Description
 * @createTime 2021年02月09日 16:12:00
 */
public class QualifierDependencyDemo {

    @Autowired
    private List<Person> persons;

    @Autowired
    @Qualifier
    private List<Person> person;

/*
    @Autowired
    @GroupBean
    private List<Person> persons3;
*/


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierDependencyDemo.class);
        applicationContext.refresh();

        QualifierDependencyDemo bean = applicationContext.getBean(QualifierDependencyDemo.class);
        //person person1 person2
        System.out.println(bean.persons);
        //person2, person3
        System.out.println(bean.person);
        //person3
        //System.out.println(bean.persons3);
        applicationContext.close();
    }

    @Bean
    public SuperPerson superPerson() {
        return new SuperPerson();
    }

    @Bean
    public Person person1() {
        return new Person(1);
    }

    @Bean
    @Qualifier
    public Person person2() {
        return new Person(2);
    }

//    @Bean
//    @GroupBean
//    public Person person3() {
//        return new Person(3);
//    }

}
