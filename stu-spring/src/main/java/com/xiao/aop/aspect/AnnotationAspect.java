package com.xiao.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xiao ji hao
 * @create 2021年06月29日 16:16:00
 */
@Component
@Aspect
public class AnnotationAspect {

    @Pointcut("execution(public void com.xiao.aop.service.AopService.*(..)) ")
    public void pointCut(){
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("==> before....");
    }

}
