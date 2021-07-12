package com.xiao.thread.lock;

/**
 * @author xiao ji hao
 * @create 2021年06月20日 17:52:00
 */
public class TestWait {

    static Object LOCK = new Object();

    public static void main(String[] args) throws Exception {
        synchronized (LOCK) {
            //当前线程必须要获得了这个锁才能调用
            LOCK.wait();
        }
    }

}
