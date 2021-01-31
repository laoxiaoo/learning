package com.xiao.in.spring.dependency;

import com.xiao.in.spring.dependency.annotation.AnUser;
import com.xiao.pojo.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName DependencyLookUpDemo.java
 * @Description 依赖查找demo
 * @createTime 2021年01月27日 22:46:00
 */
public class DependencyLookUpDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(DependencyLookUpConfig.class);
        BeanFactory beanFactory = annotationConfigApplicationContext;
        //lookUpLazyTime(beanFactory);
        //lookUpRealTime(beanFactory);
        System.out.println(annotationConfigApplicationContext.getBeanFactory());
        System.out.println(beanFactory);
        lookUpByAnnotation(beanFactory);
    }

    /**
     * 通过注解来查找bean
     * @param beanFactory
     */
    private static void lookUpByAnnotation(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            Map<String, Object> beans = ((ListableBeanFactory) beanFactory).getBeansWithAnnotation(AnUser.class);
            System.out.println(beans.toString());
        }
    }

    /**
     * 及时查找
     * @param beanFactory
     */
    static void lookUpRealTime(BeanFactory beanFactory) {
        Person bean = beanFactory.getBean(Person.class);
        System.out.println(bean);
    }

    /**
     * 延迟查找
     * @param beanFactory
     */
    static void lookUpLazyTime(BeanFactory beanFactory) {
        ObjectFactory<Person> bean = beanFactory.getBean(ObjectFactory.class);
        System.out.println(bean.getObject());
    }
}
