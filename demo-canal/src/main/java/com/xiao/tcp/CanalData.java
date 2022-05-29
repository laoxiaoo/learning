package com.xiao.tcp;

import com.alibaba.otter.canal.protocol.CanalEntry;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lao xiao
 * @create 2022年05月28日 09:22:00
 */
@Getter
@Setter
@ToString
public class CanalData {

    private CanalEntry.EventType eventType;

    List<Map > before = new ArrayList<>();
    List<Map > after = new ArrayList<>();

}
