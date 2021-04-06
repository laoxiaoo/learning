package com.xiao.pojo;

import com.xiao.valid.SaveUserValid;
import com.xiao.valid.UpdateUserValid;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName User.java
 * @Description
 * @createTime 2021年04月06日 21:22:00
 */
@Getter
@Setter
@ToString
public class User {
    @NotBlank(message = "用户名不能为空", groups = SaveUserValid.class)
    @Null(message = "修改不能传用户名", groups = UpdateUserValid.class)
    private String name;
}
