package com.xiao.in.spring.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName LookupEnvironmentDemo.java
 * @Description
 * @createTime 2021年02月22日 21:01:00
 */
public class LookupEnvironmentDemo {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LookupEnvironmentDemo.class);
        applicationContext.refresh();
        Environment environmentTmp = applicationContext.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);
        System.out.println(environmentTmp == applicationContext.getBean(LookupEnvironmentDemo.class).environment);
        applicationContext.close();
    }

}
