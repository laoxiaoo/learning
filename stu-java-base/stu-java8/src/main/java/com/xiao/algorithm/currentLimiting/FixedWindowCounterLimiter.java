package com.xiao.algorithm.currentLimiting;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定窗口计数器
 *
 *
 * @author lao xiao
 * @date 2023-06-28 16:50
 */
public class FixedWindowCounterLimiter {

    /**
     * 时间窗口的大小，表示这，这个时间（秒）内，能够放行N流量
     */
    private int windowsSize;

    /**
     * 阈值: 单位时间内，最多能放行的流量
     */
    private int limit;

    /**
     * 计数器
     */
    private AtomicInteger count;

    public FixedWindowCounterLimiter(int windowsSize, int limit) {
        this.windowsSize = windowsSize;
        this.limit = limit;
        count = new AtomicInteger(0);
        //启动后，一个线程一直去在windowsSize时间段内，清空count
        new Thread(() -> {
            while (true) {
                ThreadUtil.sleep(windowsSize*1000);
                count.set(0);
            }
        }).start();
    }

    /**
     * 尝试请求
     * @return
     */
    public Boolean tryRequest() {
        int i = count.incrementAndGet();
        if(i>limit) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public static void main(String[] args) {
        // 限流配置, 2s内只允许通过5个
        FixedWindowCounterLimiter rateLimiter = new FixedWindowCounterLimiter(2, 5);

        // 请求总数
        int allNum = 13;
        // 通过数
        int passNum = 0;
        // 被限流数
        int blockNum = 0;
        //模拟连续请求
        for(int i=0; i<allNum; i++){
            if(rateLimiter.tryRequest()){
                passNum++;
            }else{
                blockNum++;
            }
        }
        System.out.println("请求总数: "+allNum+", 通过数: "+passNum+", 被限流数: "+blockNum);
    }


}
