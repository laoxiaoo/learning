package com.xiao.thread.pool;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 测试线程池定时
 *
 * @author xiao ji hao
 * @create 2021年06月23日 11:06:00
 */
@Slf4j
public class TestPoolTimer {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = now.withHour(11).withMinute(19).withSecond(0).with(DayOfWeek.WEDNESDAY);
        if(now.compareTo(time) > 0) {
            //如果当前时间>需要定时的时间，则定时时间延迟一周
            time.plusWeeks(1);
        }
        log.debug("下次执行时间：{}", time);
        long millis = Duration.between(now, time).toMillis();
        //一个星期的时间间隔
        long period = 1000*60*24*7;
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        //执行定时任务，延迟到定时的时间，然后每次执行
        pool.scheduleAtFixedRate(() -> {
            log.debug("执行定时任务...");
        }, millis, period, TimeUnit.MILLISECONDS);
    }

}
