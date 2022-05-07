package com.xiao.pointcut;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 配置Aspect pointcut
 *
 * @author xiao ji hao
 * @create 2022年02月13日 21:55:00
 */
@Aspect
public class AspectConfiguration {
    /**
     * 定义一个point cut 可以拦截public 任何方法
     */
    @Pointcut("execution(public * *(..))")
    private void anyPublicMethod() {
    }

    /**
     * 定义拦截前的方法执行
     */
    @Before("anyPublicMethod()")
    public void beforeMethod() {
        System.out.println("before method");
    }

    @AfterReturning("anyPublicMethod()")
    public void afterReturningMethod() {
        System.out.println("afterReturning method");
    }
}
