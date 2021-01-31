package com.xiao.in.spring.bean;

import com.xiao.pojo.Person;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName BeanDefinitionCreateDemo.java
 * @Description BeanDefinition 创建demo
 * @createTime 2021年01月30日 11:01:00
 */
public class BeanDefinitionCreateDemo {

    public static void main(String[] args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Person.class);
        //属性设置 第一种方式
        beanDefinitionBuilder.addPropertyValue("name", "张三").addPropertyValue("age", 12);
        //获取实例  beanDefinition不是bean的最终形态，不是生命周期，可以随时修改
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        //通过abstractBeanDefinition 获取beanDefinition
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(Person.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name", "张三").add("age", 12);
        genericBeanDefinition.setPropertyValues(propertyValues);
    }

}
