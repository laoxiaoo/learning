package com.xiao.openfeign;

import com.xiao.vo.UserVO;
import org.springframework.stereotype.Component;

/**
 * @author xiao ji hao
 * @create 2021年07月04日 23:37:00
 */
@Component
public class UserFeignImpl implements UserFeign {
    @Override
    public UserVO getUser(Long userId) {
        return new UserVO("服务器凉了，兜底的方法", 20);
    }
}
