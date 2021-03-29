package me.hj.zhibo.service;

import me.hj.zhibo.vo.RespVO;

public interface IUserInofService {
    RespVO getTeacher(Integer uid);
    RespVO getStudents(Integer uid);
    RespVO getInfoByName(String name);
}
