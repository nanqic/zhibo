package me.hj.zhibo.controller;

import me.hj.zhibo.service.IAdminService;
import me.hj.zhibo.service.IUserService;
import me.hj.zhibo.vo.AddStudentVO;
import me.hj.zhibo.vo.RespVO;
import me.hj.zhibo.vo.AddTeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IUserService userService;

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

    /*
     *添加新的老师
     */
    @PostMapping("/t")
    RespVO addTeacher(@RequestBody AddTeacherVO vo) {
        return adminService.addTeacher(vo);
    }

    /*
     *添加新的学生
     */
    @PostMapping("/s")
    RespVO addStudent(@RequestBody AddStudentVO vo) {
        return adminService.addStudent(vo);
    }

    /*
    获取老师列表
     */
    @GetMapping("/teachers")
    RespVO teachers() {
        return adminService.getTeachers();
    }
}
