package com.xiao.annotation;


import org.springframework.context.annotation.Import;
import com.xiao.bootstrap.ServerBeanDefinition;
import com.xiao.server.Server;

import java.lang.annotation.*;

/**
 * @author lao xiao
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
