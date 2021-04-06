package me.hj.zhibo.controller;


import me.hj.zhibo.entity.Announce;
import me.hj.zhibo.service.IAnnounceService;
import me.hj.zhibo.service.IDissertationService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;


@RequestMapping("/teacher")
@RestController
public class TeacherController {
    @Autowired
    private IDissertationService service;
    @Autowired
    private IAnnounceService annoService;

    // 上传新题目，包含附件
    @PostMapping("/disser")
    private RespVO newDissertation(@RequestBody MultipartFile file, String name) throws IOException {

        return service.newDisser(file, name);
    }

    // 获取当前登录教师发布的题目
    @GetMapping("/{index}/{size}")
    RespVO myDissertation(@PathVariable("index") int index, @PathVariable("size") int size){

        return service.myDissers(index, size);
    }

    // 删除题目
    @DeleteMapping("/")
    RespVO delete(@RequestParam(value = "did") int did, @RequestParam(value = "path") String path) throws FileNotFoundException {
        return service.delete(did, path);
    }

    // 发布通告

    @PostMapping("/anno")
    RespVO newAnno(@RequestBody Announce a) {
        return annoService.newAnno(a.getTitle(), a.getContent());
    }

}
