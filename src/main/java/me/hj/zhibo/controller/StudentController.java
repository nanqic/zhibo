package me.hj.zhibo.controller;

import me.hj.zhibo.service.IDissertationService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private IDissertationService dService;

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
    @GetMapping("/student/abort")
    RespVO abort(@RequestParam int did){
        return dService.abort(did);
    }
}
