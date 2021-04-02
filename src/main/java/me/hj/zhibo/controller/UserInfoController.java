package me.hj.zhibo.controller;

import me.hj.zhibo.service.IUserInofService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserInfoController {

    @Autowired
    private IUserInofService userInfoService;

    @GetMapping("/stu/info")
    RespVO stuInfo() {
        return userInfoService.getStudentInfo();
    }

    @GetMapping("/teacher/info")
    RespVO teacherInfo() {
        return userInfoService.getTeacherInfo();
    }
    @GetMapping("/teacherInfo/{name}")
    RespVO getInfoByName(@PathVariable("name") String name) {
        return userInfoService.getInfoByName(name);
    }

}
