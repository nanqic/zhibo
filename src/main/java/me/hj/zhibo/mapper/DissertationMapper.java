package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.Dissertation;
import me.hj.zhibo.vo.DissertationVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface DissertationMapper extends BaseMapper<Dissertation> {
    @Select("select * from tb_dissertation d where d.uid=#{uid}")
    IPage<DissertationVO> getMyDissertation(int uid, Page<DissertationVO> page);
}
