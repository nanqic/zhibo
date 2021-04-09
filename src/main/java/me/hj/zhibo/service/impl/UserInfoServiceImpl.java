package me.hj.zhibo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.hj.zhibo.entity.Counselor;
import me.hj.zhibo.entity.TeacherInfoView;
import me.hj.zhibo.entity.StudentInfo;
import me.hj.zhibo.entity.StudentInfoView;
import me.hj.zhibo.mapper.CounselorMapper;
import me.hj.zhibo.mapper.TeacherInfoMapper;
import me.hj.zhibo.mapper.StudentInfoMapper;
import me.hj.zhibo.mapper.UserMapper;
import me.hj.zhibo.service.IUserInofService;
import me.hj.zhibo.utils.UserUtil;
import me.hj.zhibo.vo.RespVO;
import me.hj.zhibo.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInofService {
    @Autowired
    StudentInfoMapper infoMapper;
    @Autowired
    CounselorMapper counMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TeacherInfoMapper teacherInfoMapper;

    @Override
    public RespVO getStudentInfo() {
        int uid = getUid();
        StudentInfoView info = infoMapper.getUserInfoViewById(uid);
        // 查询对应老师
        int teacUid = counMapper.selectById(uid).getTeachUid();
        // 查询老师名字
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("name").eq("uid", teacUid);
        String teacherName = teacherInfoMapper.selectOne(wrapper).getName();

        // 查询详细信息
        UserInfoVO vo = new UserInfoVO();
        vo.setView(info);
        vo.setCounselor(new String[]{teacherName});

        return RespVO.ok("查询成功！", vo);
    }

    @Override
    public RespVO getTeacherInfo() {
        int uid = getUid();
        // 查询对应所有学生uid
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("teach_uid", uid);
        wrapper1.select("stu_uid");
        List<Counselor> counselors = counMapper.selectList(wrapper1);

        // 查询所有学生名字
        QueryWrapper<StudentInfo> wrapper2 = new QueryWrapper<StudentInfo>();
        wrapper2.select("name");
        counselors.forEach(counselor -> wrapper2.eq("uid", counselor.getStuUid()).or());
        List<StudentInfo> infos = infoMapper.selectList(wrapper2);
        String[] stusName = new String[infos.size()];
        for (int i = 0; i <= infos.size() - 1; i++) {
            stusName[i] = infos.get(i).getName();
        }

        // 查询老师详细信息
        TeacherInfoView view = teacherInfoMapper.getTeacherInfoViewById(uid);
        UserInfoVO vo = new UserInfoVO();
        vo.setView(view);
        // 被指导的学生列表
        vo.setCounselor(stusName);
        return RespVO.ok("查询成功！", vo);
    }

    @Override
    public RespVO getInfoByName(String name) {
        TeacherInfoView view = teacherInfoMapper.getTeacherInfoViewByName(name);
        return RespVO.ok("ok", view);
    }

    private int getUid() {
        int uid = userMapper.getUid(UserUtil.getCurrentUser().getUsername());
        return uid;
    }
}
