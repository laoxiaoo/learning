package com.xiao.sampl;

import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * 元素采样
 * 每隔100毫秒，从流中取出一个元素
 * @author lao xiao
 * @create 2022年 08月 24日 18:59
 */
public class DemoSample {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1,100)
                //延迟流的速度
                .delayElements(Duration.ofMillis(10))
                .sample(Duration.ofMillis(100))
                .subscribe(System.out::println);

        Thread.sleep(10000);
    }

}
