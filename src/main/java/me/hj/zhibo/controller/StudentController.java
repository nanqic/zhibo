package me.hj.zhibo.controller;

import me.hj.zhibo.vo.RespVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    //    @GetMapping("/dissers/{index}/{size}")
//    RespVO disserList(@PathVariable("index") int index, @PathVariable("size") int size) {
//
//        return service.disserList(index, size);
//    }

    //查询学生志愿
//    @GetMapping("/{uid}")
//    RespVO selectOne(@PathVariable("uid") int uid) {
//        return service.getOne(uid);
//    }

//    // 提交选题志愿
//    @PostMapping("/submit")
//    RespVO submitDisser(@RequestParam int did) {
//        return service.submitDisser(did);
//    }
}
