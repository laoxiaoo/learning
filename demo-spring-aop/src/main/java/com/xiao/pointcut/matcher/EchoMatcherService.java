package com.xiao.pointcut.matcher;

import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author xiao ji hao
 * @create 2022年02月15日 21:12:00
 */
public class EchoMatcherService extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return true;
    }
}
