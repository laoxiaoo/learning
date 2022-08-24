package com.xiao.projectreactor;

import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Mono提供类似的工厂方法，但主要针对单个元素。它也经常与nullable类型和Optional类型一起使用
 *
 * @author lao xiao
 * @create 2022年 08月 11日 19:21
 */
public class DemoMono {

    public static void main(String[] args) {
        Mono<String> just = Mono.just("laoxiao");
        just.subscribe(System.out::println);

        //避免空指针异常，返回不包含任何值的optiona1对象。
        Mono<Object> empty = Mono.justOrEmpty(Optional.empty());
        empty.subscribe(System.out::println);
    }

}
