package com.xiao.thread.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static com.xiao.thread.lock.TestPark.sleep;

/**
 * @author xiao ji hao
 * @create 2021年06月23日 15:20:00
 */
@Slf4j
public class TestFork {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(3);
        Integer i = pool.invoke(new MyTask(5));
        log.debug("计算结果：{}", i);
    }

    static class MyTask extends RecursiveTask<Integer> {
        int i;

        public MyTask(int i) {
            this.i = i;
        }
        @Override
        protected Integer compute() {
            log.debug("开始计算： {}", i);
            if(i==1) {
                return i;
            }
            sleep(1000);
            MyTask task = new MyTask(i - 1);
            ForkJoinTask<Integer> fork = task.fork();
            int iTmp = this.i + fork.join();
            return iTmp;
        }
    }

}
