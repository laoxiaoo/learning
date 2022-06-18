package com.xiao.sqlSession;

import com.xiao.pojo.Configuration;
import com.xiao.pojo.MapperStatement;
import com.xiao.util.GenericTokenParser;
import com.xiao.util.ParameterMapping;
import com.xiao.util.ParameterMappingTokenHandler;
import com.xiao.util.TokenHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @author lao xiao
 * @create 2022年05月29日 21:11:00
 */
public class SimpleExecutor implements Executor {

    @Override
    public Object query(Configuration configuration, MapperStatement mapperStatement, Object... params) throws Exception {
        Connection connection = configuration.getDataSource().getConnection();
        //转换sql
        //将 select* from xxx where id =#{id} =>select* from xxx where id = ?
        BoundSql boundSql = getBoundSql(mapperStatement.getSql());
        //jdbc的执行过程
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getParseSql());
        List<ParameterMapping> list = boundSql.getList();
        for(ParameterMapping mapping : list) {
            String content = mapping.getContent();
        }
        return null;
    }

    /**
     * 解析占位符的sql
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理器类
        //将 #{xxx}  转换成 ?
        ParameterMappingTokenHandler tokenHandler =  new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        //解析出来后的sql
        String parseSql = genericTokenParser.parse(sql);
        //xxx的内容 即#{}中的内容
        List<ParameterMapping> list = tokenHandler.getParameterMappings();
        return new BoundSql(parseSql, list);
    }
}
