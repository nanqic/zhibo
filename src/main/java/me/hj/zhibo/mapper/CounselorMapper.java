package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.hj.zhibo.entity.Counselor;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounselorMapper extends BaseMapper<Counselor> {
    @Select("SELECT `name` as teacher from tb_teacher_info WHERE uid=(SELECT teach_uid FROM tb_counselor WHERE stu_uid=#{uid})")
    String getTeacherByStuUid(int uid);
    @Select("SELECT `name` as student from tb_student_info WHERE uid IN (SELECT stu_uid FROM tb_counselor WHERE teach_uid=#{uid})")
    String[] getStusByTeachUid(int uid);
}
