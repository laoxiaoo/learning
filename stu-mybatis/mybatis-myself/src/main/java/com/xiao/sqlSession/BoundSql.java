package com.xiao.sqlSession;

import com.xiao.util.ParameterMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lao xiao
 * @create 2022年05月29日 21:15:00
 */
@Getter
@Setter
@AllArgsConstructor
public class BoundSql {

    private String parseSql;

    private List<ParameterMapping> list;
}
