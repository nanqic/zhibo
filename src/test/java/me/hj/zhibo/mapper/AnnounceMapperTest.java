package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.entity.Announce;
import me.hj.zhibo.vo.AnnounceVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class AnnounceMapperTest {
    @Autowired
    AnnounceMapper mapper;

    @Test
    void test(){

        Page<AnnounceVO> page = new Page<>(1,1);
        IPage<AnnounceVO> iPage = mapper.getListByUid(41,page);
        System.out.println(iPage);
    }

    @Test
    void insert(){
        Announce a = new Announce()
                .setTitle("new")
                .setContent("qqq");
        mapper.insert(a);
    }

}