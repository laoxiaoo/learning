package xiao.annotation;

import org.springframework.context.annotation.Import;
import xiao.HelloWordConfig;

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
