package me.hj.zhibo.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetailServiceImplTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void pwTest(){
        String pw = passwordEncoder.encode("123");
        System.out.println(pw);
    }

}