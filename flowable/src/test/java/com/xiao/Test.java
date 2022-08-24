package com.xiao;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;

/**
 * @author lao xiao
 * @version 1.
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2021年01月22日 14:34:00
 */
public class Test {


    @org.junit.Test
    public void test2() {
        //测试SpringEL解析器
        //String template = "#{#user}";//设置文字模板,其中#{}表示表达式的起止，#user是表达式字符串，表示引用一个变量。
        String template= "#{#user}";

        FelEngine fel = new FelEngineImpl();
        FelContext ctx = fel.getContext();
        ctx.set("单价", 1.5898);
        ctx.set("数量", 1);
        ctx.set("运费", 75);


        ctx.set("a1", 1);
        ctx.set("a2", 2);
        Object result = fel.eval("a1*");
        System.out.println(result);
    }

}
