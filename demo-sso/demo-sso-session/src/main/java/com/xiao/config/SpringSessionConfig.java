package com.xiao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @author xiao ji hao
 * @create 2022年01月08日 17:32:00
 */
@Configuration
public class SpringSessionConfig {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("LAOXIAOJSESSION");
        serializer.setDomainNamePattern(".laoxiao.com");
        return serializer;
    }

}
