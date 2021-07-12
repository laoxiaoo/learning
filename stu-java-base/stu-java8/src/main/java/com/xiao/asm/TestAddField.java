package com.xiao.asm;

import cn.hutool.core.io.FileUtil;
import jdk.internal.org.objectweb.asm.*;

import java.util.HashMap;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName TestAddField.java
 * @Description
 * @createTime 2021年06月11日 19:48:00
 */
public class TestAddField {

    public static void main(String[] args) {
        byte[] bytes = FileUtil.readBytes(ClassLoader.getSystemClassLoader()
                .getResource("")
                .getPath() + "/com/xiao/asm/MyTest.class");

        ClassWriter writer = new ClassWriter(0);
        ClassVisitor visitor = new ClassVisitor(Opcodes.ASM4, writer) {
            @Override
            public void visitEnd() {
                FieldVisitor field = cv.visitField(Opcodes.ACC_PUBLIC, "str", "Ljava/lang/String;", null, null);
                field.visitEnd();
            }
        };
        ClassReader reader = new ClassReader(bytes);
        reader.accept(visitor, 0);
        byte[] byteArray = writer.toByteArray();
        FileUtil.writeBytes(byteArray, "com/xiao/asm/MyTest_0.class");
    }

}
