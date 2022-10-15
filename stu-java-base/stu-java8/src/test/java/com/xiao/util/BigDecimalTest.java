package com.xiao.util;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author lao xiao
 * @create 2022年07月09日 19:42:00
 */
public class BigDecimalTest {

    @Test
    public void test1() {
        //============= 保留两位小数
        // 0.2
        System.out.println(new BigDecimal(0.2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        // 0.23
        System.out.println(new BigDecimal(0.235).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        // 0.24
        System.out.println(new BigDecimal(0.2351).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        // 42.0
        System.out.println(new BigDecimal(42).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    @Test
    public void test2() {
        // 保留两位小数，个位无数字填充0
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        // 0.2
        System.out.println(numberFormat.format(0.2));
        // 0.23
        System.out.println(numberFormat.format(0.235));
        // 0.24
        System.out.println(numberFormat.format(0.2351));
        // 42
        System.out.println(numberFormat.format(42));
    }

    @Test
    public void test3() {
        // 保留两位小数，对应位上无数字填充0
        DecimalFormat df = new DecimalFormat("#0.00");
        // 0.20
        System.out.println(df.format(0.2));
        // 0.23
        System.out.println(df.format(0.235));
        // 0.2, 因为0.2351在0.23-0.24之间，距离0.24更近，所以输出0.24
        System.out.println(df.format(0.2351));
        // 42.00
        System.out.println(df.format(42));
    }

    @Test
    public void test4() {
        DecimalFormat df4 = new DecimalFormat();
        // #：位置上无数字不显示
        df4.applyPattern("#.##");
        System.out.println(df4.format(345235.0));// 345235
        // 0:位置上无数字显示0
        df4.applyPattern("0.00");
        System.out.println(df4.format(345235.0));// 345235.00
        // 加负数显示
        df4.applyPattern("-0.00");
        System.out.println(df4.format(345235.34567));// -345235.35
        // 逗号分隔
        df4.applyPattern("-0,000.00");
        System.out.println(df4.format(345235.34567));// -345,235.35
        // 百分位
        df4.applyPattern("0.00%");
        System.out.println(df4.format(0.34567));// 34.57%
        // 千分位
        df4.applyPattern("0.00\u2030");
        System.out.println(df4.format(0.34567));// 345.67‰
        // 科学计数法,E之前是底数的格式，E之后的是指数的格式
        df4.applyPattern("0.00E00");
        System.out.println(df4.format(2342.444));// 2.34E03
        // 格式后面加单位符号
        df4.applyPattern("0.00 KG");
        System.out.println(df4.format(2342.444));// 2342.44 KG
        df4.applyPattern("0.00 QA");
        System.out.println(df4.format(2342.444));// 2342.44 QA
        // 使用舍入模式：ROUND_HALF_EVEN，
        // 保留位数是奇数，使用ROUND_HALF_DOWN
        // 保留位数是偶数，使用ROUND_HALF_UP
        System.out.println(df4.format(2342.435));// 2342.43 QA
        System.out.println(df4.format(2342.445));// 2342.45 QA

        // String.format
        // 保留两位小数，个位数及小数点后两位无数字填充0，四舍五入
        System.out.println(String.format("%.2f", 0.2));// 0.20
        System.out.println(String.format("%.2f", 0.235));// 0.24
        System.out.println(String.format("%.2f", 0.236));// 0.24
        System.out.println(String.format("%.2f", 42.0));// 42.00
    }


    /**
     * 相加
     */
    @Test
    public void add() {
        BigDecimal b1 = new BigDecimal(1);
        BigDecimal b2 = new BigDecimal(1);
        System.out.println(b1.add(b2));
    }

    /**
     * 相减
     */
    @Test
    public void subtract() {
        BigDecimal b1 = new BigDecimal(1);
        BigDecimal b2 = new BigDecimal(2);
        System.out.println(b1.subtract(b2));
    }

}
