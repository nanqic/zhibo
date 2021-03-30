package me.hj.zhibo.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.vo.LoginResultsVO;
import me.hj.zhibo.vo.UserListVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void selectTest() {
//        LoginResultsVO vo = userMapper.getLoginResultsVO("admin");
//        System.out.println(vo);
        Page<UserListVO> page = new Page<UserListVO>(1,2);
        IPage<UserListVO> iPage = userMapper.getUserList(page);
        System.out.println(iPage);

    }
}