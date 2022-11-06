package com.xiao.server.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lao xiao
 * @date 2022年10月28日 17:47
 */
@Getter
@Setter
@ToString
public class CommonChatMessage extends BaseRequest{

    private String source;

    private String to;

    private String msg;

    @Override
    public int getMessageType() {
        return super.CommonChatMessage;
    }
}
