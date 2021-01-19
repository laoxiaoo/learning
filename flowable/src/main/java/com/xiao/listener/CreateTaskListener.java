package com.xiao.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 肖杰
 *
 * 术语：
 *
 * Assignee: 任务的受理人，即执行人。它有两种情况（有值,NULL)
 *
 * Owner: 任务的委托人。
 *
 * CandidateGroup: 候选用户组
 *
 * CandidateUser: 候选人
 *
 * delegateTask: 委派任务/签收的任务
 *
 * resolveTask:  委派任务的代办，任务的拥有者把任务委派他人来办理，他人办完后，又重新回到任务拥有者，会产生流转记录。
 *
 * turnTask： 转办任务，只是改变当前任务的办理人而已，不会产生流转记录。
 *
 * CompleteTask: 完成任务，或叫办结提交下一步。
 *
 * claimTask：任务签收
 *
 * @version 1.0.0
 * @ClassName CreateTaskListener.java
 * @Description 创建任务
 * @createTime 2020年10月28日 10:26:00
 */
@Component
@Slf4j
public class CreateTaskListener implements TaskListener {
    @Autowired
    private TaskService taskService;
    @Override
    public void notify(DelegateTask delegateTask) {
        log.debug("创建task监听： {}", delegateTask.toString());
        //设置当前task的所属人
        taskService.setOwner(delegateTask.getId(), "2");
    }
}
