package com.xiao;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: learning
 * @description: TODO
 * @author: lonely xiao
 * @create: 2020-10-15 23:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddFlow {

    //private final String flow = "{\"name\":\"流程B\",\"nodeList\":[{\"id\":\"j64q7773on\",\"name\":\"数据接入\",\"type\":\"timer\",\"left\":\"242px\",\"top\":\"201px\",\"ico\":\"el-icon-time\",\"state\":\"success\"},{\"id\":\"om6o6k1sci\",\"name\":\"流程结束\",\"type\":\"end\",\"left\":\"835px\",\"top\":\"244px\",\"ico\":\"el-icon-caret-right\",\"state\":\"success\"},{\"id\":\"fzdij1skj\",\"name\":\"数据接入1\",\"type\":\"timer\",\"left\":\"457px\",\"top\":\"173px\",\"ico\":\"el-icon-time\",\"state\":\"success\"},{\"id\":\"iq2vj993k\",\"name\":\"数据接入2\",\"type\":\"timer\",\"left\":\"493px\",\"top\":\"308px\",\"ico\":\"el-icon-time\",\"state\":\"success\"},{\"id\":\"f5jbsmgks\",\"name\":\"接口调用\",\"type\":\"task\",\"left\":\"40px\",\"top\":\"203px\",\"ico\":\"el-icon-odometer\",\"state\":\"success\"}],\"lineList\":[{\"from\":\"f5jbsmgks\",\"to\":\"j64q7773on\"},{\"from\":\"j64q7773on\",\"to\":\"fzdij1skj\"},{\"from\":\"fzdij1skj\",\"to\":\"iq2vj993k\"},{\"from\":\"iq2vj993k\",\"to\":\"om6o6k1sci\"}]}";

    private final String flow = "{\"name\":\"流程\",\"nodeList\":[{\"id\":\"nodeStart\",\"name\":\"流程发起节点\",\"type\":\"task\",\"left\":\"84px\",\"top\":\"248px\",\"ico\":\"icon-flow-start green\"},{\"id\":\"nodeEnd\",\"name\":\"流程结束\",\"type\":\"end\",\"left\":\"1139px\",\"top\":\"258px\",\"ico\":\"icon-flow-end red\"},{\"id\":\"hsh5hukxxm\",\"name\":\"流程节点2\",\"type\":\"timer\",\"left\":\"492px\",\"top\":\"249px\",\"ico\":\"icon-flow-node green\",\"state\":\"success\"},{\"id\":\"z5mc3ikea\",\"name\":\"流程节点\",\"type\":\"timer\",\"left\":\"684px\",\"top\":\"157px\",\"ico\":\"icon-flow-node green\",\"state\":\"success\"},{\"id\":\"pme8oslt4u\",\"name\":\"流程节点1\",\"type\":\"timer\",\"left\":\"706px\",\"top\":\"344px\",\"ico\":\"icon-flow-node green\",\"state\":\"success\"}],\"lineList\":[{\"from\":\"nodeStart\",\"to\":\"hsh5hukxxm\"},{\"from\":\"hsh5hukxxm\",\"to\":\"z5mc3ikea\"},{\"from\":\"hsh5hukxxm\",\"to\":\"pme8oslt4u\"},{\"from\":\"z5mc3ikea\",\"to\":\"nodeEnd\"},{\"from\":\"pme8oslt4u\",\"to\":\"nodeEnd\"}]}";


    //流程存储组件
    @Autowired
    private RepositoryService repositoryService;
    //运行时服务组件
    @Autowired
    private RuntimeService runtimeService;
    //流程任务组件
    @Autowired
    private TaskService taskService;


    String processId= "process3";

    @Test
    public void test() {
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
            if("task".equals(node.getStr("type"))) {
                startEvent=new StartEvent();

                startEvent.setId(node.getStr("id"));
                startEvent.setName(node.getStr("name"));
            }

            if("end".equals(node.getStr("type"))) {
                endEvent=new EndEvent();
                endEvent.setId(node.getStr("id"));
                endEvent.setName(node.getStr("name"));
            }

            if("timer".equals(node.getStr("type"))) {
                UserTask userTask=new UserTask();
                userTask.setId(node.getStr("id"));
                userTask.setName(node.getStr("name"));
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

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processId);
        Task task = taskService.createTaskQuery().singleResult();

        System.out.println("当前任务：" + task.getName());
        Map vars = new HashMap<>();
        vars.put("days", 7);
        taskService.complete(task.getId(), vars);


        Task task2 = taskService.createTaskQuery().singleResult();
        System.out.println("当前任务：" + task2.getName());


    }
}