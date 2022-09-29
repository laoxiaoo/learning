package com.xiao;

import com.xiao.config.WebFluxConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

/**
 *
 * 基于Reactor Netty实现WebFlux服务   (Standalone服务)
 * @author lao xiao
 * @date 2022年09月29日 15:17
 */
public class SpringWebfluxApplication {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(WebFluxConfig.class);
        //通过ApplicationContext创建HttpHandler
        HttpHandler httpHandler = WebHttpHandlerBuilder.applicationContext(applicationContext).build();
        //内置HttpHandler适配器
        ReactorHttpHandlerAdapter httpHandlerAdapter = new ReactorHttpHandlerAdapter(httpHandler);
        // 创建HttpServer实例，它是ReactorNetty API的一部分
        DisposableServer server = HttpServer.create().host("127.0.0.1").port(8080).handle(httpHandlerAdapter).bindNow();
        // 为了使应用程序保持活动状态，阻塞主Thread 并监听服务器的处理事件
        server.onDispose().block();
    }

}
