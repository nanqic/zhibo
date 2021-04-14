package me.hj.zhibo.service.impl;

import me.hj.zhibo.service.IAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    private IAdminService service;
    @Test
    void searchTest(){
        service.searchUser(1,10,"410");
    }

}