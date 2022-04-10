package com.xiao.base;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过标准工厂的方式的方式生产代理对象
 * <br>
 * 当执行对应的代理方法时，会进过before方法
 * @author xiao ji hao
 * @create 2022年02月09日 23:04:00
 */
public class AspectFactoryDemo {

    public static void main(String[] args) {
        //被代理对象
        Map<String, String> map = new HashMap<>();
        AspectJProxyFactory aspect = new AspectJProxyFactory(map);
        aspect.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("执行方法前 " + method.getName());
            }
        });
        aspect.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                System.out.println("执行方法后 " + method.getName());
            }
        });
        //获取代理对象
        Map<String, String> proxy = aspect.getProxy();
        proxy.put("laoxiao", "laoxiao");
    }

}
