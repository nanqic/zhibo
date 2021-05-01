package me.hj.zhibo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.Announce;
import me.hj.zhibo.service.IAnnounceService;
import me.hj.zhibo.vo.AnnounceVO;
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
    @Test
    void test(){

    }
}