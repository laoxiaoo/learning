package com.xiao.pojo;

import lombok.Getter;
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
public class Person implements InitializingBean {
    private String name;
    private Integer age;

    @PostConstruct
    public void postInit() {
        System.out.println("==> PostConstruct init");
    }

    public void initMethod() {
        System.out.println("==> init method");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("==> afterPropertiesSet init");
    }
}
