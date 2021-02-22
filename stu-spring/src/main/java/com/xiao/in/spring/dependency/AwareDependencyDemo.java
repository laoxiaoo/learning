package com.xiao.in.spring.dependency;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName AwareDependencyDemo.java
 * @Description
 * @createTime 2021年02月08日 18:20:00
 */
public class AwareDependencyDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext context;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AwareDependencyDemo.class);
        applicationContext.refresh();

        System.out.println(beanFactory == applicationContext.getBeanFactory());
        System.out.println(context == applicationContext);
        applicationContext.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory=beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }
}
