package me.hj.zhibo.service.impl;

import me.hj.zhibo.entity.Announce;
import me.hj.zhibo.service.IAnnounceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnnounceServiceImplTest {
    @Autowired
    IAnnounceService service;
    @Test
    void newTest(){
        Announce a = new Announce().setTitle("123").setContent("345");
        service.newAnno("111","222");
    }
}