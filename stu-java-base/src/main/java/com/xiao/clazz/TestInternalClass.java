package com.xiao.clazz;

/**
 * @author malone xiao
 * @ClassName TestInternalClass.java
 * @Description
 * @createTime 2021年05月13日 19:59:00
 */
public class TestInternalClass {

    public static void main(String[] args) {
        InternalClass.A a = new InternalClass().new A();

        InternalClass.B b = new InternalClass.B();
    }

}
