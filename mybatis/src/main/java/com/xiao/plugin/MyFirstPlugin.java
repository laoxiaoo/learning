package com.xiao.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * type:拦截的接口
 * method:拦截的方法，可以到type(StatementHandler)中寻找
 * args：方法的参数
 *
 * @author lao xiao
 * @create 2022年06月07日 21:15:00
 */
@Intercepts({
        @Signature(type= StatementHandler.class,
                method="parameterize",
                args=java.sql.Statement.class)
        })
public class MyFirstPlugin implements Interceptor {

    /**
     * 拦截方法：只要被拦截的目标对象执行，就会执行这个方法
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstPlugin...intercept:"+invocation.getMethod());
        //动态的改变一下sql运行的参数：以前1号员工，实际从数据库查询3号员工
        //获取拦截到的目标对象
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象："+target);
        //拿到：StatementHandler==>ParameterHandler===>parameterObject
        //拿到target的元数据（它可以拿到StatementHandler里面的属性值）
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句用的参数是："+value);
        //修改完sql语句要用的参数
        metaObject.setValue("parameterHandler.parameterObject", 11);
        //执行目标方法（这里一定要注意）
        Object proceed = invocation.proceed();
        //返回执行后的返回值
        return proceed;
    }

    /**
     * 把当前拦截器类生成代理，存入拦截器链中
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        //我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象
        System.out.println("MyFirstPlugin...plugin:mybatis将要包装的对象"+target);
        Object wrap = Plugin.wrap(target, this);
        //返回为当前target创建的动态代理
        return wrap;
    }

    /**
     * 获取配置文件的参数
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的信息："+properties);
    }
}
