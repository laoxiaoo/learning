package com.xiao.service;

import org.springframework.stereotype.Component;

/**
 * @author xiao jie
 * @create 2022年01月11日 17:44:00
 */
public class UserManagerSpy implements UserManager {
    @Override
    public String getRemoteUser() {
        return "spy";
    }
}
