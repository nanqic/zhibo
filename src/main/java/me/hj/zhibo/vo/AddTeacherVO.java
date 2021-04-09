package me.hj.zhibo.vo;

import lombok.Data;
import me.hj.zhibo.entity.TeacherInfo;

@Data
public class AddTeacherVO {
    private String username;
    private TeacherInfo teacherInfo;
}
