package com.xiao.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 肖杰
 * @version 1.0.0
 * @ClassName CompleteTaskListener.java
 * @Description 完成任务监听
 * @createTime 2020年10月28日 10:09:00
 */
@Component
@Slf4j
public class CompleteTaskListener implements TaskListener {

    @Autowired
    private TaskService taskService;

    @Override
    public void notify(DelegateTask delegateTask) {
        log.debug("==================>完成task监听：{}", delegateTask);
        taskService.setAssignee(delegateTask.getId(), "2");
    }
}
