package com.xiao.binlog;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;

import java.io.IOException;

/**
 * 测试mysql 的binlog的监听
 * <br/>
 * binlog可以用来从库的监听某个数据库的更改，<br/>然后将监听的数据串行化的进入中间件然后添加进入缓存中
 *
 * 
 * @author xiao ji hao
 * @create 2021年07月09日 20:26:00
 */
public class Test {


    public static void main(String[] args) {
        BinaryLogClient client = new BinaryLogClient("192.168.1.134", 3306, "root", "123456");
        EventDeserializer eventDeserializer = new EventDeserializer();
        client.setEventDeserializer(eventDeserializer);
        client.registerEventListener(new BinaryLogClient.EventListener() {
            @Override
            public void onEvent(com.github.shyiko.mysql.binlog.event.Event event) {
                EventHeader header = event.getHeader();
                
                EventType eventType = header.getEventType();
                System.out.println("监听的事件类型:" + eventType);

                if (EventType.isWrite(eventType)) {
                    //获取事件体
                    WriteRowsEventData data = event.getData();
                    System.out.println(JSONUtil.toJsonStr(data));
                } else if (EventType.isUpdate(eventType)) {
                    UpdateRowsEventData data = event.getData();
                    System.out.println(JSONUtil.toJsonStr(data));
                } else if (EventType.isDelete(eventType)) {
                    DeleteRowsEventData data = event.getData();
                    System.out.println(JSONUtil.toJsonStr(data));
                }
            }
        });
        try {
            client.connect();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
