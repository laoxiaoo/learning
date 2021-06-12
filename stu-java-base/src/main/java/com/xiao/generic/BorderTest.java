package com.xiao.generic;

/**
 * @author 肖杰
 * @ClassName BorderTest.java
 * @Description 泛型擦写边界
 * @createTime 2021年04月08日 18:00:00
 */
public class BorderTest {


    public static void main(String[] args) {
        Border2<String> border2 = new Border2<>();
        String a = "aaa";
        border2.setT(a);
    }

}
class Border1<T extends Integer> {
}

class Border2<T extends String> {
    T t;
    public T setT(T t){
        this.t = t;
        return this.t;
    }
}