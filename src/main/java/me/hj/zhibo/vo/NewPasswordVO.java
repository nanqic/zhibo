package me.hj.zhibo.vo;

import lombok.Data;

@Data
public class NewPasswordVO {
    private String username;
    private String curPassword;
    private String newPassword;
}
