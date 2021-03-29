package me.hj.zhibo.mapper;


import me.hj.zhibo.vo.LoginResultsVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void selectTest() {
        LoginResultsVO vo = userMapper.getLoginResultsVO("admin");
        System.out.println(vo);

    }
}