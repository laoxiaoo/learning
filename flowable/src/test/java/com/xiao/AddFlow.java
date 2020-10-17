package com.xiao;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    private final String flow = "{\"name\":\"流程B\",\"nodeList\":[{\"id\":\"j64q7773on\",\"name\":\"数据接入\",\"type\":\"timer\",\"left\":\"242px\",\"top\":\"201px\",\"ico\":\"el-icon-time\",\"state\":\"success\"},{\"id\":\"om6o6k1sci\",\"name\":\"流程结束\",\"type\":\"end\",\"left\":\"835px\",\"top\":\"244px\",\"ico\":\"el-icon-caret-right\",\"state\":\"success\"},{\"id\":\"fzdij1skj\",\"name\":\"数据接入1\",\"type\":\"timer\",\"left\":\"457px\",\"top\":\"173px\",\"ico\":\"el-icon-time\",\"state\":\"success\"},{\"id\":\"iq2vj993k\",\"name\":\"数据接入2\",\"type\":\"timer\",\"left\":\"493px\",\"top\":\"308px\",\"ico\":\"el-icon-time\",\"state\":\"success\"},{\"id\":\"f5jbsmgks\",\"name\":\"接口调用\",\"type\":\"task\",\"left\":\"40px\",\"top\":\"203px\",\"ico\":\"el-icon-odometer\",\"state\":\"success\"}],\"lineList\":[{\"from\":\"f5jbsmgks\",\"to\":\"j64q7773on\"},{\"from\":\"j64q7773on\",\"to\":\"fzdij1skj\"},{\"from\":\"fzdij1skj\",\"to\":\"iq2vj993k\"},{\"from\":\"iq2vj993k\",\"to\":\"om6o6k1sci\"}]}";


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
        Map<String, List<SequenceFlow>> out = new HashMap<>();
        Map<String, List<SequenceFlow>> in = new HashMap<>();
        for(int i=0; i<lineList.size(); i++) {
            SequenceFlow s2 = new SequenceFlow();
            JSONObject line = lineList.getJSONObject(i);
            String id = RandomUtil.randomString(6);
            s2.setId(id);
            s2.setName(id);
            s2.setSourceRef(line.getStr("from"));
            s2.setTargetRef(line.getStr("to"));

            out.computeIfAbsent(line.getStr("from"), k -> new ArrayList<>()).add(s2);
            in.computeIfAbsent(line.getStr("to"), k -> new ArrayList<>()).add(s2);
            process.addFlowElement(s2);
        }

        //设置节点线段

        startEvent.setOutgoingFlows(CollUtil.toList(out.get(startEvent.getId()).get(0)));
        endEvent.setIncomingFlows(in.get(startEvent.getId()));
        userTasks.stream().forEach(f -> {
            //设置进来的线段
            f.setIncomingFlows(in.get(f.getId()));
            //设置出去的先
            f.setOutgoingFlows(CollUtil.toList(out.get(f.getId()).get(0)));
            process.addFlowElement(f);
        });



        process.setId(processId);
        process.setName(processId);
        process.addFlowElement(startEvent);
        process.addFlowElement(endEvent);

        System.out.println(userTasks);
    }
}