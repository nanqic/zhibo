package me.hj.zhibo.service.impl;

import me.hj.zhibo.service.IUserInofService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoServiceImplTest {
    @Autowired
    IUserInofService service;

    @Test
    void test(){
        System.out.println(service.getTeacher(2));
    }

    @Test
    void testTeach(){
        System.out.println(service.getStudents(41));
    }

}