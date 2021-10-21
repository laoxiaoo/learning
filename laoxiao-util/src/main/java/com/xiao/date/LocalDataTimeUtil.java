package com.xiao.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 *
 * LocalDateTime通用工具类
 * @author xiao jie
 * @create 2021年08月23日 16:01:00
 */
public class LocalDataTimeUtil {
    /**
     * 时间转为 LocalDateTime
     * @param time
     * @return
     */
    public static LocalDateTime parse(String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(time,df);
    }


    /**
     * 当前是否在时间范围之内
     * @param start
     * @param end
     * @return
     */
    public static Boolean isBetween(LocalDateTime start, LocalDateTime end) {
        LocalDateTime now = LocalDateTime.now();
        return isBetween(start, end, now);
    }


    /**
     * now 是否在时间范围之内
     * @param start
     * @param end
     * @param now
     * @return
     */
    public static Boolean isBetween(LocalDateTime start, LocalDateTime end, LocalDateTime now) {
        if(now.isAfter(start) && now.isBefore(end)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    /**
     * 当前时间是否在本月
     * @return
     */
    public static Boolean isBetweenMonth(LocalDateTime time) {
        LocalDateTime now = LocalDateTime.now();
        return isBetween(beginOfMonth(now), endOfMonth(now), time);
    }


    /**
     * 获取当前时间的开始月份
     * @param now
     * @return
     */
    public static LocalDateTime beginOfMonth(LocalDateTime now) {
        return now.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
    }

    /**
     * 获取一个月的结束时间
     * @param now
     * @return
     */
    public static LocalDateTime endOfMonth(LocalDateTime now) {
        return now.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);
    }
}
