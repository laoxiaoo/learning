package com.xiao;

import com.xiao.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.tools.reflect.Metaobject;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;


/**
 *
 * mybatis 反射工具类测试使用
 *
 * @author xiao ji hao
 */
@Slf4j
public class MetaObjectTest {

    @Test
    public void getProperty() {
        //对于mybatis，他不知道具体的类
        Object obj = new Student();
        Configuration configuration = new Configuration();
        MetaObject metaObject = configuration.newMetaObject(obj);
        //输出：phoneNumber
        log.debug("获取驼峰属性名称：{}", metaObject.findProperty("phone_number", true));
    }

}
