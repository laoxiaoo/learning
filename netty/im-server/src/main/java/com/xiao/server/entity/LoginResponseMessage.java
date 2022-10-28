package com.xiao.server.entity;

import lombok.NoArgsConstructor;

/**
 * @author lao xiao
 * @date 2022年10月28日 11:37
 */
@NoArgsConstructor
public class LoginResponseMessage extends BaseResponse {

    public  LoginResponseMessage(Integer code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }


}
