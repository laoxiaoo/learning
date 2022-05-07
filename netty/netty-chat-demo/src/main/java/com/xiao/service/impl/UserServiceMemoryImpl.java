package com.xiao.service.impl;

import com.xiao.service.UserService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟数据库登录的内存登陆service
 *
 * @author lao xiao
 * @create 2022年05月07日 08:11:00
 */
public class UserServiceMemoryImpl implements UserService {

    private Map<String, String> allUserMap = new ConcurrentHashMap<>();

    {
        allUserMap.put("zhangsan", "123");
        allUserMap.put("lisi", "123");
        allUserMap.put("wangwu", "123");
        allUserMap.put("zhaoliu", "123");
        allUserMap.put("qianqi", "123");
    }

    @Override
    public Boolean login(String username, String password) {
        String pass = allUserMap.get(username);
        if (pass == null) {
            return Boolean.FALSE;
        }
        return pass.equals(password);
    }
}
