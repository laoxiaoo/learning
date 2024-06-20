package com.xiao.date;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * LocalDateTime通用工具类
 * @author
 */
public class LocalDateTimeUtil {

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

    public static List<LocalDateTime> getBetweenMounth(LocalDateTime start, LocalDateTime end) {
        if(start.isAfter(end)) {
            throw new IllegalArgumentException("时间输入错误");
        }
        List<LocalDateTime> list = new ArrayList<>();
        LocalDateTime tmp = start;
        while (true) {
            tmp = tmp.plusDays(1);
            if(tmp.isAfter(end)){
                break;
            }
            list.add(tmp);
        }
        return list;
    }

    /**
     * 获取相差的天数
     * @param start
     * @param end
     * @return
     */
    public static Long getLegthDays(LocalDateTime start, LocalDateTime end) {
        Duration between = Duration.between(start, end);
        return between.toDays();
    }


    public static Integer getMonthInt(LocalDateTime time) {
        return Integer.valueOf(time.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

}
