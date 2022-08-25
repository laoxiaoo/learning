package com.xiao.error;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;

/**
 * 为subscribe操作符中的 onError 信号定义处理程序。
 *
 * @author lao xiao
 * @create 2022年 08月 25日 19:35
 */
public class DemoDeal {

    public static void main(String[] args) {
        Flux.from(new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> subscriber) {
                subscriber.onError(new RuntimeException("出现异常"));
            }
        }).subscribe(var -> System.out.println(var) //处理正常情况
                , err -> System.err.println(err)); //处理异常情况
    }

}
