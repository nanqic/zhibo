package me.hj.zhibo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StudentInfo {
    @TableId(type = IdType.INPUT)
    private Integer uid;
    private String name;
    private byte deptId;
    private byte majorId;
    private String phone;
    private byte classId;
    private Integer graduation;
}
