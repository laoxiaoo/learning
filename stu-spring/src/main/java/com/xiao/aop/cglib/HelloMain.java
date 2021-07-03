package com.xiao.aop.cglib;

import org.junit.Test;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Malone Xiao
 * @create 2021年06月11日 23:35:00
 */
public class HelloMain {

    @Test
    public void testStaticProxy() {
        ProxyHelloService proxy = new ProxyHelloService(new DefaultHelloService());
        proxy.sayHello("老肖");
    }

    @Test
    public void testJdkProxy() {
        Object object = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{HelloService.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        DefaultHelloService defaultHelloService = new DefaultHelloService();
                        defaultHelloService.sayHello(args[0] + " 在代理层做了些不可描述的事情");
                        return null;
                    }
                });
        HelloService helloService = (HelloService) object;
        helloService.sayHello("某人");
    }

    @Test
    public void testCglibProxy() {
        Enhancer enhancer = new Enhancer();
        //设置需要增强的类
        enhancer.setSuperclass(DefaultHelloService.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object source, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("执行前");
                Object result = methodProxy.invokeSuper(source, args);
                System.out.println("执行后");
                return result;
            }
        });
        HelloService helloService = (HelloService) enhancer.create();
        helloService.sayHello("老肖");
    }

    @Test
    public void testMethod() throws Exception {
        Class<?> loadClass = Thread.currentThread()
                .getContextClassLoader()
                .loadClass("com.xiao.aop.cglib.HelloService");
        Method method = ReflectionUtils.findMethod(loadClass, "sayHello", String.class);
        System.out.println("method:"+method);
    }

    @Test
    public void testMethodFilter() throws Exception {
        Class<?> loadClass = Thread.currentThread()
                .getContextClassLoader()
                .loadClass("com.xiao.aop.cglib.HelloService");
        ReflectionUtils.doWithMethods(loadClass, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                //扫描到方法调用
                System.out.println("Method:"+method);
            }
        }, new ReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {
                //如果返回false，则不调用方法
                if(method.getName().equals("test")) {
                    return false;
                }
                return true;
            }
        });
    }

    @Test
    public void testAddField() throws Exception {
        BeanGenerator generator = new BeanGenerator();
        generator.setSuperclass(Person.class);
        generator.addProperty("str", String.class);
        Object o = generator.create();
//        ReflectionUtils.doWithMethods(o.getClass(),method -> {
//            System.out.println(method);
//        });
        Method setStr = ReflectionUtils.findMethod(o.getClass(), "setStr", String.class);
        ReflectionUtils.invokeMethod(setStr, o, "laoxiao");
        System.out.println(ReflectionUtils.invokeMethod(ReflectionUtils.findMethod(o.getClass(), "getStr"), o));
    }

}
