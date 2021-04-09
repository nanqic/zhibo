package me.hj.zhibo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/*
老师登录结果
 */
@Accessors(chain = true)
@Data
public class TeLoginResultsVO {
    private Integer uid;
    private String name;
    private String roleName;
    private String dept;
    private String major;
    private String jobTitle;
    private String degree;
}
