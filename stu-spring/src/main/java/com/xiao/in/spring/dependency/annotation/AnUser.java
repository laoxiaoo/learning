package com.xiao.in.spring.dependency.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName AnUser.java
 * @Description
 * @createTime 2021年01月27日 23:17:00
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnUser {
}
