package com.xiao.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lao xiao
 * @create 2022年05月07日 21:12:00
 */
@Getter
@Setter
@ToString
public class ChatResponseMessage extends AbstractResponseMessage {
    /**
     * 从谁返回的
     */
    private String from;

    /**
     * 返回的内容
     */
    private String content;

    public ChatResponseMessage() {
        super(Boolean.TRUE, null);
    }

    public ChatResponseMessage(Boolean success, String reason) {
        super(success, reason);
    }

    @Override
    public int getMessageType() {
        return 3;
    }
}
