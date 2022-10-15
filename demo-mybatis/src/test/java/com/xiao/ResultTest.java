package com.xiao;

import com.xiao.base.BaseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 结果集处理测试方法
 *
 * @author xiao ji hao
 */
@Slf4j
public class ResultTest extends BaseCode {

    @Test
    public void testContext() {
        List list = new ArrayList();

        ResultHandler handler = new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
                if(resultContext.getResultCount() > 2) {
                    resultContext.stop();
                }
                list.add(resultContext.getResultObject());
            }
        };

        sqlSession.select("com.xiao.dao.StudentMapper.selectAll", handler);
        log.debug("获取到结果集大小：{}", list.size());

    }


}
