package com.xiao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author xiao ji hao
 * @create 2022年01月09日 22:39:00
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserManager userManager;

    @Override
    public String getUser() {
        return userManager.getRemoteUser();
    }
}
