package com.xiao.dualDataSource.controller;

import cn.hutool.core.collection.CollUtil;
import com.xiao.dualDataSource.dao1.WriterStudentDao;
import com.xiao.dualDataSource.dao2.ReadStudentDao;
import com.xiao.dualDataSource.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiao ji hao
 * @create 2021年08月11日 20:36:00
 */
@RestController
@Slf4j
public class StudentController {

    @Autowired
    private WriterStudentDao writerStudentDao;

    @Autowired
    private ReadStudentDao readStudentDao;

    @Autowired
    private StudentService studentService;


    @PostMapping("/save")
    public void save() {
        studentService.save();
    }


    @GetMapping("/get")
    public void get() {
        List student = readStudentDao.getStudent();
        log.debug("students: {}", student.toArray());
    }

}
