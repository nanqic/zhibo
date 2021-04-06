package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.hj.zhibo.entity.DisserStu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisserStuMapper extends BaseMapper<DisserStu> {
    @Select("SELECT * FROM tb_disser_stu WHERE did=#{did} OR uid=#{uid}")
    List<DisserStu> isSelected(int did, int uid);
}
