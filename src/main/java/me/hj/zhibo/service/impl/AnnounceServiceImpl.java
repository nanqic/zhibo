package me.hj.zhibo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.Announce;
import me.hj.zhibo.mapper.AnnounceMapper;
import me.hj.zhibo.service.IAnnounceService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnounceServiceImpl implements IAnnounceService {
    @Autowired
    AnnounceMapper mapper;
    @Override
    public RespVO getPage(int index, int size) {
        IPage<Announce> iPage = new Page<>(index,size);
        IPage<Announce> resPage = mapper.selectPage(iPage,null);
        return RespVO.ok("查询成功",resPage);
    }

    @Override
    public RespVO newAnno(String title, String content) {
        Announce anno = new Announce()
                .setTitle(title)
                .setContent(content);
        int row = mapper.insert(anno);
        if (row!=1)
        return null;

        return RespVO.ok("发布成功！");
    }
}
