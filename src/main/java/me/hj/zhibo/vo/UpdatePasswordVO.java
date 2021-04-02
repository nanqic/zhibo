package me.hj.zhibo.vo;

import lombok.Data;

@Data
public class UpdatePasswordVO {
    private String oldPassword;
    private String newPassword;
}
