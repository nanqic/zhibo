package me.hj.zhibo.entity;

import lombok.Data;

@Data
public class StudentInfoView {
    private Integer uid;
    private String name;
    private String dept;
    private String major;
    private String phone;
    private String className;
    private String graduation;
}
