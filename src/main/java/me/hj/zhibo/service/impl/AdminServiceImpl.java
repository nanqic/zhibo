package me.hj.zhibo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.mapper.UserMapper;
import me.hj.zhibo.service.IAdminService;
import me.hj.zhibo.vo.RespVO;
import me.hj.zhibo.vo.UserListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public RespVO userList(int index, int size) {
        Page<UserListVO> page = new Page<>(index, size);
        IPage<UserListVO> resPage = userMapper.getUserList(page);
        return RespVO.ok("ok", resPage);
    }

    @Override
    public RespVO resetUser(String username) {
        userMapper.resetUser(username);
        return RespVO.ok("ok");
    }

    @Override
    public RespVO deleteUser(String username) {
        userMapper.deleteUser(username);
        return RespVO.ok("ok");
    }

    @Override
    public RespVO disableUser(String username) {
        userMapper.disableUser(username);
        return RespVO.ok("ok");
    }

    @Override
    public RespVO enableUser(String username) {
        userMapper.enableUser(username);
        return RespVO.ok("ok");
    }
}
