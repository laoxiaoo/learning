package com.xiao.outer.code;

import cn.hutool.core.util.StrUtil;
import com.xiao.entity.BaseRequest;
import com.xiao.entity.CommonChatMessage;
import com.xiao.entity.LoginRequestMessage;
import com.xiao.util.JacksonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lao xiao
 * @date 2022年11月18日 17:50
 */
public class RequestCodeFactory {

    public static final int LoginRequestMessage=0;
    public static final int CommonChatMessage=1;

    private static final Map<Integer, Class<? extends BaseRequest>> messageClasses = new HashMap<>();

    static {
        messageClasses.put(LoginRequestMessage, com.xiao.entity.LoginRequestMessage.class);
        messageClasses.put(CommonChatMessage, com.xiao.entity.CommonChatMessage.class);
    }

    public static BaseRequest getMessageClass(String json) {
        String[] split = StrUtil.split(json, "&&");
        return JacksonUtil.toBean(split[1], messageClasses.get(Integer.valueOf(split[0])));
    }

}
