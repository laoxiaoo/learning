package com.xiao.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xiao ji hao
 * @create 2022年01月07日 21:39:00
 */
@Getter
@Setter
@ToString
public class LoginDTO implements Serializable {

    private String password;

    private String username;

}
