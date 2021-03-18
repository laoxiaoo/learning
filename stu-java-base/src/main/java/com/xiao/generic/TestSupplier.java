package com.xiao.generic;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName TestSupplier.java
 * @Description
 * @createTime 2021年03月01日 21:16:00
 */
public class TestSupplier {

    public static void main(String[] args) {

    }


    public class SuperIterable<T> implements Iterable<T> {


        @Override
        public Iterator<T> iterator() {

            return null;
        }
    }

}
