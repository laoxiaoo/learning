package com.xiao.mockito;

import com.xiao.controller.LoginController;
import com.xiao.dao.AccountDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

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

}
