package com.xiao.flow;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.task.service.delegate.TaskListener;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 肖杰
 * @version 1.0.0
 * @ClassName BpmnModelBuilder.java
 * @Description bpmn模型构造器
 * @createTime 2020年10月28日 08:46:00
 */
public class BpmnModelBuilder {
    private JSONObject flowJson;
    private JSONArray nodeList;
    private JSONArray lineList;

    private List<UserTask> userTasks;
    private StartEvent startEvent;
    private EndEvent endEvent;
    private Process process;
    //每个节点出去的线的集合
    private Map<String, List<SequenceFlow>> out;
    private Map<String, List<SequenceFlow>> in;

    private BpmnModel bpmnModel;


    private final String taskNodeType = "task";
    private final String startNodeType = "start";
    private final String endNodeType = "end";
    private final String candidateNodeName = "candidates";
    private final String copyUserNode = "copyUser";
    private final String copyDeptNode = "copyDept";

    /**
     * 处理人角色前缀
     */
    public final static String CANDIDATE_ROLE_PREFIX = "role::";
    /**
     * 处理人 用户前缀
     */
    public final static String CANDIDATE_USER_PREFIX = "user::";

    /**
     * 处理人 部门前缀
     */
    public final static String CANDIDATE_DEPART_PREFIX = "depart::";


    /**
     * 表单字段节点名称
     */
    public final static String COLUMN_NAME = "tableColumn";

    /**
     * 抄送用户的节点名称
     */
    public final static String COPY_USER_ID = "tableCopyUser";

    public final static String COLUMN_ATTR_NAME = "columnId";

    public final static String COPY_ATTR_USER_ID = "copyUserId";

    public final static String COPY_ATTR_DEPT_ID = "copyDeptId";

    /**
     * 当前节点处理人：角色集合
     */
    private final String candidateRoles = "roles";
    /**
     * 当前节点处理人：用户集合
     */
    private final String candidateUsers = "users";

    /**
     * 当前节点处理人：部门集合集合
     */
    private final String candidateDeparts = "departs";

    private final String columnIds = "columnIds";





    /**
     * 完成节点bean
     */
    private String completeTaskListenerBeanName = "${completeTaskListener}";
    /**
     * 创建节点监听bean
     */
    private String createTaskListenerBeanName = "${createTaskListener}";


    public BpmnModelBuilder(String flowJson) {
        this.flowJson = JSONUtil.parseObj(flowJson);


        this.userTasks = new ArrayList<>();
        this.startEvent = new StartEvent();
        this.endEvent = new EndEvent();
        this.process = new Process();
        this.bpmnModel = new BpmnModel();
    }

    public BpmnModelBuilder setCompleteTaskListenerBeanName(String beanName) {
        this.completeTaskListenerBeanName = "${" + beanName + "}";
        return this;
    }

    public BpmnModelBuilder setCreateTaskListenerBeanName(String beanName) {
        this.createTaskListenerBeanName = "${" + beanName + "}";
        return this;
    }


    /**
     * 绘制节点
     * @return
     */
    public BpmnModelBuilder builderNode() {

        nodeList = flowJson.getJSONArray("nodeList");
        for(int i=0; i<nodeList.size(); i++) {
            JSONObject node = nodeList.getJSONObject(i);
            if(startNodeType.equals(node.getStr("type"))) {
                startEvent.setId(node.getStr("id"));
                startEvent.setName(node.getStr("name"));
            }

            if(endNodeType.equals(node.getStr("type"))) {
                endEvent=new EndEvent();
                endEvent.setId(node.getStr("id"));
                endEvent.setName(node.getStr("name"));
            }

            if(taskNodeType.equals(node.getStr("type"))) {
                UserTask userTask=new UserTask();
                JSONObject candidates = node.getJSONObject(this.candidateNodeName);
                JSONArray columnIds = node.getJSONArray(this.columnIds);
                //设置候选人集合
                Optional.ofNullable(candidates).map(this::transformCandidate).ifPresent(userTask::setCandidateUsers);

                //设置表单集合
                Optional.ofNullable(columnIds).map(this::transformColumns).ifPresent(columnId->{
                    columnId.stream().forEach(userTask::addExtensionElement);
                });


                userTask.setId(node.getStr("id"));
                userTask.setName(node.getStr("name"));
                FlowableListener completeTaskListener = new FlowableListener();

                completeTaskListener.setEvent(TaskListener.EVENTNAME_COMPLETE);
                //设置完成监听
                completeTaskListener.setImplementation(this.completeTaskListenerBeanName);
                completeTaskListener.setImplementationType("delegateExpression");
                FlowableListener createListener = new FlowableListener();
                createListener.setEvent(TaskListener.EVENTNAME_CREATE);
                //设置创建监听
                createListener.setImplementation(this.createTaskListenerBeanName);
                createListener.setImplementationType("delegateExpression");
                userTask.setTaskListeners(CollUtil.toList(completeTaskListener, createListener));
                userTasks.add(userTask);
            }
        }
        userTasks.get(0).addExtensionElement(setCopyUsers("1,3,4,5,6", "256，14"));
        return this;
    }

