package me.hj.zhibo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer uid;
    private String name;
    private String dept;
    private String major;
    private String phone;
    private String className;
    private String graduation;
}
