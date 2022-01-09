package com.xiao.mockito;

import com.xiao.dao.AccountDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author xiao ji hao
 * @create 2022年01月09日 15:44:00
 */
public class TestMockAnnotation {

    @Mock
    private AccountDao accountDao;

    @Before
    public void init () {
        //如果使用注解，则需要初始化
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        System.out.println(accountDao.getAccount());
    }

}
