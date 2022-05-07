package com.xiao.thread.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author xiao ji hao
 * @create 2022年04月28日 20:56:00
 */
@Slf4j
public class MyThreadPool {

    //存储待执行的线程
    private Queue<Runnable> queue;

    //存储正在工作的线程
    private Set<Worker> workers = new HashSet<>();
    //核心线程大小
    private int coreSize;

    public MyThreadPool(int coreSize, int queueSize) {
        queue = new ArrayBlockingQueue<Runnable>(queueSize);
        this.coreSize = coreSize;
    }

    public void execute(Runnable task) {
        synchronized (workers) {
            if(workers.size() < coreSize) {
                //当核心线程没满，交给work对象执行线程任务
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else {
                queue.add(task);
            }
        }
    }

    class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //当任务不为空，则执行当前任务，任务为空，则从队列获取任务
            while (task != null || (task = queue.poll()) != null ) {
                try {
                    task.run();
                    task = null;
                }catch (Exception e) {

                }
            }
        }
    }

}
