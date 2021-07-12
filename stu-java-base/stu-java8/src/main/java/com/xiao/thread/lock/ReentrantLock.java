package com.xiao.thread.lock;

/**
 * 重入锁
 *
 * @author xiao ji hao
 * @create 2021年06月20日 16:21:00
 */
public class ReentrantLock {
    static Object LOCK = new Object();
    public void method1() {
        synchronized (LOCK) {
            method2();
        }
    }

    public void method2() {
        synchronized (LOCK) {

        }
    }

}
