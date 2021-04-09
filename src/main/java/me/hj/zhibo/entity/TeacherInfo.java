package me.hj.zhibo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class TeacherInfo {
    @TableId(type = IdType.INPUT)
    private Integer uid;
    private String name;
    private byte deptId;
    private byte majorId;
    private String phone;
    private String jobTitle;
    private String degree;
}
