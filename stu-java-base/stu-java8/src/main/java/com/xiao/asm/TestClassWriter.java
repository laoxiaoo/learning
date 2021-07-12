package com.xiao.asm;

import jdk.internal.org.objectweb.asm.ClassWriter;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ClassWriter.java
 * @Description
 * @createTime 2021年06月11日 18:31:00
 */
public class TestClassWriter {

    public static void main(String[] args) {
        ClassWriter writer = new ClassWriter(0);
        //类基本信息
        writer.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "pkg/Comparable", null, "java/lang/Object",
                null);
        //添加字段
        writer.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        writer.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        writer.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        //添加方法
        writer.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        writer.visitEnd();
        byte[] b = writer.toByteArray();
        //使用自定义类加载器加载
        MyClassLoader loader = new MyClassLoader();
        Class newClass = loader.defineClass("pkg.Comparable", b);
        System.out.println(newClass.getMethods()[0].getName());
    }

}
