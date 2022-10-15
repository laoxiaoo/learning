package com.xiao.dualDataSource.entity;

import com.xiao.dualDataSource.config.UserStateEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xiao ji hao
 * @create 2021年08月18日 22:50:00
 */
@ToString
@Getter
@Setter
public class Student {

    private Long id;

    private String name;

    private UserStateEnum status;

}
