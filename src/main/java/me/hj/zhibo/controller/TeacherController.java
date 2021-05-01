package me.hj.zhibo.controller;


import me.hj.zhibo.entity.Announce;
import me.hj.zhibo.service.IAnnounceService;
import me.hj.zhibo.service.IDissertationService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;


@RequestMapping("/teacher")
@RestController
public class TeacherController {
    @Autowired
    private IDissertationService dService;
    @Autowired
    private IAnnounceService annoService;

    // 上传新题目，包含附件
    @PostMapping("/disser")
    private RespVO newDissertation(@RequestBody MultipartFile file, String name) throws IOException {

        return dService.newDisser(file, name);
    }

    // 获取当前登录教师发布的题目
    @GetMapping("/{index}/{size}")
    RespVO myDissertation(@PathVariable("index") int index, @PathVariable("size") int size){

        return dService.myDissers(index, size);
    }

    // 删除题目
    @DeleteMapping("/")
    RespVO delete(@RequestParam(value = "did") int did, @RequestParam(value = "path") String path) throws FileNotFoundException {
        return dService.delete(did, path);
    }

    // 发布通告

    @PostMapping("/anno")
    RespVO newAnno(@RequestBody Announce a) {
        return annoService.newAnno(a.getTitle(), a.getContent());
    }

    @GetMapping("/anno/{index}/{size}")
    RespVO page(@PathVariable("index") Integer index,@PathVariable("size") Integer size){
        return annoService.getPage(index,size);
    }
    
    @GetMapping("/audits/{index}/{size}")
    RespVO auditList(@PathVariable int index, @PathVariable int size){
        return dService.getAuditList(index, size);
    }

    @PostMapping("/reject")
    RespVO reject(@RequestParam int did, @RequestParam int status, @RequestParam(required = false) String advice){
        return dService.reject(did,status,advice);
    }
    @PostMapping("/pass")
    RespVO pass(@RequestParam int did, @RequestParam int status){
        return dService.pass(did,status);
    }
}
