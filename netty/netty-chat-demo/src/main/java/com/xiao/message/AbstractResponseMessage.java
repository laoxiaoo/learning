package com.xiao.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 返回消息的公共抽象类
 *
 * @author lao xiao
 * @create 2022年05月07日 21:10:00
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public abstract class AbstractResponseMessage extends Message {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 原因
     */
    private String reason;

}
