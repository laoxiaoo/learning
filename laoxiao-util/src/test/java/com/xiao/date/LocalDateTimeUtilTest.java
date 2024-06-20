package com.xiao.date;

import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author lao xiao
 * @date 2024-06-20 17:02
 */
public class LocalDateTimeUtilTest {

    @Test
    public void t1() {
        Integer monthInt = LocalDateTimeUtil.getMonthInt(LocalDateTime.now());
        System.out.println(monthInt);
    }
}
