package me.hj.zhibo.controller;

import me.hj.zhibo.vo.UpdatePasswordVO;
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

    @PatchMapping("/user/password")
    RespVO updatePasswd(@RequestBody UpdatePasswordVO vo) {
        return userService.updatePasswd(vo);
    }

    @GetMapping("/user/loginRes")
    RespVO getLoginResults() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getLoginResults(user.getUsername());
    }
}
