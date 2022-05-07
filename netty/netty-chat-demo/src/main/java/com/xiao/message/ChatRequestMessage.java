package com.xiao.message;

import lombok.*;

/**
 * @author lao xiao
 * @create 2022年05月07日 21:09:00
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequestMessage extends Message {

    /**
     * 聊天内容
     */
    private String content;

    /**
     * 发送给谁
     */
    private String to;

    /**
     * 来源于谁
     */
    private String from;

    @Override
    public int getMessageType() {
        return 2;
    }
}
