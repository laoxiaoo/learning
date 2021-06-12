package com.xiao.asm;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;


/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ClassPrinter.java
 * @Description
 * @createTime 2021年06月11日 18:08:00
 */
public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(Opcodes.ASM4);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println("版本:"+version);
        System.out.println("名字："+name);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println("读取到字段："+name);
        return super.visitField(access, name, desc, signature, value);
    }

    public static void main(String[] args) throws Exception {
        ClassPrinter printer = new ClassPrinter();
        ClassReader reader = new ClassReader("java.lang.String");
        reader.accept(printer, 0);
    }
}
