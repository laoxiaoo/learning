package com.xiao.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * @author 肖杰
 * @version 1.2.8
 * @ClassName EndTaskListener.java
 * @Description
 * @createTime 2020年12月03日 19:35:00
 */
@Component
@Slf4j
public class EndTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        log.error("结束节点...");
    }
}
