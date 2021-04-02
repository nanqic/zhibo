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
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select u.uid, u.username,u.password, i.name from tb_user u, tb_user_info i where u.uid=i.uid and u.username=#{username}")
    UserRegisterVO getUserRegister(String username);

    // 只查询老师和学生用户
    @Select("select u.uid, u.username, u.enabled, r.role_name, i.name from (tb_user u left join tb_user_role r on u.role_id=r.role_id) left join tb_user_info i on u.uid=i.uid WHERE enabled<>4 and u.role_id BETWEEN 1 and 2")
    IPage<UserListVO> getUserList(Page<UserListVO> page);

    // 登录时，查询正常用户
    @Select("select u.uid, u.username, u.password, u.enabled, r.role_name from tb_user u left join tb_user_role r on u.role_id=r.role_id where u.enabled=1 and u.username=#{username}")
    UserLoginVO getUserLogin(String username);

    @Select("select u.uid,r.role_name,i.name, i.major, i.class_name, i.graduation from (tb_user u left join tb_user_role r on u.role_id=r.role_id) LEFT JOIN tb_user_info i on u.uid=i.uid where u.username=#{username}")
    LoginResultsVO getLoginResults(String username);

    @Update("UPDATE tb_user set password='' WHERE username=#{username}")
    int resetUser(String username);

    @Update("UPDATE tb_user set enabled=0 WHERE username=#{username}")
    int disableUser(String username);

    @Update("UPDATE tb_user set enabled=1 WHERE username=#{username}")
    int enableUser(String username);

    // 软删除，设置enabled=4
    @Update("update tb_user set enabled=4 where username=#{username}")
    int deleteUser(String username);

    @Update("update tb_user set password=#{password} where username=#{username}")
    int updatePassword(String password,String username);

    @Select("select uid from tb_user where username=#{username}")
    int getUid(String username);
}
