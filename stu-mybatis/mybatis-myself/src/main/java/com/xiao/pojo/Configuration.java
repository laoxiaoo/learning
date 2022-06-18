package com.xiao.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 配置信息的转载类
 * @author lao xiao
 * @create 2022年05月22日 21:59:00
 */
@Getter
@Setter
public class Configuration {

    /**
     * 数据源配置
     */
    private DataSource dataSource;

    /**
     * sql语句配置的集合
     *
     * <namespace+id, sql配置>
     */
    private Map<String, MapperStatement> mapperStatementMap = new HashMap<>();

}
