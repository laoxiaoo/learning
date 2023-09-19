package com.xiao.copy;

/**
 * @author xiao jie
 * @date 2023-09-11 09:51
 */
public class BeanCopyUtilProxy {

    public static <K, T> T copy(K source, Class<T> target) {
        System.out.println("===========开始浅拷贝");
        long startTime = System.nanoTime();
        T t = BeanCopyUtil.copy(source, target);
        long t1 = (System.nanoTime() - startTime)/1000000;
        System.out.println("浅拷贝结束， 执行时间："+t1);
        return t;
    }

    public static <K, T> T copyDeep(K source, Class<T> target) {
        System.out.println("===========开始深拷贝");
        long startTime = System.nanoTime();
        T t = BeanCopyUtil.copyDeep(source, target);
        long t1 = (System.nanoTime() - startTime)/1000000;
        System.out.println("深拷贝结束， 执行时间："+t1);
        return t;
    }

}
