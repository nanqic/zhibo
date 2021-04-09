package me.hj.zhibo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.Announce;
import me.hj.zhibo.mapper.AnnounceMapper;
import me.hj.zhibo.mapper.UserMapper;
import me.hj.zhibo.service.IAnnounceService;
import me.hj.zhibo.utils.UserUtil;
import me.hj.zhibo.vo.AnnounceVO;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnounceServiceImpl implements IAnnounceService {
    @Autowired
    AnnounceMapper mapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public RespVO getPage(int index, int size) {
        Page<AnnounceVO> page = new Page<>(index,size);
        IPage<AnnounceVO> resPage = mapper.getList(page);
        return RespVO.ok("查询成功",resPage);
    }

    @Override
    public RespVO newAnno(String title, String content) {
        Announce anno = new Announce()
                .setTitle(title)
                .setContent(content)
                .setUid(getUid());
        int row = mapper.insert(anno);
        if (row!=1)
        return null;

        return RespVO.ok("发布成功！");
    }
    private int getUid() {
        int uid = userMapper.getUid(UserUtil.getCurrentUser().getUsername());
        return uid;
    }
}
