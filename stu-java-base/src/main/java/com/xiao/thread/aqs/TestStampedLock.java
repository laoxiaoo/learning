package com.xiao.thread.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.StampedLock;

import static com.xiao.thread.lock.TestPark.sleep;

/**
 * @author xiao ji hao
 * @create 2021年06月23日 21:09:00
 */
@Slf4j
public class TestStampedLock {

    public static class Container {
        StampedLock lock = new StampedLock();

        public String read() {
            //进行乐观读，获取戳
            long stamp = lock.tryOptimisticRead();
            //模拟做了很多不可描述的事情
            //此时可能发生其他锁产生
            sleep(1000);
            //验戳
            if(lock.validate(stamp)) {
                log.debug("==> 中间没有发生其他锁");
                return "直接读出来的";
            }
            //验证失败，此时锁升级成读锁
            try {
                log.debug("==> 开始锁升级");
                stamp = lock.readLock();
                sleep(1000);
                log.debug("==> 读锁后产生的数据");
                return "==> 读锁后产生的数据";
            } finally {
                lock.unlock(stamp);
            }
        }

        public void writer() {
            long stamp = lock.writeLock();
            try {
                sleep(2000);
                log.debug("==> 开始写事件");
            } finally {
                lock.unlock(stamp);
            }
        }
    }
}
