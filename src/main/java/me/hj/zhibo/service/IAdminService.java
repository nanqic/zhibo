package me.hj.zhibo.service;

import me.hj.zhibo.vo.RespVO;

public interface IAdminService {
    RespVO userList(int index, int size);
    RespVO resetUser(String username);
    RespVO deleteUser(String username);
    RespVO disableUser(String username);
    RespVO enableUser(String username);

}
