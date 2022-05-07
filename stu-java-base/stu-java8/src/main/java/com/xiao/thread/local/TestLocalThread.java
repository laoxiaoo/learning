package com.xiao.thread.local;

/**
 * @author xiao ji hao
 * @create 2021年07月16日 08:19:00
 */
public class TestLocalThread {

    public static void main(String[] args) {
        TestLocalThread testLocalThread = new TestLocalThread();
        testLocalThread.before();
    }



    private static ThreadLocal<String> tl = new ThreadLocal<>();

    public void before() {
        tl.set("laoxiao");
    }

}
