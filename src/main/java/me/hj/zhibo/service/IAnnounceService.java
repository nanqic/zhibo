package me.hj.zhibo.service;

import me.hj.zhibo.vo.RespVO;

public interface IAnnounceService{
    RespVO getPage(int index, int size);
    RespVO newAnno(String title, String content);
}
