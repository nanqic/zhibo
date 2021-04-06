package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.vo.DissertationVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DissertationMapperTest {
    @Autowired
    DissertationMapper mapper;

    @Test
    void test(){
        Page<DissertationVO> page = new Page<>(1,20);

        System.out.println(mapper.search("%t%",page));
    }

}