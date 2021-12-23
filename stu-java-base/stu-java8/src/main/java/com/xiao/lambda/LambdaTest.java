package com.xiao.lambda;

import java.util.Collections;
import java.util.List;

/**
 * @author xiao ji hao
 * @create 2021年10月16日 18:45:00
 */
public class LambdaTest {

    public static void foreach() {
        List<String> list = Collections.singletonList("aaa");
        list.forEach(var -> {
            System.out.println(var);
        });
    }

}
