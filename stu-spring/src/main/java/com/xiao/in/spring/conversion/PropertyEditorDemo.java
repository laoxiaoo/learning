package com.xiao.in.spring.conversion;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName PropertyEditorDemo.java
 * @Description
 * @createTime 2021年02月20日 12:50:00
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {
        StringToPropertyEditor editor = new StringToPropertyEditor();
        editor.setAsText("name=老肖");
        //最终会输出Property对象数据
        System.out.println(editor.getValue());
    }

    static class StringToPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            //将String 类型转为properties
            Properties properties = new Properties();
            try {
                properties.load(new StringReader(text));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //暂存
            setValue(properties);
        }
    }
}
