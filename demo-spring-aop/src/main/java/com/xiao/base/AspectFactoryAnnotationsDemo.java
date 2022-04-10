package com.xiao.base;

import com.xiao.pointcut.AspectConfiguration;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * AspectFactory读取注解的方式，进行方法的拦截
 *
 * @author xiao ji hao
 * @create 2022年04月10日 16:01:00
 */
public class AspectFactoryAnnotationsDemo {

    public static void main(String[] args) {
        //被代理对象
        Map<String, String> map = new HashMap<>();
        AspectJProxyFactory aspect = new AspectJProxyFactory(map);
        //添加配置类
        aspect.addAspect(AspectConfiguration.class);
        //获取代理对象
        Map<String, String> proxy = aspect.getProxy();
        //我们在执行代理对象的方法时候，能够执行AspectConfiguration配置的类
        proxy.put("laoxiao", "laoxiao");
    }

}
