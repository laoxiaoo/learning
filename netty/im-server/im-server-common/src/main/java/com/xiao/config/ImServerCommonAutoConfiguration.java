package com.xiao.config;

import com.xiao.util.chat.factory.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author lao xiao
 * @date 2022年11月18日 14:39
 */
@Configuration
public class ImServerCommonAutoConfiguration {

    @Bean(value = "sessionMemory")
    public Session getSessionMemory() {
        return new SessionMemoryImpl();
    }

    @Bean(value = "sessionRedis")
    @Primary
    public Session getSessionRedis(@Qualifier("sessionMemory")Session sessionMemory) {
        SessionRedisImpl session = new SessionRedisImpl();
        session.setSession(sessionMemory);
        return session;
    }

    @Bean
    public SpringFactory springFactory() {
        return new SpringFactory();
    }

}
