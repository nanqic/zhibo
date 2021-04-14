package me.hj.zhibo.service;

import me.hj.zhibo.vo.AddStudentVO;
import me.hj.zhibo.vo.AddTeacherVO;
import me.hj.zhibo.vo.RespVO;

public interface IAdminService {
    RespVO userList(int index, int size);
    RespVO searchUser(int index, int size, String username);
    RespVO resetUser(String username);
    RespVO deleteUser(String username);
    RespVO disableUser(String username);
    RespVO enableUser(String username);
    RespVO addTeacher(AddTeacherVO vo);
    RespVO addStudent(AddStudentVO vo);
    RespVO getTeachers();

}
