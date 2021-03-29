package me.hj.zhibo.controller;

import me.hj.zhibo.service.IDisserUserService;
import me.hj.zhibo.service.IDissertationService;
import me.hj.zhibo.vo.RespVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;


@RequestMapping("/disser")
@RestController
public class DissertationController {
    @Autowired
    private IDissertationService service;
    @Autowired
    IDisserUserService disserUserService;

    @PostMapping("/upload")
    private RespVO upload(@RequestBody MultipartFile file, String name, int uid) throws IOException {

        return service.upload(file, name, uid);
    }
    @GetMapping("/download")
    ResponseEntity download(@RequestParam String path)throws FileNotFoundException {
        System.out.println(path);
        return service.download(path);
    }

    @GetMapping("/list/{index}/{size}")
    RespVO disserList(@PathVariable("index") int index, @PathVariable("size") int size){

        return service.disserList(index,size);
    }
    @GetMapping("/my/{index}/{size}")
    RespVO myDisser(@PathVariable("index") int index, @PathVariable("size") int size,
                      @RequestParam(value = "uid", required = true) int uid){

        return service.disserList(index,size,uid);
    }
    @PostMapping("/delete")
    RespVO delete(@RequestParam(value = "did") int did, @RequestParam(value = "path") String path) throws FileNotFoundException{
        return service.delete(did,path);
    }

    // 提交选题志愿
    @PostMapping("/submit")
    RespVO submitDisser(@RequestParam int did,int uid){
        return disserUserService.submitDisser(did,uid);
    }

    //查询学生志愿
    @GetMapping("/select/{uid}")
    RespVO selectOne(@PathVariable("uid") int uid){
        return service.selectOne(uid);
    }
}
