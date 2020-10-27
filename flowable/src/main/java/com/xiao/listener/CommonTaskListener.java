package com.xiao.listener;

import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * @author 肖杰
 * @version 1.0.0
 * @ClassName CommonTaskListener.java
 * @Description 通用流程监听类
 * @createTime 2020年10月27日 09:15:00
 */
@Component
public class CommonTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("监听执行："+delegateTask.toString());
    }
}
