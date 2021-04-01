package me.hj.zhibo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class DisserUser {
    // 论文题目和出题老师关联表
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer did;
    private Integer uid;
}
