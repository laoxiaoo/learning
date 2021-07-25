package com.xiao.aop.factory;

import org.springframework.aop.framework.ProxyFactory;

/**
 *
 * 实现目标方法的拦截
 *
 * @author xiao ji hao
 */
public class ProxyFactoryTest {

    public static void main(String[] args) {
        interfaceProxy();
    }

    /**
     * 代理对象未指定接口，使用CGLIB生成代理类
     */
    public static void classProxy() {
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(new MyTarget());
        factory.addAdvice(new AroundInterceptor());
        MyTarget targetProxy = (MyTarget) factory.getProxy();
        targetProxy.sayHello();
    }

    public static void interfaceProxy() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(new Class[] {TargetService.class});
        proxyFactory.setTarget(new TargetServiceImpl());
        proxyFactory.addAdvice(new AroundInterceptor());
        TargetService targetService = (TargetService)proxyFactory.getProxy();
        targetService.sayHello();
        System.out.printf(targetService.getClass().toString());
    }
}
