package me.hj.zhibo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class LoginResultsVO {
    private Integer uid;
    private String name;
    private String roleName;
    private String major;
    private String className;
    private String graduation;
}
