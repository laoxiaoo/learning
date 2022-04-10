package com.xiao.pointcut.matcher;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author xiao ji hao
 * @create 2022年02月15日 22:44:00
 */
public class EchoMethodServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("拦截到方法：" + invocation.getMethod());
        return invocation.proceed();
    }
}
