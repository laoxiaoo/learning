package com.xiao.simple;

import io.reactivex.rxjava3.core.Observable;

/**
 * from 的方式创建响应式流
 * @author lao xiao
 * @create 2022年 08月 08日 19:51
 */
public class DemoFrom {

    public static void main(String[] args) {
        Observable<Integer> fromArray = Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6});

        fromArray.subscribe(var ->{
            System.out.println("成功..."+var);
        }, var -> System.out.println("结束："+var), () -> System.out.println("结束"));

    }

}
