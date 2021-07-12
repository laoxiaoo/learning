package com.xiao.collection;

import java.util.HashMap;

/**
 * @author xiao ji hao
 * @create 2021年07月06日 10:28:00
 */
public class TestMap {

    public static void main(String[] args) {
        testHashMap();
    }

    public static void testHashMap() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(1, 1);
        hashMap.put(1, 2);
    }

}
