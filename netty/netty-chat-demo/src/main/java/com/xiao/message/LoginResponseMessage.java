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
public class LoginResponseMessage extends AbstractResponseMessage {

    @Override
    public int getMessageType() {
        return 1;
    }

    public LoginResponseMessage(Boolean success, String reason) {
        super(success, reason);
    }

}
