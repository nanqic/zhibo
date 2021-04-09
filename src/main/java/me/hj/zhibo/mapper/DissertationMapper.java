package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.Dissertation;
import me.hj.zhibo.vo.DissertationVO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface DissertationMapper extends BaseMapper<Dissertation> {
    @Select("select d.*, i.name as teacher from tb_dissertation d left join tb_teacher_info i on d.uid=i.uid")
    IPage<DissertationVO> getDissers(Page<DissertationVO> page);

    @Select("select * from tb_dissertation d where d.uid=#{uid}")
    IPage<DissertationVO> getMyDissertation(int uid, Page<DissertationVO> page);

    @Select("SELECT d.*,i.name as teacher FROM (tb_dissertation d JOIN tb_disser_stu s ON d.did=s.did) LEFT JOIN tb_teacher_info i ON d.uid=i.uid WHERE s.uid=#{uid}")
    DissertationVO myAspiration(int uid);

    @Update("update tb_dissertation SET `status`=0 WHERE did=#{did}")
    int updateStatus(int did);

    @Select("SELECT d.*,i.name as teacher FROM tb_dissertation d LEFT JOIN v_user_info i ON d.uid=i.uid WHERE d.name LIKE #{words}")
    IPage<DissertationVO> search(String words, Page<DissertationVO> page);

}
