package me.hj.zhibo.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import me.hj.zhibo.entity.Counselor;
import me.hj.zhibo.entity.UserInfo;

@Accessors(chain = true)
@Data
public class UserInfoVO {
    private UserInfo userInfo;
    private String[] counselor; //指导老师 or 被指导学生列表
}
