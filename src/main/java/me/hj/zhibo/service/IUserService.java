package me.hj.zhibo.service;

import me.hj.zhibo.vo.UpdatePasswordVO;
import me.hj.zhibo.vo.RespVO;
import me.hj.zhibo.vo.UserRegisterVO;

public interface IUserService {
    RespVO register(UserRegisterVO vo);
    RespVO updatePasswd(UpdatePasswordVO vo);
    RespVO getLoginResults(String username);
}
