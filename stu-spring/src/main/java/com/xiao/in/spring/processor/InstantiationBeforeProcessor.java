package com.xiao.in.spring.processor;

import com.xiao.in.spring.dependency.SuperPerson;
import com.xiao.pojo.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.ObjectUtils;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName InstantiationBeforeProcessor.java
 * @Description
 * @createTime 2021年02月14日 00:49:00
 */
@PropertySource("某配置1.properties")
@PropertySource("某配置2.properties")
public class InstantiationBeforeProcessor {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InstantiationBeforeProcessor.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new MyInstantiationBeanProcessor());
        });
        applicationContext.refresh();
        System.out.println(applicationContext.getBean("person"));
        applicationContext.close();
    }

    static class MyInstantiationBeanProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            System.out.println(beanClass);
            return null;
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals(beanName, "person")) {
                Person person = Person.class.cast(bean);
                person.setAge(2);
                return true;
            }
            return false;
        }
    }

    @Bean
    public Person person() {
        return new Person(1);
    }

    @Bean
    public SuperPerson superPerson() {
        return new SuperPerson();
    }

}
