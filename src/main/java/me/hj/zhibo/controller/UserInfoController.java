package me.hj.zhibo.controller;


import me.hj.zhibo.service.IUserInofService;
import me.hj.zhibo.vo.NewPasswordVO;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private IUserInofService userInfoService;
    @GetMapping("/info/{uid}")
    RespVO userInfo(@PathVariable("uid") int uid){
//        Integer uid = (int)session.getAttribute("uid");
        if (uid>40)
            return userInfoService.getStudents(uid);

        return userInfoService.getTeacher(uid);
    }


    @GetMapping ("/teacherInfo/{name}")
    RespVO getInfoByName(@PathVariable("name") String name){
        return  userInfoService.getInfoByName(name);
    }

}
