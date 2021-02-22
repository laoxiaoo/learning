package com.xiao.in.spring.validated;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationInterceptor;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName BeanValidatedDemo.java
 * @Description
 * @createTime 2021年02月19日 17:32:00
 */
@ComponentScan("com.xiao.in.spring.validated")
public class BeanValidatedDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanValidatedDemo.class);
        applicationContext.refresh();
        UserProcess bean = applicationContext.getBean(UserProcess.class);
        bean.process(new User());
        applicationContext.close();
    }

    @Bean
    static LocalValidatorFactoryBean validated() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        return validator;
    }

    @Bean
    static MethodValidationPostProcessor methodValidator(Validator validator) {
        MethodValidationPostProcessor methodValidation = new MethodValidationPostProcessor();
        methodValidation.setValidator(validator);
        return methodValidation;
    }

    @Setter
    @Getter
    @ToString
    static class User {
        @NotNull
        private String name;

        private Integer id;
    }

    @Component
    @Validated
    static class  UserProcess {
        public void process(@Valid User user) {
            System.out.println(user);
        }
    }
}
