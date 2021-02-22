package com.xiao.in.spring.definition;

import com.xiao.pojo.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName PropertiesDependencyDemo.java
 * @Description
 * @createTime 2021年02月12日 22:28:00
 */
public class PropertiesDependencyDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "application.properties";
        EncodedResource encodedResource = new EncodedResource(new ClassPathResource(location), "UTF-8");
        int i = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("加载bean数量: " + i);

        Person bean = beanFactory.getBean(Person.class);
        System.out.println(bean);
    }

}
