package com.xiao.in.spring.resource;

import com.xiao.pojo.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName AnnotaedResourceDemo.java
 * @Description
 * @createTime 2021年02月18日 16:20:00
 */
public class InjectResourceDemo {

    @Value("classpath:/application.properties")
    private Resource resource;

    @Value("classpath*:/META-INF/spring.*")
    private Resource[] resources;

    @PostConstruct
    public void init() {
        System.out.println(resource.getFilename());
        System.out.println("=========>PostConstruct");
        Arrays.stream(resources).map(Resource::getFilename).forEach(System.out::println);
     }

    @Bean(initMethod = "initMethod")
    public Person person() {
        return new Person();
    }



    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectResourceDemo.class);
        applicationContext.refresh();
        applicationContext.close();
    }
}
