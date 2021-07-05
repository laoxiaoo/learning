package com.xiao.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xiao.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author xiao ji hao
 * @create 2021年07月04日 11:23:00
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/get")
    public UserVO getUser(Long userId) {
        log.debug("接收到请求：{}", userId);
        return new UserVO("张三", 20);
    }

    @GetMapping("/a")
    public String getA() {
        return "A";
    }

    @GetMapping("/b")
    public String getB() throws InterruptedException {
        Thread.sleep(2000);
        return "B";
    }

    @SentinelResource(value = "hotKey", blockHandlerClass = HandlerClass.class, blockHandler = "blockHandler")
    @GetMapping("/hotKey")
    public String hotKey(String p1, String p2) {
        return "hotkey";
    }

    public String blockHandler(String p1, String p2, BlockException exception) {
        return "block error";
    }

    @SentinelResource(value = "fallback", fallback = "handlerFallback")
    @RequestMapping("/fallback")
    public String fallback(String p1) {
        if(Objects.equals(p1, "1")) {
            throw new IllegalArgumentException("发生了异常");
        }
        return "fallback";
    }

    public String handlerFallback(String p1, Throwable e) {
        return "异常方法....";
    }




}
