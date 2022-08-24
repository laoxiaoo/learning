package com.xiao.bootstrap;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.xiao.annotation.EnableHelloWord;
import com.xiao.annotation.EnableServer;
import com.xiao.server.Server;

/**
 * @author lao xiao
 * @version 1.
 * @ClassName HelloWordBootStrap.java
 * @Description TODO
 * @createTime 2021年03月21日 18:57:00
 */
@EnableHelloWord
@EnableServer(type = Server.Type.FTP)
public class HelloWordBootStrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HelloWordBootStrap.class);
        applicationContext.refresh();
        //String bean = applicationContext.getBean("helloWord", String.class);
        Server bean = applicationContext.getBean(Server.class);
        //System.out.println(bean);
        bean.start();
        applicationContext.close();
    }
}
