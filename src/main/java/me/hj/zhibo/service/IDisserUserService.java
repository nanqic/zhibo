package me.hj.zhibo.service;

import me.hj.zhibo.vo.RespVO;

public interface IDisserUserService {
    RespVO submitDisser(Integer did, Integer uid);
}
