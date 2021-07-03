package com.xiao.clazz;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author malone xiao
 * @ClassName TestInternalClass.java
 * @Description
 * @createTime 2021年05月13日 19:59:00
 */
@Slf4j
public class TestInternalClass {

    public static void main(String[] args) throws Exception {
        /*InternalClass.A a = new InternalClass().new A();

        InternalClass.B b = new InternalClass.B();*/

        method();

    }

    public static void method() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //调用有参的构造方法生成类实例
        Constructor<InternalClass> constructor
                = InternalClass.class.getDeclaredConstructor(new Class[] {Integer.class});
        InternalClass instance = constructor.newInstance(new Integer[] {1});
        log.debug("instance {}", instance);
    }

}
