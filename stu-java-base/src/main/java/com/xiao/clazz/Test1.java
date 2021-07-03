package com.xiao.clazz;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author malone xiao
 * @ClassName Test1.java
 * @Description
 * @createTime 2021年05月17日 20:04:00
 */
public class Test1 {

    public static void main(String[] args) {
        String str = new String("aaa");

        ConcurrentHashMap currentMap = new ConcurrentHashMap();
        currentMap.put("aa", "aa");

        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        System.out.println(list1.getClass().equals(list2.getClass()));
    }

    public void method1(int a) {

    }

}
