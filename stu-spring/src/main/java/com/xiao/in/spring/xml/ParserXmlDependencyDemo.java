package com.xiao.in.spring.xml;

import com.xiao.pojo.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ParserXmlDependencyDemo.java
 * @Description
 * @createTime 2021年02月17日 16:03:00
 */
public class ParserXmlDependencyDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/person-context.xml");
        Person bean = beanFactory.getBean(Person.class);
        System.out.println(bean);
    }
}
