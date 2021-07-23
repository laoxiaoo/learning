package com.xiao.aop.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TargetServiceImpl implements TargetService {
    @Override
    public void sayHello() {
      log.debug("==>sayHello");
    }
}
