package me.hj.zhibo.vo;

import lombok.Data;
import me.hj.zhibo.entity.StudentInfo;

@Data
public class AddStudentVO {
    private String username;
    private Integer teacherUid;
    private StudentInfo studentInfo;
}
