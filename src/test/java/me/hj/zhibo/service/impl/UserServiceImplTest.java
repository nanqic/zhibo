package me.hj.zhibo.service.impl;


import me.hj.zhibo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private IUserService userService;

    @Test
    void registerTest(){
//        UserLoginVO r =new UserLoginVO()
//                .setAccount(202101014105L)
//                .setName("周五").setPassword("123");
//        System.out.println(userService.register(r));
    }

    @Test
    void loginTest(){

    }

    @Test
    void updatePwTest(){

    }

    @Test
    void tests(){
        System.out.println(userService.userList(1, 3));
    }


}