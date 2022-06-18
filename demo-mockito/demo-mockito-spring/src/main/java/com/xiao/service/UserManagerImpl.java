package com.xiao.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author xiao ji hao
 * @create 2022年01月09日 22:40:00
 */
@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    private DeepService deepService;

    @Override
    public String getRemoteUser() {
        return deepService.getDeep(new HashMap());
    }

}
