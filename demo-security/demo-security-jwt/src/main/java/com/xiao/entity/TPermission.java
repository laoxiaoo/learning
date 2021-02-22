package com.xiao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName TPermission.java
 * @Description
 * @createTime 2021年01月24日 19:35:00
 */
@ToString
@Getter
@Setter
public class TPermission {

    private Long id;

    private String permissionCode;

    private String permissionName;
}
