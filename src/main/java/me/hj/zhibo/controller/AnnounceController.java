package me.hj.zhibo.controller;

import me.hj.zhibo.entity.Announce;
import me.hj.zhibo.service.IAnnounceService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/anno")
@RestController
public class AnnounceController {
    @Autowired
    IAnnounceService service;

    @GetMapping("/page/{index}/{size}")
    RespVO page(@PathVariable("index") Integer index,@PathVariable("size") Integer size){
        return service.getPage(index,size);
    }


}
