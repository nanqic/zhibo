package me.hj.zhibo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**   status 选题状态
 *     -0 选题进行中 -1 已完成选题
 *     -2 初审中 -3 初审退回 -4 初审通过
 *     -5 中期鉴定中 -6 中期鉴定退回  -7 中期鉴定通过
 *     -8 最终审核中 -9 最终审核退回 -10 最终审核通过
 */
@Accessors(chain = true)
@Data
public class Dissertation {
    @TableId(type = IdType.AUTO)
    private Integer did;
    private String name;
    private String path;
    private Integer uid;
    private String auditPath;
    private Integer status;
    private String advice;
}
