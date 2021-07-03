package xiao.annotation;


import org.springframework.context.annotation.Import;
import xiao.bootstrap.ServerBeanDefinition;
import xiao.server.Server;

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
