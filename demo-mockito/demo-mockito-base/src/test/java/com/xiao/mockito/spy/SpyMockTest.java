package com.xiao.mockito.spy;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiao ji hao
 * @create 2022年01月09日 20:32:00
 */
public class SpyMockTest {

    /**
     * 采用了spy后，只有通过了subbing的方法才会调用mock方法
     */
    @Test
    public void testSpy() {
        List realList = new ArrayList();
        List list = Mockito.spy(realList);
        list.add("test1");
        list.add("test2");
        System.out.println(list.get(0));

        //但
        Mockito.when(list.get(1)).thenReturn("spy1");
        System.out.println(list.get(1));
    }

}
