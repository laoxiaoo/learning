package com.xiao.backPressure;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

/**
 * 放弃后面进来的数据
 * @author lao xiao
 * @date 2022年09月15日 19:28
 */
public class DemoBackpressureDrop {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Flux.range(1, 1000)
                .delayElements(Duration.ofMillis(10))
                .onBackpressureDrop()
                .delayElements(Duration.ofMillis(100))
                .subscribe(System.out::println, err -> {
                    System.out.println(err);
                    countDownLatch.countDown();
                }, () -> countDownLatch.countDown());
        countDownLatch.await();

    }

}
