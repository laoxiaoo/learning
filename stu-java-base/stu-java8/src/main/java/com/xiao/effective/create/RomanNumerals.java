package com.xiao.effective.create;

import java.util.regex.Pattern;

/**
 * 对字符串正则表达式的匹配校验
 *
 * @author xiao ji hao
 * @create 2021年08月10日 08:06:00
 */
public class RomanNumerals {

     private static Pattern pattern = Pattern.compile("(-| +|^)M{0,9}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})( +|$)");


    /**
     * 采用每一次都重复调用Pattern.compile(regex)方法，这样的效率并不是很高
     * @param number
     * @return
     */
    public Boolean isRomanNumerals(String number) {
        return number.matches("(-| +|^)M{0,9}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})( +|$)");
    }

    /**
     * 采用静态方法的模式，这样不会重复的产生对象，效率提升
     * @param number
     * @return
     */
    public Boolean isRomanNumerals2(String number) {
        return pattern.matcher(number).matches();
    }

}
