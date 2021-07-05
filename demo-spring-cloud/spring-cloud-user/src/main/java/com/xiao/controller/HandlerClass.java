package com.xiao.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author xiao ji hao
 * @create 2021年07月04日 21:01:00
 */
public class HandlerClass {
    public static String blockHandler(String p1, String p2, BlockException exception) {
        return "block error";
    }
}
