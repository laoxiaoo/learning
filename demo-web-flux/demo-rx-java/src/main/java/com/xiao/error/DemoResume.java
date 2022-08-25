package com.xiao.error;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;

/**
 * 如果发送异常流，则用新的流来替换
 *
 * @author lao xiao
 * @create 2022年 08月 25日 19:43
 */
public class DemoResume {

    public static void main(String[] args) throws InterruptedException {
        Flux.from(new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> subscriber) {
                System.out.println("异常流");
                subscriber.onError(new RuntimeException("出现异常"));
            }
        }).onErrorResume(event -> Flux.from(subscriber -> subscriber.onNext("新的正常的流数据")))
                .subscribe(var -> System.out.println(var) //处理正常情况
                , err -> System.err.println(err) //处理异常情况
                        , () -> System.out.println("完成"));
        Thread.sleep(10000);
    }

}
