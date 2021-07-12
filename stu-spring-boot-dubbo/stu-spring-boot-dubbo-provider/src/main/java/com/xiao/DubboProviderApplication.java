package com.xiao;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * @author malone xiao
 * @create 2021年06月07日 17:47:00
 */
@SpringBootApplication
@Slf4j
@EnableDubbo
public class  DubboProviderApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DubboProviderApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.debug("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port") + "/api/swagger-ui.html",
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port") + "/api/swagger-ui.html",
                env.getActiveProfiles());
    }

}
