package me.hj.zhibo.service.impl;

import me.hj.zhibo.mapper.UserMapper;
import me.hj.zhibo.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginVO vo = userMapper.getUserLogin(username);

        // 如果查询不到用户，提示不存在
        if (ObjectUtils.isEmpty(vo)) throw new UsernameNotFoundException("账号错误或用户不存在");
        // 根据查到的角色，创建相应的角色
        String role = vo.getRoleName();
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        // 这里传入数据库查到的密码，security框架会自动校验
        UserDetails userDetails = new User(username, vo.getPassword(),
                simpleGrantedAuthorities);
        return userDetails;
    }
}
