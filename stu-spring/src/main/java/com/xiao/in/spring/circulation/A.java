package com.xiao.in.spring.circulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author xiao ji hao
 * @create 2021年06月29日 17:07:00
 */
@Service
public class A {

    @Autowired
    private B b;

}
