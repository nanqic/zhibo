package me.hj.zhibo.controller;

import me.hj.zhibo.vo.NewPasswordVO;
import me.hj.zhibo.vo.RespVO;
import me.hj.zhibo.service.IUserService;
import me.hj.zhibo.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    RespVO register(@RequestBody UserRegisterVO vo) {
        return userService.register(vo);
    }

    @PostMapping("/user/newPassword")
    RespVO updatePasswd(@RequestBody NewPasswordVO vo) {
        System.out.println(vo);
        return userService.updatePasswd(vo);
    }
    @GetMapping("/user/list/{index}/{size}")
    RespVO userList(@PathVariable("index") int index, @PathVariable("size") int size) {
        return userService.userList(index, size);
    }
    @GetMapping("/user/loginRes")
    RespVO getLoginResults(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getLoginResults(user.getUsername());
    }
}
