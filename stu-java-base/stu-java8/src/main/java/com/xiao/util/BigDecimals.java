package com.xiao.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal示例与工具类
 *
 *
 * @author lao xiao
 * @create 2022年07月09日 19:41:00
 */
public class BigDecimals {
    public static BigDecimal doubleAdd(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal floatAdd(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal doubleSub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal floatSub(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal doubleMul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal floatMul(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal doubleDiv(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        // 保留小数点后两位 ROUND_HALF_UP = 四舍五入
        return b1.divide(b2, 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal floatDiv(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        // 保留小数点后两位 ROUND_HALF_UP = 四舍五入
        return b1.divide(b2, 2, RoundingMode.HALF_UP);
    }

    /**
     * 比较v1 v2大小
     *
     * @param v1 v1
     * @param v2 v2
     * @return int(v1 > v2 return 1 v1 = v2 return 0 v1 < v2 return - 1)
     */
    public static int doubleCompareTo(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.compareTo(b2);
    }

    public static int floatCompareTo(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.compareTo(b2);
    }

    /**
     * 获取小数长度
     * @param bigDecimal
     * @return
     */
    public static int getDecimalLength(BigDecimal bigDecimal) {
        String balanceStr = bigDecimal.toString();
        int indexOf = balanceStr.indexOf(".");
        if(indexOf>0) {
            return balanceStr.length() - 1 - indexOf;
        }
        return 0;
    }

    /**
     * 分转元
     * @param bigDecimal
     * @return
     */
    public static long convertYuan(BigDecimal bigDecimal) {
        return bigDecimal.multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_DOWN).longValue();
    }

    /**
     * 分转元
     * @param value
     * @return
     */
    public static long convertYuan(String value) {
        return new BigDecimal(value).multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_DOWN).longValue();
    }

    /**
     * 元转分
     * @param value
     * @return
     */
    public static String couvertFenStr(Long value) {
        return couvertFen(value).toString();
    }

    /**
     * 元转分
     * @param value
     * @return
     */
    public static BigDecimal couvertFen(Long value) {
        return new BigDecimal(value).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_DOWN);
    }

}
