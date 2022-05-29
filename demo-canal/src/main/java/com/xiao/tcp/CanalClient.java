package com.xiao.tcp;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.ByteString;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Canal TCP模式
 * @author xiao ji hao
 * @create 2022年01月01日 16:01:00
 */

public class CanalClient {

    public static void main(String[] args) {
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("192.168.1.134", 11111),
                "example", "", "");
        while (true) {
            connector.connect();
            //订阅数据库
            connector.subscribe("my_test.*");
            //一次获取100的数据，如果数据不够100则全部获取
            Message message = connector.get(100);
            List<CanalEntry.Entry> entries = message.getEntries();
            if(entries.size() <= 0) {
                try {
                    //没有数据，则休息一会
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                entries.forEach(CanalClient::parseEntry);
            }
        }
    }


    public static void parseEntry(CanalEntry.Entry entry){
        try {
            //获取表名
            System.out.println(entry.getHeader().getTableName());
            //entryType
            CanalEntry.EntryType entryType = entry.getEntryType();
            if(CanalEntry.EntryType.ROWDATA == entryType) {
                //如果是row类型
                //获取数据
                ByteString storeValue = entry.getStoreValue();
                //反序列化
                CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);
                //获取事件类型：新增、修改等
                CanalEntry.EventType eventType = rowChange.getEventType();
                CanalData canalData = new CanalData();
                canalData.setEventType(eventType);
                //获取数据集合
                List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
                for(CanalEntry.RowData rowData : rowDatasList) {
                    Map before = new HashMap<>();
                    Map after = new HashMap<>();
                    //更新前数据
                    List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
                    for(CanalEntry.Column column : beforeColumnsList) {
                        before.put(column.getName(), column.getValue());
                    }
                    //更新后数据
                    List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
                    for (CanalEntry.Column column :afterColumnsList) {
                        after.put(column.getName(), column.getValue());
                    }

                   /* System.out.println("更新前:" + beforeColumnsList.toString());
                    System.out.println("更新后：" + afterColumnsList.toString());*/

                    canalData.getAfter().add(after);
                    canalData.getBefore().add(before);
                }
                System.out.println(canalData);
            }
        } catch (Exception e ) {

        }
    }

}
