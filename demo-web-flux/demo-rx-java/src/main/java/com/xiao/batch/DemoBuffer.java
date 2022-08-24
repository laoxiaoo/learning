package com.xiao.batch;

import reactor.core.publisher.Flux;

/**
 * 100个元素，通过缓冲的操作，每5个元素执行一次
 *  <br>
 *  打印
 *  <br>
 *  [1, 2, 3, 4, 5]
 * [6, 7, 8, 9, 10]
 * [11, 12, 13, 14, 15]
 * [16, 17, 18, 19, 20]...
 * <br>
 * 什么时候采用批处理：如数据库操作，集合里一批一批的保存
 * @author lao xiao
 * @create 2022年 08月 24日 8:43
 */
public class DemoBuffer {

    public static void main(String[] args) {
        Flux.range(1, 100)
                .buffer(5).subscribe(System.out::println);
    }

}
