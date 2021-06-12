package com.xiao.log;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class SysLog {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String moduleName;

    private String webUrl;

    private String ip;

    private String browerType;

    private String parame;

}
