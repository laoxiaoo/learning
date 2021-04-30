package com.xiao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName OAuth2ServerApplication.java
 * @Description
 * @createTime 2021年01月18日 23:05:00
 */
@SpringBootApplication
@Slf4j
public class OAuth2ServerApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(OAuth2ServerApplication.class, args);
        Environment env = context.getEnvironment();
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
