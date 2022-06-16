package com.xiao.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xiao jie
 * @create 2022年06月16日 15:08:00
 */
public class SpyDeepServiceImpl implements DeepService {
    @Override
    public String getDeep(Map map) {
        return "spy deep";
    }
}
