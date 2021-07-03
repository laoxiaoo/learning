package com.xiao.jvm.gc;


import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author Malone Xiao
 * @ClassName TestSoftReference.java
 * @Description
 * @createTime 2021年05月20日 22:54:00
 */
public class TestSoftReference {

    public static void main(String[] args) {
        Object o = new Object();
        SoftReference<Object> reference = new SoftReference<>(o);
        //销毁强引用
        o = null;
        //获取软引用对象
        Object o1 = reference.get();
    }


    @Test
    public void test1() {
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);
        o = null;
        Object o1 = weakReference.get();
    }

    public void test2() {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> reference = new PhantomReference<>(o, referenceQueue);
        //一旦将Object对象回收，就会将虚引用存放到ReferenceQueue队列
        o = null;
    }
}
