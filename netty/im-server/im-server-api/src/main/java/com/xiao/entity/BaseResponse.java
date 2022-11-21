package com.xiao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lao xiao
 * @date 2022年10月28日 11:38
 */
@Getter
@Setter
@ToString
public class BaseResponse {

    private Integer code;

    private String msg;
}
