package com.xiao.diamond;

import cn.hutool.core.collection.CollUtil;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiao jie
 * @create 2022年01月18日 16:28:00
 */
public class D implements B,C{

    @Override
    public void show() {
        B.super.show();
    }


    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("1", null);
        objectObjectHashMap.put("2", null);
        List collect = objectObjectHashMap.values()
                .stream()
                .map(Object::hashCode)
                .collect(Collectors.toList());
    }
}
