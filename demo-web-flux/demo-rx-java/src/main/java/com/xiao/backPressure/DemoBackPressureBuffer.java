package com.xiao.backPressure;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

/**
 * onBackPressureBuffer操作符会请求无界需求并将返回的元素推送到下游。如果下游消费者无法跟上，那么元素将缓冲在队列中。
 * <br>
 * 如果缓冲满了就抛出异常
 * @author lao xiao
 * @date 2022年09月15日 19:12
 */
public class DemoBackPressureBuffer {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Flux.range(1, 1000)
                .delayElements(Duration.ofMillis(10))
                .onBackpressureBuffer(300)
                .delayElements(Duration.ofMillis(100))
                .subscribe(System.out::println, err -> {
                    System.out.println(err);
                    countDownLatch.countDown();
                }, () -> countDownLatch.countDown());
        countDownLatch.await();

    }

}
