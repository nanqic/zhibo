package me.hj.zhibo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 通告信息实体类，只有老师才可以发表通告
 */
@Accessors(chain = true)
@Data
public class Announce {
    @TableId(type = IdType.AUTO)
    private Integer aid;
    private String title;
    private String content;
    private Integer uid;
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
