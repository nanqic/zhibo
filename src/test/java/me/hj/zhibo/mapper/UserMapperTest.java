package me.hj.zhibo.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.User;
import me.hj.zhibo.vo.UserListVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Test
    void selectTest() {
//        StuLoginResultsVO vo = userMapper.getLoginResultsVO("admin");
//        System.out.println(vo);
        Page<UserListVO> page = new Page<UserListVO>(1,2);
        IPage<UserListVO> iPage = userMapper.getUserList(page);
        System.out.println(iPage);

    }
    @Test
    void insertTest(){
        User user = new User()
                .setUsername("test");
        int row = userMapper.insert(user);
        int key = user.getUid();
        System.out.println(row);
        System.out.println(key);
    }
}