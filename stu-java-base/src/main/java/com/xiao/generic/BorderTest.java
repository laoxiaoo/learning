package com.xiao.generic;

/**
 * @author 肖杰
 * @ClassName BorderTest.java
 * @Description 泛型擦写边界
 * @createTime 2021年04月08日 18:00:00
 */
public class BorderTest {

    static class Border1<T extends Integer> {
    }

    static class Border2<T extends String> {
    }
    public static void main(String[] args) {
        Border1<Integer> border1 = new Border1<>();
        Border2<String> border2 = new Border2<>();
        System.out.println(border1.getClass().equals(border2.getClass()));
    }

}
