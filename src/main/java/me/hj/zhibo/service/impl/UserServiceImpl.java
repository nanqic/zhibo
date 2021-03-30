package me.hj.zhibo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.vo.*;
import me.hj.zhibo.entity.User;
import me.hj.zhibo.mapper.UserMapper;
import me.hj.zhibo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import javax.servlet.http.HttpSession;



@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    HttpSession session;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public RespVO register(UserRegisterVO vo) {
        UserRegisterVO matchedVO = userMapper.getUserRegister(vo.getUsername());
        // 注册信息非空判断
        if (vo.getName().isEmpty() || vo.getPassword().isEmpty() || vo.getUsername().isEmpty()) {
            return RespVO.error("数据非法");
        }
        // 先判断姓名与账号是否匹配，再判断密码是否被初始化
        if (ObjectUtils.isEmpty(matchedVO)) return RespVO.error("请输入合法的账号！");
        if (!matchedVO.getName().equals(matchedVO.getName())) return RespVO.error("姓名与账号不匹配，请检查是否输入错误");
        if (StringUtils.isNotBlank(matchedVO.getPassword())) {
            return RespVO.error("账号已被注册，如非本人注册，请联系管理员处理");
        }
//        md5对密码摘要加密
        String pw = passwordEncoder.encode(vo.getPassword());
        User user = new User()
                .setPassword(pw);
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("username", vo.getUsername());
        int row = userMapper.update(user, wrapper);
        if (row != 1) {
            return RespVO.error("服务器内部异常");
        }
        return RespVO.ok("ok");
    }

    @Override
    public RespVO updatePasswd(NewPasswordVO vo) {

        String pw = passwordEncoder.encode(vo.getCurPassword());
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", vo.getUsername());
        wrapper.eq("password", pw);
        User matchedUser = userMapper.selectOne(wrapper);
        // 如果查询不到用户，提示原密码错误
        if(ObjectUtils.isEmpty(matchedUser)) return RespVO.error("原密码错误");
        User user = new User()
                .setPassword(vo.getNewPassword())
                .setUid(1);
        int row = userMapper.updateById(user);
        if (row == 1)
        return RespVO.ok("修改成功！");

        return  RespVO.error("服务器内部错误");
    }

    @Override
    public RespVO getLoginResults(String username) {
        LoginResultsVO vo = userMapper.getLoginResults(username);
        return RespVO.ok("ok",vo);
    }

}
