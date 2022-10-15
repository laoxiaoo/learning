package com.xiao.in.spring.xml;

import com.xiao.aop.cglib.Person;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * 加载一个xml格式的bean
 *
 * @author xiao ji hao
 * @create 2021年07月12日 15:40:00
 */
public class LoaderXmlBean {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("classpath:/META-INF/my-bean.xml");
        context.refresh();
        Person person = context.getBean(Person.class);
        System.out.println(person);
        context.close();
    }

}
