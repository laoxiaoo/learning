package com.xiao.pointcut;

/**
 * 配置顺序
 * <br>
 * 1.定义配置类
 * 2.在xml中配置AspectXmlConfiguration 的bean
 * 3.在aop:config中配置aop:aspect
 * 4.在aop:aspect 配置对应的切面等信息
 *
 *
 * @author xiao ji hao
 * @create 2022年02月14日 20:03:00
 */
public class AspectXmlConfiguration {
    private void anyPublicMethod() {
    }

    public void beforeMethod() {

    }
}
