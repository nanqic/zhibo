package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.hj.zhibo.entity.StudentInfo;
import me.hj.zhibo.entity.StudentInfoView;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {
    @Select("select *from v_student_info where uid=#{uid}")
    StudentInfoView getStudentInfoViewById(int uid);

    @Select("select *from v_student_info where name=#{name}")
    StudentInfoView getStudentInfoViewByName(String name);

}
