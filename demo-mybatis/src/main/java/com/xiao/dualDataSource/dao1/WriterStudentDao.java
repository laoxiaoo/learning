package com.xiao.dualDataSource.dao1;

import com.xiao.dualDataSource.entity.Student;

import java.util.List;

/**
 * @author xiao ji hao
 * @create 2021年08月11日 20:38:00
 */
public interface WriterStudentDao {

    void insert();

    void insertEntity(Student student);

    List<Student> list();
}
