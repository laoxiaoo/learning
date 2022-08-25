package com.xiao.error;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;

/**
 * 一旦流中出现onError，则使用指定的元素替代
 * @author lao xiao
 * @create 2022年 08月 25日 19:50
 */
public class DemoReturn {

    public static void main(String[] args) throws InterruptedException {
        Flux.just("user")
                .flatMap(var -> Flux.error(new RuntimeException("error")))
                .onErrorReturn("新的正常的流数据")
                .subscribe(var -> System.out.println(var) //处理正常情况
                        , err -> System.err.println(err) //处理异常情况
                        , () -> System.out.println("完成"));
        Thread.sleep(10000);
    }

}
