package com.xiao.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiao ji hao
 * @create 2021年06月21日 14:33:00
 */
public class TestReentrantLock {

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void method1() {
        lock.lock();
        try {
            method2();
        } finally {
            lock.unlock();
        }
    }

    public static void method2() {
        lock.lock();
        try {

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        method1();
    }

}