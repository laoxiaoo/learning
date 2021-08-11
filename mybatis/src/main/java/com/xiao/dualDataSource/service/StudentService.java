package com.xiao.dualDataSource.service;

import com.xiao.dualDataSource.dao1.WriterStudentDao;
import com.xiao.dualDataSource.dao2.ReadStudentDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiao ji hao
 * @create 2021年08月11日 22:43:00
 */
@Service
@Slf4j
public class StudentService {

    @Autowired
    private WriterStudentDao writerStudentDao;

    @Autowired
    private ReadStudentDao readStudentDao;

    @Transactional(rollbackFor = Exception.class)
    public void save() {
        writerStudentDao.insert();
        List student = readStudentDao.getStudent();
        log.debug("student: {}", student.toArray());
        readStudentDao.insert();

        throw new RuntimeException("1111");
    }

}
