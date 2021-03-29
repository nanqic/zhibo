package me.hj.zhibo.service.impl;

import me.hj.zhibo.entity.DisserUser;
import me.hj.zhibo.entity.Dissertation;
import me.hj.zhibo.mapper.DisserUserMapper;
import me.hj.zhibo.mapper.DissertationMapper;
import me.hj.zhibo.service.IDisserUserService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisserUserServiceImpl implements IDisserUserService {

    @Autowired
    private DisserUserMapper mapper;
    @Autowired
    private DissertationMapper dissertationMapper;

    @Override
    public RespVO submitDisser(Integer did, Integer uid) {
        DisserUser du = new DisserUser()
                .setDid(did)
                .setUid(uid);
        mapper.insert(du);
        Dissertation dissertation = new Dissertation()
                .setDid(did).setCount(1);
        dissertationMapper.updateById(dissertation);
        return RespVO.ok("ok");
    }
}
