package com.xiao;

import com.xiao.manager.DemoManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@EnableAutoConfiguration
//@Slf4j
public class DubboRegistryNacosConsumerBootstrap {


    @DubboReference(version = "${demo.service.version}")
    private DemoManager demoManager;

    public static void main(String[] args) {
        SpringApplication.run(DubboRegistryNacosConsumerBootstrap.class).close();
    }

//    @Bean
//    public ApplicationRunner runner() {
//        return args -> log.info(demoManager.sayHello("mercyblitz"));
//    }
}