package com.xiao.annotation;

import com.xiao.HelloWordConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author 肖杰
 * @version 1.
 * @ClassName EnableHelloword.java
 * @Description TODO
 * @createTime 2021年03月21日 18:49:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(HelloWordConfig.class)
public @interface EnableHelloWord {

}
