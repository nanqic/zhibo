package me.hj.zhibo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.*;
import me.hj.zhibo.mapper.CounselorMapper;
import me.hj.zhibo.mapper.StudentInfoMapper;
import me.hj.zhibo.mapper.TeacherInfoMapper;
import me.hj.zhibo.mapper.UserMapper;
import me.hj.zhibo.service.IAdminService;
import me.hj.zhibo.vo.AddStudentVO;
import me.hj.zhibo.vo.AddTeacherVO;
import me.hj.zhibo.vo.RespVO;
import me.hj.zhibo.vo.UserListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private CounselorMapper counselorMapper;

    @Override
    public RespVO userList(int index, int size) {
        Page<UserListVO> page = new Page<>(index, size);
        IPage<UserListVO> resPage = userMapper.getUserList(page);
        return RespVO.ok("ok", resPage);
    }

    @Override
    public RespVO searchUser(int index, int size, String username) {
        username = "%"+username+"%";
        Page<UserListVO> page = new Page<>(index, size);
        IPage<UserListVO> resPage = userMapper.searchUser(page, username);
        return RespVO.ok("ok", resPage);
    }

    @Override
    public RespVO resetUser(String username) {
        userMapper.resetUser(username);
        return RespVO.ok("ok");
    }

    @Override
    public RespVO deleteUser(String username) {
        userMapper.deleteUser(username);
        return RespVO.ok("ok");
    }

    @Override
    public RespVO disableUser(String username) {
        userMapper.disableUser(username);
        return RespVO.ok("ok");
    }

    @Override
    public RespVO enableUser(String username) {
        userMapper.enableUser(username);
        return RespVO.ok("ok");
    }

    @Override
    public RespVO addTeacher(AddTeacherVO vo) {
        User user = new User()
                .setUsername(vo.getUsername())
                .setRole_id(1);
        int row = userMapper.insert(user);
        vo.getTeacherInfo().setUid(user.getUid());
        if (row == 1) {
            int row2 = teacherInfoMapper.insert(vo.getTeacherInfo());
            if (row2 == 1) return RespVO.ok("ok");
        }

        return RespVO.error("error");
    }

    @Override
    public RespVO addStudent(AddStudentVO vo) {
        User user = new User()
                .setUsername(vo.getUsername())
                .setRole_id(2);
        int row = userMapper.insert(user);
        int stuUid = user.getUid();
        System.out.println(vo);
        StudentInfo info = vo.getStudentInfo();
        info.setUid(stuUid);
        if (row == 1) {
            int row2 = studentInfoMapper.insert(info);
            if (row2 == 1) {
                Counselor c = new Counselor()
                        .setStuUid(stuUid)
                        .setTeachUid(vo.getTeacherUid());
                int row3 = counselorMapper.insert(c);
                if (row3 == 1)
                    return RespVO.ok("ok");
            }
        }
        return RespVO.error("error");
    }

    @Override
    public RespVO getTeachers() {
        TeacherInfoView[] infoViews = teacherInfoMapper.getTeachers();
        return RespVO.ok("ok", infoViews);
    }
}
