package com.xiao.projectreactor;

import reactor.core.publisher.Flux;

/**
 * @author lao xiao
 * @create 2022年 08月 11日 19:17
 */
public class DemoFlux {

    public static void main(String[] args) {
        //创建响应式流
        Flux<String> just = Flux.just("laoxiao", "hello");
        just.subscribe(System.out::println);
        //第一个: 起始数组， 一共生成的数据量
        Flux<Integer> range = Flux.range(100, 5);
        range.subscribe(System.out::println);

    }

}
