package com.xiao.base;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 使用aspect注解的方式
 * @author xiao ji hao
 * @create 2022年02月08日 20:22:00
 */
@Aspect//声明本类为Aspect切面
@Configuration
@EnableAspectJAutoProxy//激活 aspect 注解自动代理
public class AspectJDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJDemo.class);
        context.refresh();
        AspectJDemo bean = context.getBean(AspectJDemo.class);
        //此时获取的Aspect的对象已经被cglib提升
        System.out.println(bean);
        context.close();
    }
}
