package com.xiao.jvm.string;

/**
 * @author Malone Xiao
 * @ClassName TestString2.java
 * @Description
 * @createTime 2021年05月15日 23:07:00
 */
public class TestString2 {

    public static void main(String[] args) {
        //String a = new String("ab");
        //StringBuilder的toString不会在常量池产生“ab"
        String str = new String("a") + new String("b");
        //常量池没有，在常量池创建一个引用，指向堆区的“ab"
        System.out.println(str.hashCode());
        str.intern();
        String b = "ab";
        //true
        System.out.println(str == b);
        System.out.println(str.hashCode());

        String java = "ja" + new String("va");
        String jTmp = java.intern();
        System.out.println(java == jTmp);
    }

}
