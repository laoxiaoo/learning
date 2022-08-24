package com.xiao.sampl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

/**
 * 指定对应的时间进行采样
 *
 * @author lao xiao
 * @create 2022年 08月 24日 19:06
 */
public class DemoSampleTimeout {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Flux.range(1,100).delayElements(Duration.ofMillis(100))
                //每次拉取采样的时间随机产生
                .sampleTimeout(var -> Mono.delay(Duration.ofMillis(random.nextInt(100)+50)))
                .subscribe(System.out::println);
        Thread.sleep(10000);
    }

}
