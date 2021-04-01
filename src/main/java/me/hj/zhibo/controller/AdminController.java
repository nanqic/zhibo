package me.hj.zhibo.controller;

import me.hj.zhibo.service.IAdminService;
import me.hj.zhibo.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @GetMapping("/users/{index}/{size}")
    RespVO userList(@PathVariable("index") int index, @PathVariable("size") int size) {
        return adminService.userList(index, size);
    }

    @PatchMapping("/reset/{username}")
    RespVO resetUser(@PathVariable String username) {
        return adminService.resetUser(username);
    }

    @PatchMapping("/delete/{username}")
    RespVO deleteUser(@PathVariable String username) {
        return adminService.deleteUser(username);
    }

    @PatchMapping("/disable/{username}")
    RespVO disableUser(@PathVariable String username) {
        return adminService.disableUser(username);
    }

    @PatchMapping("/enable/{username}")
    RespVO enableUser(@PathVariable String username) {
        return adminService.enableUser(username);
    }
}
