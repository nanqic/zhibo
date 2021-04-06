package me.hj.zhibo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class DisserStu {
    // 论文题目和选题学生
    @TableId(value = "did" ,type = IdType.INPUT)
    private Integer did;
    private Integer uid;
}
