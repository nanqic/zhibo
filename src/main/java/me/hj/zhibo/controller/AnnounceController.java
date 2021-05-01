package me.hj.zhibo.controller;

import me.hj.zhibo.entity.Announce;
import me.hj.zhibo.service.IAnnounceService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnnounceController {
    @Autowired
    IAnnounceService service;




}
