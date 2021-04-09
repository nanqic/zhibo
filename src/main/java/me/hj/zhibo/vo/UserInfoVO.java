package me.hj.zhibo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class UserInfoVO {
    private Object view;
    private String[] counselor; //指导老师 or 被指导学生列表
}
