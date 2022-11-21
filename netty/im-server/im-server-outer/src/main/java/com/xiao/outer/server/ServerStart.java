package com.xiao.outer.server;

import com.xiao.inner.server.InnerServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author lao xiao
 * @date 2022年09月30日 16:18
 */
@Slf4j
@SpringBootApplication
public class ServerStart implements ApplicationRunner {

    @Autowired
    private Environment environment;
    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(ServerStart.class)
                .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);
    }


    static void start(Integer port, Integer innerPort) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new ServerInitializer())  //(4)
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
            log.info("WebsocketChatServer 启动了" + port);
            ChannelFuture f = b.bind(port).addListener(var -> {
                startInnerServer(innerPort);
                log.info("启动完成....");
            }).sync();

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            log.info("WebsocketChatServer 关闭了");
        }
    }

    static void startInnerServer(Integer port) {
        InnerServer.start(port);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String port = environment.getProperty("chat.outer.port");
        log.info("获取启动端口：{}", port);
        start(Integer.valueOf(port), Integer.valueOf(environment.getProperty("chat.inner.port")));
    }
}
