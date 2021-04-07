package com.xiao.controller;

import com.xiao.pojo.User;
import com.xiao.valid.SaveUserValid;
import com.xiao.valid.UpdateUserValid;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ValidController.java
 * @Description
 * @createTime 2021年04月06日 21:21:00
 */
@RestController
@RequestMapping("/valid")
public class ValidController {

    @PostMapping("/save")
    public String save(@Validated(SaveUserValid.class) @RequestBody User user, BindingResult result) {
        if(result.hasErrors()) {
            //将错误信息合并起来返回
            StringBuilder builder = new StringBuilder();
            result.getFieldErrors().forEach(item -> builder.append(item.getDefaultMessage()));
            return builder.toString();
        }
        return null;
    }

    @PostMapping("/update")
    public void update(@Validated(UpdateUserValid.class) @RequestBody User user) {
        System.out.println("update -> "+ user.toString());
    }

}
