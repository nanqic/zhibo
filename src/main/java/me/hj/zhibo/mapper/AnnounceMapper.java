package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.Announce;
import me.hj.zhibo.vo.AnnounceVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnounceMapper extends BaseMapper<Announce> {
    @Select("select a.title, a.content, a.update_time, i.name from tb_announce a left join tb_teacher_info i on a.uid=i.uid ORDER BY a.aid desc")
    IPage<AnnounceVO> getList(Page<AnnounceVO> page);

}
