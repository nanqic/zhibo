package me.hj.zhibo.service;

import me.hj.zhibo.vo.*;

public interface IUserService {
    RespVO register(UserRegisterVO vo);
    RespVO updatePasswd(UpdatePasswordVO vo);
    RespVO getLoginResults();
}
