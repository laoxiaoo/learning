package com.xiao.copy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lao xiao
 * @create 2022年 08月 11日 11:23
 */
@Getter
@Setter
@ToString
public class UserDTO {

    private Integer userId;

    private String userName;

    public static UserVO voToDto(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        userVO.setUserId(userDTO.getUserId());
        userVO.setUserName(userDTO.getUserName());
        return userVO;
    }

}
