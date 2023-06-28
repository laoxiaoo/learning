package com.xiao.algorithm.currentLimiting;

/**
 * 滑动窗口
 *
 * @author lao xiao
 * @date 2023-06-28 17:13
 */
public class SlidingWindowCounterLimiter {

    /**
     * 时间窗口大小
     */
    private int windowsSize;

    /**
     * 阈值
     */
    private int limit;

    /**
     * 子窗口大小
     */
    private int sonWindowsSize;

    /**
     * 最后一次滑动的时间搓
     */
    private Long lastTime;

}
