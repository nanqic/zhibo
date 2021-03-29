package me.hj.zhibo.controller;

import me.hj.zhibo.service.IFileService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/file")
@RestController
public class FileController {
    @Autowired
    private IFileService service;

    @PostMapping("/upload")
    RespVO upload(@RequestBody MultipartFile file, int uid)throws IOException {
        return service.uploadFile(file,uid);
    }
    @GetMapping("/list/{index}")
    RespVO fileList(@PathVariable int index){
        return service.fileList(index);
    }
}
