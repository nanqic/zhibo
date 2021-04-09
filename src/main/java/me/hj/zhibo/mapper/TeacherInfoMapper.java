package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.hj.zhibo.entity.TeacherInfo;
import me.hj.zhibo.entity.TeacherInfoView;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherInfoMapper extends BaseMapper<TeacherInfo> {
    @Select("select *from v_teacher_info where uid=#{uid}")
    TeacherInfoView getTeacherInfoViewById(int uid);

    @Select("select *from v_teacher_info where name=#{name}")
    TeacherInfoView getTeacherInfoViewByName(String name);

    @Select("select uid, name from v_teacher_info")
    TeacherInfoView[] getTeachers();
}
