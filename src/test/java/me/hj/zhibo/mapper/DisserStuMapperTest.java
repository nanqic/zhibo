package me.hj.zhibo.mapper;

import lombok.extern.slf4j.Slf4j;
import me.hj.zhibo.entity.DisserStu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class DisserStuMapperTest {
    @Autowired
    DisserStuMapper mapper;

    @Test
    void selectTest(){
        List list = mapper.isSelected(3,3);
        log.info("---------List: {}", list);
        System.out.println(list.isEmpty());
    }
    @Test
    void deleteTest(){
        mapper.deleteById(5);
    }

}