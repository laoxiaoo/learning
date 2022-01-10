package com.xiao.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author xiao ji hao
 * @create 2022年01月09日 22:23:00
 */
@SpringBootTest
@AutoConfigureMockMvc //开启@AutoConfigureMockMvc注解
@RunWith(SpringRunner.class)
public class TestSpringMvc {


    @Autowired
    private MockMvc mockMvc; //注入MockMvc对象，我们可以通过此对象来模拟HttpServletRequest对象

    /**
     *
     * @throws Exception
     */
    @Test
    public void testTestController() throws Exception {
        //发出get请求.perform(get("/test?name=dd"))
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/test?name=dd"))
                .andExpect(status().isOk())
                //.andExpect(content().string("hello dd"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}
