package com.xiao.service;

/**
 * @author lao xiao
 * @create 2022年05月07日 08:10:00
 */
public interface UserService {

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    Boolean login(String username, String password);

}
