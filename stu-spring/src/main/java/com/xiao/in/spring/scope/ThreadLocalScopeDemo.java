package com.xiao.in.spring.scope;

import com.xiao.pojo.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ThreadLocalScopeDemo.java
 * @Description
 * @createTime 2021年02月11日 23:17:00
 */
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public Person person() {
        return new Person(String.valueOf(Thread.currentThread().getId()));
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        applicationContext.refresh();
        for(int i=0; i<3; i++) {
            new Thread(() -> {
                Person bean = applicationContext.getBean(Person.class);
                System.out.println(bean);
                Person bean1 = applicationContext.getBean(Person.class);
                System.out.println(bean1);
            }).start();
        }
        Thread.sleep(Integer.MAX_VALUE);
        applicationContext.close();
    }

}
