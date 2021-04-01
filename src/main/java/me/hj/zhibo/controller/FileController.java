package me.hj.zhibo.controller;

import me.hj.zhibo.service.IDissertationService;
import me.hj.zhibo.service.IFileService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@RequestMapping("/file")
@RestController
public class FileController {
    @Autowired
    private IFileService service;
    @Autowired
    private IDissertationService disserService;

    @PostMapping("/upload")
    RespVO upload(@RequestBody MultipartFile file)throws IOException {
        return service.uploadFile(file);
    }
    @GetMapping("/list/{index}")
    RespVO fileList(@PathVariable int index){
        return service.fileList(index);
    }

    // 下载论文
    @GetMapping("/disser")
    ResponseEntity download(@RequestParam String path) throws FileNotFoundException {
        return disserService.download(path);
    }
}
