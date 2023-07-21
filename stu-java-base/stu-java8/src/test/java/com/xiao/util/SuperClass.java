package com.xiao.util;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * @author xiao jie
 * @date 2023-07-21 10:23
 */
public class SuperClass extends TypeReference<BigDecimals> {

    @Test
    public void test1() {
        System.out.println(super.getType());
    }

}
