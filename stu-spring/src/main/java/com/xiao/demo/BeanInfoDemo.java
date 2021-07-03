package com.xiao.demo;

import com.xiao.aop.cglib.Person;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Arrays;

/**
 *
 *  {@link com.xiao.aop.cglib.Person } beanInfo示例
 * @author lonely xiao
 * @version 1.0
 * @ClassName BeanInfoDemo.java
 * @Description
 * @createTime 2021年01月27日 00:01:00
 */
public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        Arrays.stream(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            System.out.println(propertyDescriptor.toString());
        });
    }
}
