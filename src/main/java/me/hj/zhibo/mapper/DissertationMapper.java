package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.hj.zhibo.entity.Dissertation;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface DissertationMapper extends BaseMapper<Dissertation> {
    @Select("select d.teacher, d.name, d.status from tb_dissertation d left join tb_disser_user u on d.did=u.did where u.uid=#{uid}")
    Dissertation selectByUid(int uid);
}
