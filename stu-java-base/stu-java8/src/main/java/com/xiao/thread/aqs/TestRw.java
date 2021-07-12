package com.xiao.thread.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.xiao.thread.lock.TestPark.sleep;

/**
 * 测试读写锁
 *
 * @author xiao ji hao
 * @create 2021年06月23日 19:14:00
 */
@Slf4j
public class TestRw {

    public static void main(String[] args) {
//        TestStampedLock.Container container = new TestStampedLock.Container();
//        new Thread(() -> {
//            container.read();
//        }).start();
//        new Thread(() -> {
//            container.read();
//        }).start();
        int n = 10;
        log.debug("取余运算: {}, {}", n-1 & 2, 2%n);
    }

    static class Container {
        ReentrantReadWriteLock rw =  new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock r = rw.readLock();
        ReentrantReadWriteLock.WriteLock w = rw.writeLock();
        public void read() {
            r.lock();
            try {
                log.debug("开始读.....");
                sleep(1000);
            } finally {
                r.unlock();
            }
        }

        public void writer() {
            w.lock();
            try {
                log.debug("开始写.....");
                sleep(1000);
            } finally {
                w.unlock();
            }
        }
    }

}
