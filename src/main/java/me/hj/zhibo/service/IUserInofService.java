package me.hj.zhibo.service;

import me.hj.zhibo.vo.RespVO;

public interface IUserInofService {
    RespVO getTeacherInfo();
    RespVO getStudentInfo();
    RespVO getInfoByName(String name);
}
