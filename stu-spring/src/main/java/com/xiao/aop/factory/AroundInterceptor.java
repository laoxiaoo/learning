package com.xiao.aop.factory;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * 实现一个拦截类，进行方法的拦截调用
 *
 * @author xiao ji hao
 */
@Slf4j
public class AroundInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.debug("==> 调用之前");
        invocation.proceed();
        log.debug("==> 调用之后");
        return null;
    }
}
