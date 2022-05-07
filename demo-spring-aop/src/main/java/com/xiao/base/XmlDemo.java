package com.xiao.base;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiao ji hao
 * @create 2022年02月08日 20:55:00
 */
@Aspect
@Configuration
public class XmlDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/aop-context.xml");
        XmlDemo bean = context.getBean(XmlDemo.class);
        System.out.println(bean);
        context.close();
    }

}
