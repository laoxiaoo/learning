package com.xiao.dao;

import com.xiao.pojo.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @author 肖杰
 * @version 1.2.8
 * @ClassName StudentMapper.java
 * @Description 学生接口
 * @createTime 2020年11月10日 08:50:00
 */
public interface StudentMapper {

    /**
     * 查询学生
     * @param id
     * @return
     */
    Student selectStudent(@Param("id") Long id);
}
