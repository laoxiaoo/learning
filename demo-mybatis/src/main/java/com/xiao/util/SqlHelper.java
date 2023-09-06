package com.xiao.util;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author xiao jie
 * @date 2023-07-21 09:42
 */
public class SqlHelper {

    /**
     *
     * @param sqlMethod
     * @param tClass
     * @param <T>
     * @return
     */
    protected static  <T> String sqlStatement(SqlMethod sqlMethod, Class<T> tClass) {
        return com.baomidou.mybatisplus.extension.toolkit.SqlHelper.table(tClass).getSqlStatement(sqlMethod.getMethod());
    }

    /**
     * 批量执行
     * @param fun
     * @param tClass
     * @param <T>
     */
    protected static <T> void executeBatch(Consumer<SqlSession> fun, Class<T> tClass) {
        com.baomidou.mybatisplus.extension.toolkit.SqlHelper.clearCache(tClass);
        SqlSessionFactory sqlSessionFactory = com.baomidou.mybatisplus.extension.toolkit.SqlHelper.sqlSessionFactory(tClass);
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            fun.accept(sqlSession);
            sqlSession.commit();
        } catch (Throwable t) {
            sqlSession.rollback();
            Throwable unwrapped = ExceptionUtil.unwrapThrowable(t);
            if (unwrapped instanceof RuntimeException) {
                MyBatisExceptionTranslator myBatisExceptionTranslator
                        = new MyBatisExceptionTranslator(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(), true);
                throw Objects.requireNonNull(myBatisExceptionTranslator.translateExceptionIfPossible((RuntimeException) unwrapped));
            }
            throw ExceptionUtils.mpe(unwrapped);
        } finally {
            sqlSession.close();
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public static <T> boolean saveBatch(Collection<T> entityList, int batchSize, Class<T> tClass) {
        String sqlStatement = sqlStatement(SqlMethod.INSERT_ONE, tClass);
        int size = entityList.size();
        executeBatch(sqlSession -> {
            int i = 1;
            for (T entity : entityList) {
                sqlSession.insert(sqlStatement, entity);
                if ((i % batchSize == 0) || i == size) {
                    sqlSession.flushStatements();
                }
                i++;
            }
        }, tClass);
        return true;
    }


    @Transactional(rollbackFor = Exception.class)
    public static <T> boolean saveBatch(Collection<T> entityList,Class<T> tClass) {
        return saveBatch(entityList, 1000, tClass);
    }

}
