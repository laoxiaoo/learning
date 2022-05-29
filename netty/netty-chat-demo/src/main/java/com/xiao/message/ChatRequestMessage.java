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
     * 来源于谁
     */
    private String from;


    /**
     * 发送给谁
     */
    private String to;

    /**
     * 聊天内容
     */
    private String content;

    @Override
    public int getMessageType() {
        return 2;
    }
}
