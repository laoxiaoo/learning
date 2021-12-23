package com.xiao.aop.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 利用aop实现一个适配器
 * </br>
 * 实现aop前置通知，在方法执行之前进行操作
 * @author xiao ji hao
 * @create 2021年10月21日 08:12:00
 */
@Slf4j
@Component
public class HelloAdapter implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.info("before hello...");
    }
}
