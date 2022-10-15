package com.xiao.controller;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author lao xiao
 * @date 2022年09月29日 15:49
 */
@RestController
@RequestMapping("/user")
public class MyRestController {


    @GetMapping("/getUser/{id}")
    public Mono<User> getUser(@PathVariable("id") Integer id) {
        return Mono.just(new User(1, "laoxiao"));
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private Integer id;

        private String name;
    }
}
