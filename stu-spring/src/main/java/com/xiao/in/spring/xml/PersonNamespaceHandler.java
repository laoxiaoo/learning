package com.xiao.in.spring.xml;

import com.xiao.pojo.Person;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.util.Optional;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName PersonNamespaceHandler.java
 * @Description
 * @createTime 2021年02月17日 15:55:00
 */
public class PersonNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("person", new PersonBeanDefinitionParser());
    }

    private class PersonBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
        @Override
        protected Class<?> getBeanClass(Element element) {
            return Person.class;
        }

        @Override
        protected void doParse(Element element, BeanDefinitionBuilder builder) {
            this.setAttribute("name", element.getAttribute("name"), builder);
            this.setAttribute("age", element.getAttribute("age"), builder);
        }

        private void setAttribute(String name, String value, BeanDefinitionBuilder builder){
            Optional.ofNullable(value).ifPresent(v -> builder.addPropertyValue(name, v));
        }
    }
}
