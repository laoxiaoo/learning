package com.xiao.in.spring.processor;

import com.xiao.in.spring.dependency.SuperPerson;
import com.xiao.pojo.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ObjectUtils;

/**
 * bean实例化前  和  实例化后调用
 *
 *
 *
 *
 * @createTime 2021年02月14日 00:49:00
 */
//@PropertySource("某配置1.properties")
//@PropertySource("application.yml")
public class InstantiationBeforeProcessor {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocations(new String[]{"classpath:/META-INF/my-bean.xml"});
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new MyInstantiationBeanProcessor());
        });
        applicationContext.refresh();
        System.out.println(applicationContext.getBean("person"));
        applicationContext.close();
    }

    static class MyInstantiationBeanProcessor implements InstantiationAwareBeanPostProcessor {

        /**
         * bean实例化前调用，如果返回不为空，则不再实例化bean
         * @param beanClass
         * @param beanName
         * @return
         * @throws BeansException
         */
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            System.out.println(beanClass);
            return null;
        }

        /**
         * 如果返回为false，则不会对bean进行属性的设置
         * @param bean
         * @param beanName
         * @return
         * @throws BeansException
         */
        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals(beanName, "person")) {
                Person person = Person.class.cast(bean);
                person.setAge(2);
                return true;
            }
            return true;
        }
    }

//    @Bean
//    public Person person() {
//        return new Person(1);
//    }

//    @Bean
//    public SuperPerson superPerson() {
//        return new SuperPerson();
//    }

}
