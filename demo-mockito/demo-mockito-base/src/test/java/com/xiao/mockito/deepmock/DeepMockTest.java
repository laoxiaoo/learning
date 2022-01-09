package com.xiao.mockito.deepmock;

import com.xiao.service.DeepService1;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * 当 service1返回service2时
 * 按照常理，是返回空的，因为service2没有mock
 * 但是此时如果规则变成deep，则调用service1时，返回的service2也会是一个mock类
 *
 * @author xiao ji hao
 * @create 2022年01月09日 16:46:00
 */
public class DeepMockTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DeepService1 deepService1;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        System.out.println(deepService1.getDeep2());;
    }

}
