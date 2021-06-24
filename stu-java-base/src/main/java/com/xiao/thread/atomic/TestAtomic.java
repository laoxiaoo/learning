package com.xiao.thread.atomic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.concurrent.atomic.*;

import static com.xiao.thread.lock.TestPark.sleep;

/**
 * @author xiao ji hao
 * @create 2021年06月22日 10:40:00
 */
@Slf4j
public class TestAtomic {

    public static void integerAtomic() {
        AtomicInteger integer = new AtomicInteger(5);
        //相当于++i  先计算后获取
        integer.incrementAndGet();
        //相当于 i++
        integer.getAndIncrement();
        //先获取，再添加5
        integer.getAndAdd(5);

        int i = integer.updateAndGet(x -> x * 10);
        System.out.println(i);
    }

    public static void main(String[] args) {
        //integerAtomic();
        /*referenceAtomic();
        testABA();
        testABA1();
        testABA2();*/
        //testField();
        testCumulative();
    }

    public static void referenceAtomic() {
        AtomicReference<BigDecimal> ref = new AtomicReference<>(new BigDecimal("100"));
        while (true) {
            BigDecimal expect = ref.get();
            BigDecimal update = expect.subtract(new BigDecimal(5));
            if(ref.compareAndSet(expect, update)) {
                break;
            }
        }
        log.debug("获取减去的值：{}", ref.get());
    }


    public static void testABA() {
        AtomicReference<String> ref = new AtomicReference<>("A");
        String a = ref.get();
        new Thread(() -> {
            ref.compareAndSet("A", "B");
            ref.compareAndSet("B", "A");
        }).start();
        sleep(1000);
        log.debug("A->C : {}", ref.compareAndSet(a, "C"));
    }


    public static void testABA1() {
        AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);
        String a = ref.getReference();
        int stamp = ref.getStamp();
        new Thread(() -> {
            ref.compareAndSet("A", "B",stamp,  stamp+1);
            ref.compareAndSet("B", "A", stamp, stamp+1);
        }).start();
        sleep(1000);
        log.debug("A->C : {}", ref.compareAndSet(a, "C", stamp, stamp+1));
    }

    public static void testABA2() {
        AtomicMarkableReference<String> ref = new AtomicMarkableReference<>("A", true);
        String a = ref.getReference();
        new Thread(() -> {
            ref.compareAndSet("A", "B", true, false);
        }).start();
        sleep(1000);
        log.debug("A->C : {}", ref.compareAndSet(a, "C", true, false));
    }

    public static void testField() {
        //修改的类的类型， 字段类型，  字段名称
        AtomicReferenceFieldUpdater<Student, String> fieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");
        Student student = new Student();
        fieldUpdater.compareAndSet(student, null, "张三");
        log.debug("修改后的值： {}", student);
    }

    @ToString
    static class Student {
        public volatile String name;
    }


    public static void testCumulative() {
        LongAdder adder = new LongAdder();
        adder.increment();
        log.debug("获取结果：{}", adder.longValue());
    }

}
