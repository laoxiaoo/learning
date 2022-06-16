package com.xiao.mockito;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import com.xiao.controller.LoginController;
import com.xiao.dao.AccountDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author xiao ji hao
 * @create 2022年01月09日 10:27:00
 */
@RunWith(MockitoJUnitRunner.class)
public class TestFirst {

    private AccountDao accountDao;

    private HttpServletRequest request;

    private LoginController loginController;

    @Before
    public void before() {
        //提供两个mock对象
        this.accountDao = Mockito.mock(AccountDao.class);
        this.request = Mockito.mock(HttpServletRequest.class);
        loginController = new LoginController();
    }


    @Test
    public void test() {
        Mockito.when(accountDao.getAccount()).thenReturn("laoxioa");
        //当调用getparameter时返回的值
        Mockito.when(request.getParameter("username")).thenReturn("admin");
        String login = loginController.login(request);
        System.out.println(login);
        System.out.println(accountDao.getAccount());
    }

    @Test
    public void test1() {
        List<java.lang.String> list = FileUtil.readUtf8Lines(this.getClass().getClassLoader().getResource("1.txt"));
        int j = 1;
        int i = 1;
        StringBuilder sb = new StringBuilder("del ");
        for(java.lang.String l : list) {
            if(j==100) {
                FileWriter writer = new FileWriter("d://del//del-"+i+".txt");
                writer.write(sb.toString());
                j=1;
                sb = new StringBuilder("del ");
                i++;
            }
            sb.append(l).append(" ");
            j++;
        }
    }

    @Test
    public void test2() {
        FileWriter writer = new FileWriter("d://test.properties");
        writer.write("test");
    }

}
