package com.xiao.annotation;

import com.xiao.bootstrap.ServerBeanDefinition;
import com.xiao.bootstrap.ServerSelector;
import com.xiao.server.Server;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author 肖杰
 * @version 1.
 * @ClassName EnableServer.java
 * @Description TODO
 * @createTime 2021年03月22日 09:10:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import(ServerBeanDefinition.class)
public @interface EnableServer {

    Server.Type type();
}
