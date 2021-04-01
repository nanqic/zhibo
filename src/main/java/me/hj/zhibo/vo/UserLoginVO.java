package me.hj.zhibo.vo;

import lombok.Data;
@Data
public class UserLoginVO {
    private Integer uid;
    private String username; //对应学号或教职工号,管理员账号
    private String password;
    private String roleName;
}
