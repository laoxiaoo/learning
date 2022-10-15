package com.xiao.dualDataSource.dao;

import com.xiao.dualDataSource.config.UserStateEnum;
import com.xiao.dualDataSource.dao1.WriterStudentDao;
import com.xiao.dualDataSource.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author xiao ji hao
 * @create 2021年08月18日 22:35:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DaoTest {
    @Autowired
    WriterStudentDao writerStudentDao;

    @Test
    public void list() {
        List<Student> list = writerStudentDao.list();
        log.debug("list: {}",list);
    }

    @Test
    public void insert() {
        Student student = new Student();
        student.setStatus(UserStateEnum.FAULT);
        student.setName("李四");
        writerStudentDao.insertEntity(student);
    }



}
