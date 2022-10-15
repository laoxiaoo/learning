package com.xiao.listener;

import org.flowable.engine.TaskService;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lao xiao
 * @version 1.0.0
 * @ClassName CommonTaskListener.java
 * @Description 通用流程监听类
 * @createTime 2020年10月27日 09:15:00
 */
@Component
public class CommonTaskListener implements TaskListener {

    @Autowired
    private TaskService taskService;
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("监听执行："+delegateTask.toString());
        //指定任务处理人
        taskService.setAssignee(delegateTask.getId(), "1");
        //delegateTask.setOwner("2");
    }
}
