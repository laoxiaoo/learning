package com.xiao.service;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiao jie
 * @create 2022年06月16日 15:00:00
 */
@Component
public class DeepServiceImpl implements DeepService {
    @Override
    public String getDeep(Map map) {
        return "deep1";
    }
}
