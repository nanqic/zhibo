package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.Counselor;
import me.hj.zhibo.entity.FileEntity;
import me.hj.zhibo.vo.FileListVO;
import me.hj.zhibo.vo.RespVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMapper extends BaseMapper<FileEntity> {
    @Select("select f.id,f.path, i.name as uploader from tb_file f left join tb_user_info i on f.uid=i.uid")
    IPage<FileListVO> selectFileList(Page<FileListVO> page);
}
