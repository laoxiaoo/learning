package com.xiao.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author xiao ji hao
 * @create 2021年06月29日 16:16:00
 */
@Component
@Aspect
@Slf4j
public class AnnotationAspect {

    @Pointcut("execution(public void com.xiao.aop.service.AopService.*(..)) ")
    public void pointCut(){
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        log.debug("==> before....");
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        log.debug("==> after....");
    }

    @AfterReturning(pointcut = "pointCut()", returning = "ret")
    public void returning(JoinPoint joinPoint, Object ret){
        log.debug("==> retuning");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("=> around");
        Object proceed = joinPoint.proceed();
        
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void throwing(JoinPoint joinPoint, Exception exception) {
        log.debug("==> throwing");
    }
}

