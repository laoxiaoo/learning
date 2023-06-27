package com.xiao.aop;

import com.xiao.aop.adapter.HelloService;
import com.xiao.aop.adapter.HelloServiceImpl;
import com.xiao.aop.service.AopService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * spring aop测试
 *
 * @author xiao ji hao
 * @create 2021年07月07日 16:12:00
 */
@ComponentScan(basePackages = "com.xiao.aop")
@EnableAspectJAutoProxy
public class LoaderAopBean {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LoaderAopBean.class);
        context.refresh();
        AopService aopService = context.getBean(AopService.class);

        aopService.sayHello();
        HelloService bean = (HelloService) context.getBean("proxyBean");
        bean.sayHello();
        context.close();
    }
}
