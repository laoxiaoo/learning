package com.xiao.dao;

import com.xiao.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("select * from student")
    Student select(Student student);

    /**
     * 查询所有的学生信息
     * @return
     */
    List<Student> selectAll();
}