    /**
     * 绘制线段
     * @return
     */
    public BpmnModelBuilder builderLine() {
        lineList = flowJson.getJSONArray("lineList");
        //每个节点出去的线的集合
        this.out = new HashMap<>();
        this.in = new HashMap<>();
        for(int i=0; i<lineList.size(); i++) {
            SequenceFlow s2 = new SequenceFlow();
            JSONObject line = lineList.getJSONObject(i);
            //bpmn id要以字母开头
            String id = "line_"+ RandomUtil.randomString(6);
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
        return  this;
    }

    /**
     * 构造关联关系
     * @return
     */
    public BpmnModelBuilder builderRelation() {
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
        return this;
    }

    /**
     * 构造模型
     * @param processId
     * @return
     */
    public BpmnModelBuilder builder(String processId) {
        process.setId(processId);
        process.setName(processId);
        process.addFlowElement(startEvent);
        process.addFlowElement(endEvent);
        bpmnModel.addProcess(process);
        return this;
    }

    /**
     * 校验模型
     */
    public BpmnModel checkModel() {
        //验证bpmnModel 是否是正确的bpmn xml文件
        ProcessValidatorFactory processValidatorFactory=new ProcessValidatorFactory();
        ProcessValidator defaultProcessValidator = processValidatorFactory.createDefaultProcessValidator();
        //验证失败信息的封装ValidationError
        List<ValidationError> validate = defaultProcessValidator.validate(bpmnModel);
//        if(validate.size()>1) {
//            throw new RuntimeException("流程绘制有问题");
//        }
        return bpmnModel;
    }


    private List<String> transformCandidate(JSONObject candidates) {
        List<String> users = candidates.getJSONArray(this.candidateUsers).stream().map(user -> CANDIDATE_USER_PREFIX + user).collect(Collectors.toList());
        List<String> roles = candidates.getJSONArray(this.candidateRoles).stream().map(role -> CANDIDATE_ROLE_PREFIX + role).collect(Collectors.toList());
        List<String> departs = candidates.getJSONArray(this.candidateDeparts).stream().map(depart -> CANDIDATE_DEPART_PREFIX + depart).collect(Collectors.toList());
        List<String> rets = new ArrayList<>();
        Optional.ofNullable(users).ifPresent(rets::addAll);
        Optional.ofNullable(roles).ifPresent(rets::addAll);
        Optional.ofNullable(departs).ifPresent(rets::addAll);
        return rets;
    }

    private List<ExtensionElement> transformColumns(JSONArray columns) {
        List<ExtensionElement> extensionElements = columns
                .stream()
                .map(String::valueOf).map(column -> {
                    ExtensionAttribute extensionAttribute = new ExtensionAttribute();
                    extensionAttribute.setName(COLUMN_ATTR_NAME);
                    extensionAttribute.setValue(column);
                    ExtensionElement extensionElement = new ExtensionElement();
                    extensionElement.setName("flowable:"+COLUMN_NAME);
                    extensionElement.addAttribute(extensionAttribute);
                    return extensionElement;
                }).collect(Collectors.toList());
        return extensionElements;
    }

    private ExtensionElement setCopyUsers(String users, String depts){
        ExtensionAttribute user = new ExtensionAttribute();
        user.setName(COPY_ATTR_USER_ID);
        user.setValue(users);
        ExtensionAttribute dept = new ExtensionAttribute();
        dept.setName(COPY_ATTR_DEPT_ID);
        dept.setValue(depts);
        ExtensionElement extensionElement = new ExtensionElement();
        extensionElement.setName("flowable:"+COPY_USER_ID);
        extensionElement.addAttribute(user);
        extensionElement.addAttribute(dept);
        return extensionElement;
    }


}
