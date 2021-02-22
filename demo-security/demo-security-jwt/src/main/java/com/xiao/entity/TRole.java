package com.xiao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName TRole.java
 * @Description
 * @createTime 2021年01月23日 23:31:00
 */
@ToString
@Setter
@Getter
public class TRole {

    private Long id;

    private String roleCode;

    private String roleName;
}
