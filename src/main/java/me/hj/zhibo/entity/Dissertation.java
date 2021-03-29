package me.hj.zhibo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Dissertation {
    @TableId(type = IdType.AUTO)
    private Integer did;
    private String name;
    private Integer count;
    private String path;
    private String teacher;
    private Integer status;
}
