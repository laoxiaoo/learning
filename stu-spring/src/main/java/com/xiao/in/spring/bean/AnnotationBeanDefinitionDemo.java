package com.xiao.in.spring.bean;

import com.xiao.pojo.Person;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName AnnotationBeanDefinitionDemo.java
 * @Description
 * @createTime 2021年01月31日 00:16:00
 */
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnnotationBeanDefinitionDemo.class);

        //命名的方式注册
        registryBeanDefinition(applicationContext, "my-person");
        //非命名的方式
        registryBeanDefinition(applicationContext);

        //获取bean信息
        System.out.println(applicationContext.getBeansOfType(Person.class));
        applicationContext.close();
    }

    private static void registryBeanDefinition(BeanDefinitionRegistry registry, String name) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Person.class);
        beanDefinitionBuilder.addPropertyValue("name", "张三").addPropertyValue("age", 12);
        if(StringUtils.isEmpty(name)) {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
            return;
        }
        registry.registerBeanDefinition(name, beanDefinitionBuilder.getBeanDefinition());

    }

    private static void registryBeanDefinition(BeanDefinitionRegistry registry) {
        registryBeanDefinition(registry, null);
    }

    @Bean(initMethod = "initMethod")
    public Person person() {
        return new Person();
    }

}
