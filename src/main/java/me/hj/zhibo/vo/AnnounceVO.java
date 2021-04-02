package me.hj.zhibo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnnounceVO {
    private String title;
    private String content;
    private String name;
    private LocalDateTime updateTime;
}
