package com.xiao.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xiao ji hao
 * @create 2021年06月29日 16:16:00
 */
@Component
@Aspect
public class AnnotationAspect {

    @Pointcut(value = "")
    public void point() {

    }

}
