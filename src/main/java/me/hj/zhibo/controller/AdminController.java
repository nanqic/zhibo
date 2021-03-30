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

    @GetMapping("/userList/{index}/{size}")
    RespVO userList(@PathVariable("index") int index, @PathVariable("size") int size) {
        return adminService.userList(index, size);
    }

    @PutMapping("/resetUser/{username}")
    RespVO resetUser(@PathVariable String username) {
        return adminService.resetUser(username);
    }

    @PutMapping("/deleteUser/{username}")
    RespVO deleteUser(@PathVariable String username) {
        return adminService.deleteUser(username);
    }

    @PutMapping("/disableUser/{username}")
    RespVO disableUser(@PathVariable String username) {
        return adminService.disableUser(username);
    }

    @PutMapping("/enableUser/{username}")
    RespVO enableUser(@PathVariable String username) {
        return adminService.enableUser(username);
    }
}
