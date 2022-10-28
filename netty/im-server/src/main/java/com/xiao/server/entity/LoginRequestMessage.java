package com.xiao.server.entity;

import lombok.*;

/**
 * @author lao xiao
 * @create 2022年05月05日 22:03:00
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestMessage extends BaseRequest  {
    private String username;
    private String password;

    @Override
    public int getMessageType() {
        return super.LoginRequestMessage;
    }
}
