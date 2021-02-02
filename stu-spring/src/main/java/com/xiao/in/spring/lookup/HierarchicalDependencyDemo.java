package com.xiao.in.spring.lookup;

import com.xiao.in.spring.dependency.DependencyLookUpConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName HierarchicalDependencyDemo.java
 * @Description 层次性依赖查找
 * @createTime 2021年02月02日 21:37:00
 */
public class HierarchicalDependencyDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(DependencyLookUpConfig.class);

        // 获取HierarchicalBeanFactory
        // HierarchicalBeanFactory <-- ConfigurableBeanFactory <-- ConfigurableListableBeanFactory
        //所以此处我们只需要获取ConfigurableListableBeanFactory即可
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("parent bean factory: "+ beanFactory.getParentBeanFactory());
        //configurable代表可修改的，这里我们去修改parent BeanFactory
        beanFactory.setParentBeanFactory(getBeanFactory());
        System.out.println("parent bean factory: "+ beanFactory.getParentBeanFactory());
    }

    public static BeanFactory getBeanFactory() {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(DependencyLookUpConfig.class);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        return beanFactory;
    }

}
