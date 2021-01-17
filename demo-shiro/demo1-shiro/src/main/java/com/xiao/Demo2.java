package com.xiao;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName Demo2.java
 * @Description
 * @createTime 2021年01月13日 22:24:00
 */
public class Demo2 {

    public static void main(String[] args) {
        //md5:202cb962ac59075b964b07152d234b70
        Md5Hash md51 = new Md5Hash("123");
        System.out.println(md51.toHex());

        //md5+salt:50c6cfa137465a41726781e29d325a7a
        Md5Hash md52 = new Md5Hash("123", "lonely");
        System.out.println(md52.toHex());

        //md5+salt+hash散列:646bea76fce01bfaec5b9a8bf36b3938
        Md5Hash md53 = new Md5Hash("123", "lonely", 1024);
        System.out.println(md53.toHex());
    }

}
