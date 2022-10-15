package com.xiao.in.spring.validated;

import com.xiao.pojo.Person;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;

/**
 * @author lao xiao
 * @create 2022年 08月 09日 17:16
 */
public class DataBinderDemo {

    public static void main(String[] args) {
        Person person = new Person();
        DataBinder binder = new DataBinder(person);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name", "laoxiao");
        propertyValues.add("age","18");

        binder.bind(propertyValues);
        System.out.println(person);
    }

}
