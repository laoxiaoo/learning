package com.xiao.jvm.classLoader;

import sun.misc.Launcher;

import java.net.URL;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 测试获取各种类加载器
 *
 * @author Malone Xiao
 * @createTime 2021年04月30日 23:16:00
 */
public class TestClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        System.out.println(classLoader);
        TestClassLoader.class.getClassLoader();

        System.out.println(Thread.currentThread().getContextClassLoader());;
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        Stream.of(urLs).forEach(url -> System.out.println(url.toExternalForm()));

        String property = System.getProperty("java.ext.dirs");
        Arrays.stream(property.split(";")).forEach(System.out::println);

        System.out.println(Class.forName("java.lang.String").getClassLoader());

    }
}
