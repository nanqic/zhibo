package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import me.hj.zhibo.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper mapper;

    @Test
    void selectTest(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid",6);
        mapper.selectOne(wrapper);
    }

    @Test
    void updateTest(){
//        UpdateWrapper wrapper = new UpdateWrapper();
//        wrapper.eq("uid",6);
//        UserInfo userInfo = new UserInfo()
//                .setUid(6)
//                .setMajor("计算机科学")
//        mapper.update(userInfo, wrapper);

    }
    @Test
    void insertTest(){
        String[] names = {"","二","三","四","五","六","七","八","九","十"};
        String stu = "董";
        String stu2 = "秋";
        Integer uid = 31;

        for (String name:names) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(uid)
                    .setName(stu+name+stu2);
            mapper.updateById(userInfo);
//            System.out.println(stu+name);
//            System.out.println(uid);
            uid++;
        }
    }

}