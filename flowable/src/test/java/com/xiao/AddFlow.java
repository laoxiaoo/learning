package com.xiao;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;
import com.xiao.flow.BpmnModelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.service.delegate.TaskListener;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1 获取任务列表
 *
 * 1）获取候选人的任务列表
 *
 * TaskService taskService = processEngine.getTaskService();
 * List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("kermit").list();
 * 2）如果任务分配给了某一组，查询某一组的任务列表
 *
 * TaskService taskService = processEngine.getTaskService();
 * List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
 *
 *
 * 2 claim the task，当任务分配给了某一组人员时，需要该组人员进行抢占。抢到了就将该任务给谁处理，其他人不能处理。
 *
 * taskService.claim(task.getId(), "fozzie");
 * 此时，该任务已经成为个人的了，可以通过以下接口进行查询该人的待办
 *
 * List<Task> tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
 *
 *
 * 3 完成 Task，当他将任务做完后可以将该Task结束，API如下
 *
 * taskService.complete(task.getId());
 *
 *
 * @program: learning
 * @description: TODO
 * @author: lonely xiao
 * @create: 2020-10-15 23:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AddFlow {

    //private final String flow = "{\"name\":\"流程B\",\"nodeList\":[{\"id\":\"j64q7773on\",\"name\":\"数据接入\",\"type\":\"timer\",\"left\":\"242px\",\"top\":\"201px\",\"ico\":\"el-icon-time\",\"state\":\"success\"},{\"id\":\"om6o6k1sci\",\"name\":\"流程结束\",\"type\":\"end\",\"left\":\"835px\",\"top\":\"244px\",\"ico\":\"el-icon-caret-right\",\"state\":\"success\"},{\"id\":\"fzdij1skj\",\"name\":\"数据接入1\",\"type\":\"timer\",\"left\":\"457px\",\"top\":\"173px\",\"ico\":\"el-icon-time\",\"state\":\"success\"},{\"id\":\"iq2vj993k\",\"name\":\"数据接入2\",\"type\":\"timer\",\"left\":\"493px\",\"top\":\"308px\",\"ico\":\"el-icon-time\",\"state\":\"success\"},{\"id\":\"f5jbsmgks\",\"name\":\"接口调用\",\"type\":\"task\",\"left\":\"40px\",\"top\":\"203px\",\"ico\":\"el-icon-odometer\",\"state\":\"success\"}],\"lineList\":[{\"from\":\"f5jbsmgks\",\"to\":\"j64q7773on\"},{\"from\":\"j64q7773on\",\"to\":\"fzdij1skj\"},{\"from\":\"fzdij1skj\",\"to\":\"iq2vj993k\"},{\"from\":\"iq2vj993k\",\"to\":\"om6o6k1sci\"}]}";

    private final String flow = "{\"name\":\"流程\",\"nodeList\":[{\"id\":\"nodeStart\",\"name\":\"流程发起节点\",\"type\":\"start\",\"left\":\"84px\",\"top\":\"248px\",\"ico\":\"icon-flow-start green\"},{\"id\":\"nodeEnd\",\"name\":\"流程结束\",\"type\":\"end\",\"left\":\"1139px\",\"top\":\"258px\",\"ico\":\"icon-flow-end red\"},{\"id\":\"hsh5hukxxm\",\"name\":\"流程节点2\",\"type\":\"task\",\"left\":\"492px\",\"top\":\"249px\",\"ico\":\"icon-flow-node green\",\"state\":\"success\"},{\"id\":\"z5mc3ikea\",\"name\":\"流程节点\",\"type\":\"task\",\"left\":\"684px\",\"top\":\"157px\",\"ico\":\"icon-flow-node green\",\"state\":\"success\"},{\"id\":\"pme8oslt4u\",\"name\":\"流程节点1\",\"type\":\"task\",\"left\":\"706px\",\"top\":\"344px\",\"ico\":\"icon-flow-node green\",\"state\":\"success\"}],\"lineList\":[{\"from\":\"nodeStart\",\"to\":\"hsh5hukxxm\"},{\"from\":\"hsh5hukxxm\",\"to\":\"z5mc3ikea\"},{\"from\":\"hsh5hukxxm\",\"to\":\"pme8oslt4u\"},{\"from\":\"z5mc3ikea\",\"to\":\"nodeEnd\"},{\"from\":\"pme8oslt4u\",\"to\":\"nodeEnd\"}]}";
    //private final String flow = "{\"name\":\"流程\",\"nodeList\":[{\"id\":\"nodeStart\",\"name\":\"流程发起节点\",\"type\":\"start\",\"left\":\"84px\",\"top\":\"248px\",\"ico\":\"icon-flow-start green\"},{\"id\":\"nodeEnd\",\"name\":\"流程结束\",\"type\":\"end\",\"left\":\"1139px\",\"top\":\"258px\",\"ico\":\"icon-flow-end red\"},{\"id\":\"hsh5hukxxm\",\"name\":\"流程节点2\",\"type\":\"task\",\"left\":\"492px\",\"top\":\"249px\",\"ico\":\"icon-flow-node green\",\"state\":\"success\",\"candidates\":{\"users\":[1,2,3],\"departs\":[1,2],\"roles\":[]},\"columnIds\":[1,2,3]},{\"id\":\"z5mc3ikea\",\"name\":\"流程节点\",\"type\":\"task\",\"left\":\"684px\",\"top\":\"157px\",\"ico\":\"icon-flow-node green\",\"state\":\"success\"},{\"id\":\"pme8oslt4u\",\"name\":\"流程节点1\",\"type\":\"task\",\"left\":\"706px\",\"top\":\"344px\",\"ico\":\"icon-flow-node green\",\"state\":\"success\"}],\"lineList\":[{\"from\":\"nodeStart\",\"to\":\"hsh5hukxxm\"},{\"from\":\"hsh5hukxxm\",\"to\":\"z5mc3ikea\"},{\"from\":\"hsh5hukxxm\",\"to\":\"pme8oslt4u\"},{\"from\":\"z5mc3ikea\",\"to\":\"nodeEnd\"},{\"from\":\"pme8oslt4u\",\"to\":\"nodeEnd\"}]}";

    //流程存储组件
    @Autowired
    private RepositoryService repositoryService;
    //运行时服务组件
    @Autowired
    private RuntimeService runtimeService;
    //流程任务组件
    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;



    String processId= "process5";

    @Test
    public void test() {

        if(true) {
            return;
        }
        JSONObject flowJson = JSONUtil.parseObj(flow);
        BpmnModel bpmnModel=new BpmnModel();



        JSONArray nodeList = flowJson.getJSONArray("nodeList");
        JSONArray lineList = flowJson.getJSONArray("lineList");
        Map<String, JSONObject> map = nodeList.stream().map(m -> (JSONObject) m).collect(Collectors.toMap(m -> m.get("id").toString(), m -> m));

        //获取 流程开始节点
        //开始节点的属性
        StartEvent startEvent=null;
        //结束节点属性
        EndEvent endEvent=null;
        List<UserTask> userTasks = new ArrayList<>();
        for(int i=0; i<nodeList.size(); i++) {
            JSONObject node = nodeList.getJSONObject(i);
            if("start".equals(node.getStr("type"))) {
                startEvent=new StartEvent();
                startEvent.setId(node.getStr("id"));
                startEvent.setName(node.getStr("name"));
            }

            if("end".equals(node.getStr("type"))) {
                endEvent=new EndEvent();
                endEvent.setId(node.getStr("id"));
                endEvent.setName(node.getStr("name"));
//                FlowableListener completeTaskListener = new FlowableListener();
//                completeTaskListener.setEvent(TaskListener.EVENTNAME_COMPLETE);
//                completeTaskListener.setImplementation("${endTaskListener}");
//                completeTaskListener.setImplementationType("delegateExpression");
//                endEvent.setExecutionListeners(CollUtil.toList(completeTaskListener));
            }

            if("task".equals(node.getStr("type"))) {
                UserTask userTask=new UserTask();
                userTask.setCandidateUsers(CollUtil.toList("1", "2", "3"));
                userTask.setId(node.getStr("id"));
                userTask.setName(node.getStr("name"));
                FlowableListener completeTaskListener = new FlowableListener();
                completeTaskListener.setEvent(TaskListener.EVENTNAME_COMPLETE);
                //设置完成监听
                completeTaskListener.setImplementation("${completeTaskListener}");
                completeTaskListener.setImplementationType("delegateExpression");


                FlowableListener createListener = new FlowableListener();
                createListener.setEvent(TaskListener.EVENTNAME_CREATE);
                //设置创建监听
                createListener.setImplementation("${createTaskListener}");
                createListener.setImplementationType("delegateExpression");


                userTask.setTaskListeners(CollUtil.toList(completeTaskListener, createListener));
                userTasks.add(userTask);
            }
        }

        //绘制线段
        //Process对象
        Process process=new Process();
        //每个节点出去的线的集合
        Map<String, List<SequenceFlow>> out = new HashMap<>();
        Map<String, List<SequenceFlow>> in = new HashMap<>();
        for(int i=0; i<lineList.size(); i++) {
            SequenceFlow s2 = new SequenceFlow();
            JSONObject line = lineList.getJSONObject(i);
            //bpmn id要以字母开头
            String id = "line_"+RandomUtil.randomString(6);
            s2.setId(id);
            s2.setName(id);
            s2.setSourceRef(line.getStr("from"));
            s2.setTargetRef(line.getStr("to"));

            out.computeIfAbsent(line.getStr("from"), k -> new ArrayList<>()).add(s2);
            in.computeIfAbsent(line.getStr("to"), k -> new ArrayList<>()).add(s2);
            process.addFlowElement(s2);
        }

        //节点出去的线段>1的产生网关
        out.entrySet().stream().filter(lines -> lines.getValue().size() > 1).forEach(lines -> {
            List<SequenceFlow> sequenceFlows = lines.getValue();
            sequenceFlows.get(0).setConditionExpression("${days>5}");
            sequenceFlows.get(1).setConditionExpression("${days<=5}");
            //生成网关
            ExclusiveGateway eventGateway = new ExclusiveGateway();
            String id = "gateWay" + RandomUtil.randomString(6);
            eventGateway.setId(id);
            eventGateway.setName(id);

            //生成一根线,到达点为网关
            SequenceFlow sequenceFlow = new SequenceFlow();
            String lineId = "line_" + RandomUtil.randomString(6);
            sequenceFlow.setId(lineId);
            sequenceFlow.setName(lineId);
            sequenceFlow.setSourceRef(sequenceFlows.get(0).getSourceRef());
            sequenceFlow.setTargetRef(id);
            process.addFlowElement(sequenceFlow);
            //重置另外有条件的线
            sequenceFlows.stream().forEach(seqLines -> {
                seqLines.setSourceRef(id);
            });
            process.addFlowElement(eventGateway);
        });


        //设置节点线段
        startEvent.setOutgoingFlows(CollUtil.toList(out.get(startEvent.getId()).get(0)));
        endEvent.setIncomingFlows(in.get(startEvent.getId()));
        userTasks.stream().forEach(userTask -> {
            //设置进来的线段
            userTask.setIncomingFlows(in.get(userTask.getId()));
            //设置出去的先
            userTask.setOutgoingFlows(CollUtil.toList(out.get(userTask.getId()).get(0)));
            ExtensionElement extensionElement = new ExtensionElement();

            process.addFlowElement(userTask);
        });



        process.setId(processId);
        process.setName(processId);
        process.addFlowElement(startEvent);
        process.addFlowElement(endEvent);

        System.out.println(userTasks);
        bpmnModel.addProcess(process);

        //查看流程绘制情况
        BpmnXMLConverter bpmnXMLConverter=new BpmnXMLConverter();
        byte[] convertToXML = bpmnXMLConverter.convertToXML(bpmnModel);
        String bytes=new String(convertToXML);

        System.out.println(bytes);

        //验证bpmnModel 是否是正确的bpmn xml文件

        ProcessValidatorFactory processValidatorFactory=new ProcessValidatorFactory();

        ProcessValidator defaultProcessValidator = processValidatorFactory.createDefaultProcessValidator();

        //验证失败信息的封装ValidationError
        List<ValidationError> validate = defaultProcessValidator.validate(bpmnModel);
        //如果size为0说明，bpmnmodel正确，大于0,说明自定义的bpmnmodel是错误的，不可以使用的。
        System.out.println(validate.size());

        //部署流程
        Deployment deploy = repositoryService.createDeployment()
                .name("测试流程")
                .key(processId)
                .addBpmnModel("bpmnModel.bpmn", bpmnModel).deploy();

        System.out.println(deploy.toString());

        Deployment deployment = repositoryService.createDeploymentQuery().processDefinitionKey(processId).singleResult();
        if (deployment == null){
            System.out.println("没有该流程");
        }
        //流程部署id
        String deploymentId = deployment.getId();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processId);
        //获取流程定义id
        String definitionId = processInstance.getProcessDefinitionId();
        //流程实例id
        String processInstanceId = processInstance.getProcessInstanceId();

        log.debug("============>开始流转第一个节点");
        this.getTaskHistory(processInstanceId);
        this.getAssignee(processInstanceId);
        Task task = this.getTask(processInstanceId);
        this.completeTask(task);

        log.debug("<============结束流转第一个节点");
        log.debug("============>开始流转第二个节点");
        this.getOwnerTask("2");
        this.getTaskHistory(processInstanceId);
        this.getAssignee(processInstanceId);
        Task task2 = this.getTask(processInstanceId);
        Map vars = new HashMap<>();
        vars.put("days", 7);
        taskService.setVariablesLocal(task2.getId(), vars);
        this.completeTask(task2);
        log.debug("<============结束流转第二个节点");
        Task task3 = this.getTask(processInstanceId);
        this.completeTask(task3);
        Task task4 = this.getTask(processInstanceId);
        this.completeTask(task4);
        System.out.println(task4);
//        Task task = taskService.createTaskQuery().singleResult();
//        task.setAssignee("1");
//        System.out.println("当前任务：" + task.toString());
//        this.getTaskHistory(processInstanceId);
//        Map vars = new HashMap<>();
//        vars.put("days", 7);
//        taskService.complete(task.getId(), vars);
//        Task task2 = taskService.createTaskQuery().singleResult();
//        System.out.println("当前任务：" + task2.toString());
//        this.getAssignee(processInstanceId);
//        this.getTaskHistory(processInstanceId);
//        //taskService.complete(task2.getId());
//        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("1").list();
//        System.out.println("当前用户的任务列表："+ ArrayUtil.toString(tasks));
    }


    @Test
    public void testBuilder() {
        BpmnModel bpmnModel = new BpmnModelBuilder(flow).builderNode().builderLine().builderRelation().builder(processId).checkModel();
        //查看流程绘制情况
        BpmnXMLConverter bpmnXMLConverter=new BpmnXMLConverter();
        byte[] convertToXML = bpmnXMLConverter.convertToXML(bpmnModel);
        String bytes=new String(convertToXML);

        System.out.println(bytes);

        //部署流程
        Deployment deploy = repositoryService.createDeployment()
                .name("测试流程")
                .key(processId)
                .addBpmnModel("bpmnModel.bpmn", bpmnModel).deploy();

        System.out.println(deploy.toString());

        Deployment deployment = repositoryService.createDeploymentQuery().processDefinitionKey(processId).singleResult();
        if (deployment == null){
            System.out.println("没有该流程");
        }
        //流程部署id
        String deploymentId = deployment.getId();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processId);
        //获取流程定义id
        String definitionId = processInstance.getProcessDefinitionId();
        //流程实例id
        String processInstanceId = processInstance.getProcessInstanceId();

        log.debug("============>开始流转第一个节点");
        this.getTaskHistory(processInstanceId);
        this.getAssignee(processInstanceId);
        Task task = this.getTask(processInstanceId);
        this.getCopyUsers(task);
        this.getNextNode(task);
        Map vars = new HashMap<>();
        vars.put("days", 7);
        //设置当前task的变量
        taskService.setVariablesLocal(task.getId(), vars);
        Map<String, Object> map = task.getTaskLocalVariables();
        System.out.println(map);
        //获取变量值
        Map<String, Object> variables = taskService.getVariables(task.getId());
        //这个task下面有两个节点
        this.completeTask(task);
        log.debug("<============结束流转第一个节点");
        log.debug("============>开始流转第二个节点");
        this.getOwnerTask("2");
        this.getTaskHistory(processInstanceId);
        this.getAssignee(processInstanceId);
        Task task2 = this.getTask(processInstanceId);

        variables = taskService.getVariables(task2.getId());
        this.completeTask(task2);
        log.debug("<============结束流转第二个节点");
    }



    /**
     * 获取当前流程的流程历史
     * @param processInstanceId
     */
    public void getTaskHistory(String processInstanceId) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
        list.stream().forEach(historicTaskInstance -> {
            System.out.println("历史流程："+historicTaskInstance.getName()+" 处理人："+ historicTaskInstance.getAssignee()+" 任务所属人："+historicTaskInstance.getOwner());
        });
    }

    /**
     * 获取当前处理人的任务
     * @param userId
     */
    public void getOwnerTask (String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskOwner(userId).list();
        log.debug("当前用户[{}]的任务集合{}", userId, ArrayUtil.toString(tasks));
    }

    /**
     * 获取当前人的代理人
     * @param processInstanceId
     */
    public void getAssignee(String processInstanceId) {
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        System.out.println("当前实例任务:"+task.toString());
    }


    /**
     * 获取当前流程任务
     * @param processInstanceId
     * @return
     */
    public Task getTask(String processInstanceId) {
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();

        log.debug("当前user[2]任务:", task.toString());
        return task;
    }

    /**
     * 流转流程
     * @param task
     */
    public void completeTask(Task task) {

        taskService.complete(task.getId());
        log.debug("完成流程：", task.toString());
    }

    /**
     * 获取下一步的候选人
     * @param userId
     * @param processInstanceId
     */
    public void getConditionUserId(String userId, String processInstanceId) {
        //taskService.createTaskQuery().

        List<Task> list = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
    }

    /**
     * 获取当前节点信息
     * @param task
     */
    public void getNowNode(Task task) {
        Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        //String activityId = execution.getActivityId();
        log.debug("{} execution : {}", task.getName(), execution);
        System.out.println(execution);
        // 当前审批节点
        String currentActivityId = execution.getActivityId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(currentActivityId);
        Map<String, List<ExtensionElement>> extensionElements = flowNode.getExtensionElements();

        // 输出连线
        List<SequenceFlow> outFlows = flowNode.getOutgoingFlows();
        /*for (SequenceFlow sequenceFlow : outFlows) {
            //当前审批节点
            if ("now".equals(node)) {
                FlowElement sourceFlowElement = sequenceFlow.getSourceFlowElement();
                System.out.println("当前节点: id=" + sourceFlowElement.getId() + ",name=" + sourceFlowElement.getName());
            } else if ("next".equals(node)) {
                // 下一个审批节点
                FlowElement targetFlow = sequenceFlow.getTargetFlowElement();
                if (targetFlow instanceof UserTask) {
                    System.out.println("下一节点: id=" + targetFlow.getId() + ",name=" + targetFlow.getName());
                }
                // 如果下个审批节点为结束节点
                if (targetFlow instanceof EndEvent) {
                    System.out.println("下一节点为结束节点：id=" + targetFlow.getId() + ",name=" + targetFlow.getName());
                }
            }
        }*/
    }

    public void getCopyUsers (Task task) {
        Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        String currentActivityId = execution.getActivityId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(currentActivityId);
        Map<String, List<ExtensionElement>> extensionElements = flowNode.getExtensionElements();
        List<ExtensionElement> extensionElements1 = extensionElements.get(BpmnModelBuilder.COPY_USER_ID);
        System.out.println(extensionElements1.toString());
    }

    public void getNextNode(Task task) {
        Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();

        // 当前审批节点
        String currentActivityId = execution.getActivityId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(currentActivityId);
        // 输出连线
        List<SequenceFlow> outFlows = flowNode.getOutgoingFlows();

        getNext(outFlows);
    }

    public UserTask getNext(List<SequenceFlow> outFlows) {
        for (SequenceFlow sequenceFlow : outFlows) {
            FlowElement targetFlow = sequenceFlow.getTargetFlowElement();

            if(ObjectUtil.isNull(sequenceFlow.getConditionExpression())
                    || Boolean.valueOf(
                    String.valueOf(
                            result(sequenceFlow.getConditionExpression()
                                    .substring(sequenceFlow.getConditionExpression().lastIndexOf("{") + 1,
                                            sequenceFlow.getConditionExpression().lastIndexOf("}")))))) {
                //用户任务
                if(targetFlow instanceof ExclusiveGateway) {
                    List<SequenceFlow> outgoingFlows = ((ExclusiveGateway) targetFlow).getOutgoingFlows();
                    getNext(outgoingFlows);
                }
                if(targetFlow instanceof UserTask) {
                    UserTask userTask = (UserTask) targetFlow;
                    if(ObjectUtil.isNotEmpty(userTask)) {

                    }
                    return (UserTask)targetFlow;
                }
            }

        }
        return null;
    }

    /**
     * 校验el表达示例
     *
     * @param
     * @param exp
     * @return
     */
    public static Object result(String exp) {

        FelEngine fel = new FelEngineImpl();
        FelContext ctx = fel.getContext();
        ctx.set("days", 4);
        Object value = fel.eval(exp);
        return value;
    }


    @Test
    public void test2() {
        //测试SpringEL解析器
        //String template = "#{#user}";//设置文字模板,其中#{}表示表达式的起止，#user是表达式字符串，表示引用一个变量。
        String template= "#{#user}";

        FelEngine fel = new FelEngineImpl();
        FelContext ctx = fel.getContext();
        ctx.set("单价", 1.5898);
        ctx.set("数量", 1);
        ctx.set("运费", 75);

        Object result = fel.eval("单价*数量+运费");
        System.out.println(result);
    }

}