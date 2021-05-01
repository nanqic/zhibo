package me.hj.zhibo.controller;

import me.hj.zhibo.service.IAnnounceService;
import me.hj.zhibo.service.IDissertationService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class StudentController {
    @Autowired
    private IDissertationService dService;
    @Autowired
    private IAnnounceService announceService;

    @GetMapping("/search")
    RespVO search(@RequestParam String words){
        return dService.search(words);
    }

    @GetMapping("/dissers/{index}/{size}")
    RespVO disserList(@PathVariable("index") int index, @PathVariable("size") int size) {

        return dService.disserList(index, size);
    }

    //查询学生志愿
    @GetMapping("/student/aspir")
    RespVO aspirartion() {
        return dService.myAspiration();
    }

    // 提交选题志愿
    @PostMapping("/student/save")
    RespVO saveDisser(@RequestParam int did) {
        return dService.saveDisser(did);
    }

    //退选志愿
    @PostMapping("/student/abort")
    RespVO abort(@RequestParam int did){
        return dService.abort(did);
    }

    // 获取指导老师的通知
    @GetMapping("/stu/anno/{index}/{size}")
    RespVO page(@PathVariable("index") Integer index,@PathVariable("size") Integer size){
        return announceService.getPageByUid(index,size);
    }
    // 查询审核结果
    @GetMapping("/student/audit")
    RespVO status(){
        return dService.getStatus();
    }

    // 提交审核
    @PostMapping("/student/audit")
    RespVO submit(@RequestParam MultipartFile file){
        return dService.submitAudit(file);
    }

}
