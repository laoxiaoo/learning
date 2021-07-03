package xiao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 肖杰
 * @version 1.
 * @ClassName HelloWordConfig.java
 * @Description TODO
 * @createTime 2021年03月21日 18:53:00
 */
@Configuration
public class HelloWordConfig {

    @Bean
    public String helloWord() {
        return "helloWord";
    }
}
