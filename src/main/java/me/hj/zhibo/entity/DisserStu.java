package me.hj.zhibo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class DisserStu {
    // 论文题目和选题学生
    private Integer did;
    private Integer uid;
}
