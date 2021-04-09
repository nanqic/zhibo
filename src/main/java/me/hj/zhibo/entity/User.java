package me.hj.zhibo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    @TableId(type=IdType.AUTO)
    private Integer uid;
    private String username; //对应学号或教职工号,管理员账号
    private String password;
    private Integer role_id;//用户角色
    @TableField(exist = false)
    private Integer enabled;//是否可用

}
