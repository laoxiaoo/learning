package com.xiao.in.spring.circulation;

import com.xiao.in.spring.dependency.AnnotationDependencyResolveDemo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * 循环依赖问题
 *
 * @author xiao ji hao
 * @create 2021年06月29日 17:07:00
 */
public class CirculationDependency {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);

        context.refresh();
        A a = context.getBean(A.class);
        System.out.println(a);
        context.close();
    }

}
