package com.xiao;

import com.xiao.util.JwtUtils;
import io.jsonwebtoken.Claims;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName Test.java
 * @Description
 * @createTime 2021年01月24日 00:44:00
 */
public class Test {

    @org.junit.Test
    public void jwt() {
        Claims claims = JwtUtils.verifyJwt("eyJhbGciOiJIUzI1NiJ9." +
                "eyJ1c2VyTmFtZSI6bnVsbCwiZXhwIjoxNjExNDIyNzE0LCJ1c2VySWQiOjEsImlhdCI6MTYxMTQyMDEyMiwianRpIjoidG9rZW5JZCJ9." +
                "YrzNJ0Aql4TTlHpyqTKUtXc1Cet2AIdVAE-Bg8_rTec");
        System.out.println(claims);
    }
}
