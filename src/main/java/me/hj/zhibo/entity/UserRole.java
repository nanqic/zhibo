package me.hj.zhibo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserRole {
    @TableId(type = IdType.AUTO)
    private final Integer id;
    private final Integer roleId;
    private final String roleName;
}
