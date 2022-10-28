package com.xiao.server.entity;

import cn.hutool.core.util.StrUtil;
import com.xiao.util.JacksonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lao xiao
 * @date 2022年10月28日 13:58
 */
public abstract class BaseRequest {

    public static BaseRequest getMessageClass(String json) {
        String[] split = StrUtil.split(json, "&&");
        return JacksonUtil.toBean(split[1], messageClasses.get(Integer.valueOf(split[0])));
    }

    public abstract int getMessageType();

    public static final int LoginRequestMessage=0;
    public static final int CommonChatMessage=1;

    private static final Map<Integer, Class<? extends BaseRequest>> messageClasses = new HashMap<>();

    static {
        messageClasses.put(LoginRequestMessage, LoginRequestMessage.class);
        messageClasses.put(CommonChatMessage, CommonChatMessage.class);
    }

}
