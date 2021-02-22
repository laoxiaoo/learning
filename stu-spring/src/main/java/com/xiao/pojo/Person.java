package com.xiao.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName Person.java
 * @Description
 * @createTime 2021年01月26日 23:59:00
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Person {
    private String name;
    private Integer age;

    public Person(Integer age) {
        this.age = age;
    }
    public Person(String name) {
        this.name = name;
    }
}
