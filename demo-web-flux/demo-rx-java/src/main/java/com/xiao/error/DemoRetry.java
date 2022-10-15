package com.xiao.error;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;

/**
 * 重试来获取正常的流，如果重试N次还是不能成功，则采用异常处理
 *
 * @author lao xiao
 * @create 2022年 08月 25日 19:39
 */
public class DemoRetry {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux.from(new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> subscriber) {
                System.out.println("异常流");
                subscriber.onError(new RuntimeException("出现异常"));
            }
        })
                .retry(3) //如果发生异常流，则重试到可以正常，最多重试三次
                .subscribe(var -> {
                    System.out.println(var); //处理正常情况
                    countDownLatch.countDown();
                }
                , err -> {
                    //处理异常情况
                    System.err.println(err);
                    countDownLatch.countDown();
                });

        countDownLatch.await();
    }

}
