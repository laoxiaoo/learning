package com.xiao.thread.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.xiao.thread.lock.TestPark.sleep;

/**
 * @author xiao ji hao
 * @create 2021年06月23日 16:20:00
 */
@Slf4j
public class TestAQS {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            log.debug("==>线程执行中....");
            sleep(2000);
            lock.unlock();
        }).start();
        new Thread(() -> {
            log.debug("==>线程2准备执行....");
            lock.lock();
            log.debug("==>线程执行中....");
            lock.unlock();
            log.debug("==>解锁成功");
        }).start();
    }

    static class MyLock implements Lock {
        class MySync extends AbstractQueuedSynchronizer {
            @Override
            protected boolean tryAcquire(int arg) {
                //尝试加锁，
                if(compareAndSetState(0, arg)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
                return false;
            }

            @Override
            protected boolean tryRelease(int arg) {
                //尝试解锁
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }

            @Override
            protected boolean isHeldExclusively() {
                //是否持有独占锁
                return getState()==1;
            }

            public Condition newCondition() {
                return new ConditionObject();
            }
        }

        MySync mySync = new MySync();
        @Override
        public void lock() {
            mySync.acquire(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            mySync.acquireInterruptibly(1);
        }

        @Override
        public boolean tryLock() {
            return mySync.tryAcquire(1);
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return mySync.tryAcquireNanos(1, unit.toNanos(time));
        }

        @Override
        public void unlock() {
            mySync.release(1);
        }

        @Override
        public Condition newCondition() {
            return mySync.newCondition();
        }
    }
}
