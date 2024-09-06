package com.xiao.date;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @author lao xiao
 * @date 2024-07-29 11:00
 */
@Slf4j
public class InstantStu {

    public static void main(String[] args) throws InterruptedException {
        stuInstant();
        stuPre();
    }

    /**
     * 计算执行时间
     * @throws InterruptedException
     */
    private static void stuInstant() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000);
        Instant end = Instant.now();

        log.info("use time {}", Duration.between(start, end).toMillis());
    }

    private static void stuPre() {
        LocalDate start = LocalDate.of(2023,5, 6);
        LocalDate end = LocalDate.of(2024,6, 6);
        Period between = Period.between(start, end);
        log.info("相隔{}月", between.getMonths());

        long daysBetween = ChronoUnit.DAYS.between(start, end);
        System.out.println("两个日期之间的天数差为: " + daysBetween);

        long monthBt = ChronoUnit.MONTHS.between(start, end);
        System.out.println("两个日期之间的天数差为: " + monthBt);
    }

}
