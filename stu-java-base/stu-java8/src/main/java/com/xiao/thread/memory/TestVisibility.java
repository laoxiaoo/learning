package com.xiao.thread.memory;

import lombok.extern.slf4j.Slf4j;

import static com.xiao.thread.lock.TestPark.sleep;

/**
 *
 * 可见性问题测试
 * @author xiao ji hao
 * @create 2021年06月21日 16:45:00
 */
@Slf4j
public class TestVisibility {
    static boolean bool = true;
    static Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if(!bool) {
                        break;
                    }
                }
            }
        }).start();
        sleep(1000);
        log.debug("暂停下来..");
        //i++;
        bool = false;
    }

    public void method() {
        int a = 1;
        boolean b = true;
    }

}
