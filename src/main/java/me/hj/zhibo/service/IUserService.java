package me.hj.zhibo.service;

import me.hj.zhibo.vo.NewPasswordVO;
import me.hj.zhibo.vo.RespVO;
import me.hj.zhibo.vo.UserRegisterVO;

public interface IUserService {
    RespVO register(UserRegisterVO vo);
    RespVO updatePasswd(NewPasswordVO vo);
    RespVO userList(int index, int size);
    RespVO getLoginResults(String username);
}
