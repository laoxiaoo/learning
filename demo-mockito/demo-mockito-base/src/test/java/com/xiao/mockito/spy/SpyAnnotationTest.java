package com.xiao.mockito.spy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiao ji hao
 * @create 2022年01月09日 20:47:00
 */
public class SpyAnnotationTest {

    @Spy
    private List list = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


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
