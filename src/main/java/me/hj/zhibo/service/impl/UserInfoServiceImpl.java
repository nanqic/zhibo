package me.hj.zhibo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.hj.zhibo.entity.Counselor;
import me.hj.zhibo.entity.UserInfo;
import me.hj.zhibo.mapper.CounselorMapper;
import me.hj.zhibo.mapper.UserInfoMapper;
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
    UserInfoMapper infoMapper;
    @Autowired
    CounselorMapper counMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public RespVO getStudentInfo() {
        int uid = getUid();
        UserInfo info = infoMapper.selectById(uid);
        // 查询对应老师
        int teacUid = counMapper.selectById(uid).getTeachUid();
        // 查询老师名字
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("name").eq("uid", teacUid);
        String teacherName = infoMapper.selectOne(wrapper).getName();

        // 查询详细信息
        UserInfoVO vo = new UserInfoVO();
        vo.setUserInfo(info);
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
        QueryWrapper<UserInfo> wrapper2 = new QueryWrapper<UserInfo>();
        wrapper2.select("name");
        counselors.forEach(counselor -> wrapper2.eq("uid", counselor.getStuUid()).or());
        List<UserInfo> infos = infoMapper.selectList(wrapper2);
        String[] stusName = new String[infos.size()];
        for (int i = 0; i <= infos.size() - 1; i++) {
            stusName[i] = infos.get(i).getName();
        }

        // 查询老师详细信息
        UserInfo info = infoMapper.selectById(uid);
        UserInfoVO vo = new UserInfoVO();
        vo.setUserInfo(info);
        // 被指导的学生列表
        vo.setCounselor(stusName);
        return RespVO.ok("查询成功！", vo);
    }

    @Override
    public RespVO getInfoByName(String name) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("name", name);
        UserInfo info = infoMapper.selectOne(wrapper);
        return RespVO.ok("ok", info);
    }

    public int getUid() {
        int uid = userMapper.getUid(UserUtil.getCurrentUser().getUsername());
        return uid;
    }
}
