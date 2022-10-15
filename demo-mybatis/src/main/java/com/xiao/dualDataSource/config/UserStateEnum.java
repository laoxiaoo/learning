package com.xiao.dualDataSource.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStateEnum implements BaseCodeEnum{
    OPEN(1),         //开启
    CLOSE(2),         //关闭
    OFF_LINE(3),     //离线
    FAULT(4),     //故障
    UNKNOWN(5);     //未知

    private int code;

}