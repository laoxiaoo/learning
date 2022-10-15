package com.xiao.simple;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;

/**
 * 创建一个响应式的流
 *
 * 但调用onNext发送数据
 *
 * subscribe接收数据
 * @author lao xiao
 * @create 2022年 08月 08日 19:25
 */
public class DemoCreate {

    public static void main(String[] args) {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                for(int i=0; i<10; i++) {
                    emitter.onNext("发送数据："+ i);
                }
                emitter.onComplete();
            }
        });

        observable.subscribe(var ->{
            System.out.println("成功..."+var);
        }, var -> System.out.println("结束："+var), () -> System.out.println("结束"));
    }

}
