package com.xiao.service;


import org.springframework.stereotype.Service;

/**
 * @author xiao ji hao
 * @create 2022年01月09日 22:40:00
 */
@Service
public class UserManagerImpl implements UserManager {

    @Override
    public String getRemoteUser() {
        return "remoterUser";
    }
}
