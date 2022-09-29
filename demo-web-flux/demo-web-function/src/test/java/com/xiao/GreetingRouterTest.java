package com.xiao;

import com.xiao.controller.MyRestController;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author lao xiao
 * @date 2022年09月29日 15:56
 */
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingRouterTest {




    @Test
    public void testUser() throws InterruptedException {
        WebClient client = WebClient.create("http://localhost:8080");
        client.get().uri("/user/getUser/{id}", 1).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MyRestController.User.class)
                .subscribe(System.out::println);
        Thread.sleep(10000);
    }
}
