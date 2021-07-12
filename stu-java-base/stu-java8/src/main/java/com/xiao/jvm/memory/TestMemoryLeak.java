package com.xiao.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malone Xiao
 * @create 2021年06月07日 20:40:00
 */
public class TestMemoryLeak {

    static List list = new ArrayList();

    public void add() {
        list.add(new Object());
    }

}
