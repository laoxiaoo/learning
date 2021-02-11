package com.xiao.in.spring.dependency.annotation;

import java.lang.annotation.*;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName InjectPerson.java
 * @Description
 * @createTime 2021年02月10日 10:53:00
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectPerson {
}
