package com.xiao.in.spring.dependency.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName GroupBean.java
 * @Description
 * @createTime 2021年02月09日 16:56:00
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface GroupBean {
}
