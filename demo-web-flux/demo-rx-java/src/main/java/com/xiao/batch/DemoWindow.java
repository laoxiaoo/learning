package com.xiao.batch;

import reactor.core.publisher.Flux;

/**
 * 根据流里面的数据，如果%3=0，则进行合并
 * <br>
 * 然后产生子流
 *<br>
 * 打印结果：<br> [1, 2, 3]
 * [4, 5, 6]
 * [7, 8, 9]...
 * @author lao xiao
 * @create 2022年 08月 24日 8:50
 */
public class DemoWindow {

    public static void main(String[] args) {
        Flux.range(1,100)
                .windowUntil(var -> var%3==0)
                .subscribe(childFlux -> childFlux.collectList().subscribe(System.out::println));
    }

}
