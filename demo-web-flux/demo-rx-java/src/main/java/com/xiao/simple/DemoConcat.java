package com.xiao.simple;

import io.reactivex.rxjava3.core.Observable;

/**
 * 合并流
 * @author lao xiao
 * @create 2022年 08月 08日 19:54
 */
public class DemoConcat {

    public static void main(String[] args) {
        Observable<Integer> concat = Observable.concat(Observable.just(1, 2, 3, 4, 5, 6), Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6}));


        concat.subscribe(var ->{
            System.out.println("成功..."+var);
        }, var -> System.out.println("结束："+var), () -> System.out.println("结束"));
    }

}
