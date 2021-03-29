package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.vo.LoginResultsVO;
import me.hj.zhibo.vo.UserListVO;
import me.hj.zhibo.entity.User;
import me.hj.zhibo.vo.UserLoginVO;
import me.hj.zhibo.vo.UserRegisterVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select u.uid, u.username,u.password, i.name from tb_user u, tb_user_info i where u.uid=i.uid and u.username=#{username}")
    UserRegisterVO getUserRegisterVO(String username);
    @Select("select r.role_name, u.username,i.name from tb_user u, tb_user_role r, left join tb_user_info i on u.uid=i.uid")
    IPage<UserListVO> selectUserList(Page<UserListVO> page);
    @Select("select u.username, u.password, r.role_name from tb_user u left join tb_user_role r on u.role_id=r.role_id where u.username=#{username}")
    UserLoginVO getUserLoginVO(String username);
    @Select("select u.uid,r.role_name,i.name, i.major, i.class_name, i.graduation from (tb_user u left join tb_user_role r on u.role_id=r.role_id) LEFT JOIN tb_user_info i on u.uid=i.uid where u.username=#{username}")
    LoginResultsVO getLoginResultsVO(String username);
}
