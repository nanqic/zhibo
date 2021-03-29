package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.hj.zhibo.entity.Counselor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CounselorMapperTest {
    @Autowired
    private CounselorMapper mapper;
    @Test
    void test(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.getSqlFirst();
        mapper.selectList(wrapper);
    }
    @Test
    void insert(){
        Counselor c = new Counselor();
        int uid=1;
        for (int o=41;o<=44;o++){
            c.setTeachUid(o);
            for (int i=1;i<=10;i++){
                c.setStuUid(uid);
//                System.out.println(c.getStuUid()+"---"+c.getTechUid());
//            mapper.insert(c);
                uid++;
            }
        }


    }


}