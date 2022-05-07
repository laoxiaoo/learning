package com.xiao.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author xiao ji hao
 * @create 2022年02月13日 21:58:00
 */
@Aspect//声明本类为Aspect切面
@Configuration
@EnableAspectJAutoProxy//激活 aspect 注解自动代理
public class AspectJDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(com.xiao.base.AspectJDemo.class, AspectConfiguration.class);
        context.refresh();
        com.xiao.base.AspectJDemo bean = context.getBean(com.xiao.base.AspectJDemo.class);
        //此时获取的Aspect的对象已经被cglib提升
        System.out.println(bean);
        context.close();
    }
}