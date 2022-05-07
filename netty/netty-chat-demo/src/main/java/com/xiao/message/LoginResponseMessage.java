package com.xiao.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lao xiao
 * @create 2022年05月07日 08:15:00
 */
@ToString
@Getter
@Setter
public class LoginResponseMessage extends Message{

    private Boolean success;

    private String reason;

    @Override
    public int getMessageType() {
        return 1;
    }

    public LoginResponseMessage(Boolean success, String reason) {
        this.reason = reason;
        this.success = success;
    }

}
