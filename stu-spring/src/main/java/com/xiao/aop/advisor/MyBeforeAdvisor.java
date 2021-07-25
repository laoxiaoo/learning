package com.xiao.aop.advisor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

public class MyBeforeAdvisor implements PointcutAdvisor {
    @Override
    public Pointcut getPointcut() {
        //自定义切点
        return null;
    }

    @Override
    public Advice getAdvice() {
        //自定义增强器
        return null;
    }

    @Override
    public boolean isPerInstance() {
        return true;
    }
}
