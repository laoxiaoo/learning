package com.xiao.thread.memory;

/**
 * @author xiao ji hao
 * @create 2021年06月21日 20:43:00
 */
public class TestDCL {


}

class SingletonDemo {
    private SingletonDemo singletonDemo;

    private SingletonDemo() {

    }

    private SingletonDemo  getInstance() {
        if(singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                if(singletonDemo == null) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }
}
