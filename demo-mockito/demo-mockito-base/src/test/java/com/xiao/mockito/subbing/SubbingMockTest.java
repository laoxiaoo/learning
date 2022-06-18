package com.xiao.mockito.subbing;

import com.xiao.service.DeepService1;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

/**
 * @author xiao ji hao
 * @create 2022年01月09日 17:18:00
 */
public class SubbingMockTest {

    @Mock
    private List list;

    @Mock
    private DeepService1 deepService1;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        //当调用此方法时，返回one
        //Mockito.when(list.get(0)).thenReturn("one");
        //等价于doReturn方式
        Mockito.doReturn("two").when(list).get(1);
        System.out.println(list.get(1));
    }

    /**
     * 多次调用返回多种结果
     */
    @Test
    public void iterateSub() {
        Mockito.when(list.get(0)).thenReturn(0).thenReturn(1).thenReturn(2);
        System.out.println(list.get(0));
        System.out.println(list.get(0));
        System.out.println(list.get(0));
    }

    /**
     * 给mock类传入任意的参数，调用的时候返回匿名函数的返回值
     */
    @Test
    public void subbingAnswer() {
        Mockito.when(list.get(Mockito.anyInt())).thenAnswer(var -> {
            //获取参数信息
            Object argument = var.getArguments()[0];
            return String.valueOf(argument);
        });

        System.out.println(list.get(0));
    }


    /**
     * 调用真正的方法，而不调用mock的代理方法
     * </br>
     *
     */
    @Test
    public void subbingRealMethod() {
        Mockito.when(deepService1.getDeep2()).thenCallRealMethod();
        System.out.println(deepService1.getDeep2());
    }

}
