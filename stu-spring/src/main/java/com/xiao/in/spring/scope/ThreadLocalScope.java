package com.xiao.in.spring.scope;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ThreadLocalScope.java
 * @Description
 * @createTime 2021年02月11日 22:20:00
 */
public class ThreadLocalScope implements Scope {

    public static final String SCOPE_NAME="thread-local";

    private final NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal("thread-local-scope") {
        @Override
        protected Object initialValue() {
            //没有获取到对象是兜底返回
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = threadLocal.get();
        Object object = context.get(name);
        if(ObjectUtils.isEmpty(object)) {
            object = objectFactory.getObject();
            context.put(name, object);
        }
        return object;
    }

    @Override
    public Object remove(String name) {
        return threadLocal.get().remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return threadLocal.get().get(key);
    }

    @Override
    public String getConversationId() {
        return String.valueOf(Thread.currentThread().getId());
    }
}
