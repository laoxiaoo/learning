package com.xiao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author lao xiao
 * @version 1.
 * @ClassName StuSpringBootApplication.java
 * @Description TODO
 * @createTime 2021年03月18日 08:51:00
 */
@SpringBootApplication
@Slf4j
public class StuSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StuSpringBootApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        log.info("环境：{}", environment.getActiveProfiles());
        //配置在 yml 里的信息
        log.info("环境信息: {}", environment.getProperty("laoxiao.test"));
        //系统环境变量，如设置的JAVA_HOME
        log.info("SystemEnvironment: {}", environment.getSystemEnvironment());
        //应用的参数，java -jar -Dtest.pr=laoxiao demo.jar设置的参数,这里取得值：laoxiao
        log.info("SystemProperties: {}", environment.getSystemProperties().get("test.pr"));
    }
}
