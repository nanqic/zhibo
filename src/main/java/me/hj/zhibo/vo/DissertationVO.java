package me.hj.zhibo.vo;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class DissertationVO {
    private Integer did;
    private String name;
    private Integer count;
    private String path;
    private String teacher;
    private Integer status;
}