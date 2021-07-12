package com.xiao.clazz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author malone xiao
 * @ClassName InternalClass.java
 * @Description 内部类
 * @createTime 2021年05月13日 19:58:00
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
public class InternalClass {

    private Integer i;



    public class A {

    }

    public static class B {


    }

}
