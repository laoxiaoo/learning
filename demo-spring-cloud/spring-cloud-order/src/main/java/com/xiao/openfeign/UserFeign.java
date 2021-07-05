package com.xiao.openfeign;

import com.xiao.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xiao ji hao
 * @create 2021年07月04日 11:47:00
 */
@FeignClient(name = "user-81", fallback = UserFeignImpl.class)
//@RequestMapping("/user")
public interface UserFeign {
    @GetMapping("/user/get")
    UserVO getUser(@RequestParam("userId") Long userId);
    
}
