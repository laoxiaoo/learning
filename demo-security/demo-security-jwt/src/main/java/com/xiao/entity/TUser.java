package com.xiao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName TUser.java
 * @Description
 * @createTime 2021年01月23日 22:54:00
 */
@ToString
@Setter
@Getter
public class TUser {

    private Long id;

    private String username;

    private String loginAccount;

    private String password;
}
