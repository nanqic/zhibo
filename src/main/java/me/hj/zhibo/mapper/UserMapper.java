package me.hj.zhibo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hj.zhibo.vo.*;
import me.hj.zhibo.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select uid, username,password, name from v_user_info WHERE username=#{username}")
    UserRegisterVO getUserRegister(String username);

    // 只查询老师和学生用户
    @Select("select u.uid, u.username, u.enabled, r.role_name, i.name from (tb_user u left join tb_user_role r on u.role_id=r.role_id) left join v_user_info i on u.uid=i.uid WHERE enabled<>4 and u.role_id BETWEEN 1 and 2")
    IPage<UserListVO> getUserList(Page<UserListVO> page);

    @Select("SELECT\n" +
            "\tu.uid,\n" +
            "\tu.username,\n" +
            "\tu.enabled,\n" +
            "\tr.role_name,\n" +
            "\ti.NAME \n" +
            "FROM\n" +
            "\t( tb_user u LEFT JOIN tb_user_role r ON u.role_id = r.role_id )\n" +
            "\tLEFT JOIN v_user_info i ON u.uid = i.uid \n" +
            "WHERE\n" +
            "\tu.username LIKE #{username} \n" +
            "\tAND enabled <> 4 \n" +
            "\tAND u.role_id BETWEEN 1 \n" +
            "\tAND 2")
    IPage<UserListVO> searchUser(Page<UserListVO> page,String username);

    // 登录时，查询正常用户
    @Select("select u.uid, u.username, u.password, u.enabled, r.role_name from tb_user u left join tb_user_role r on u.role_id=r.role_id where u.enabled=1 and u.username=#{username}")
    UserLoginVO getUserLogin(String username);

    // 查询老师登录信息
    @Select("select u.uid,r.role_name,i.name, i.dept, i.major, i.job_title, i.degree from (tb_user u join tb_user_role r on u.role_id=r.role_id) LEFT JOIN v_teacher_info i on u.uid=i.uid where u.username=#{username}")
    TeLoginResultsVO getTeLoginResults(String username);

    // 查询学生登录信息
    @Select("select u.uid,r.role_name,i.name, i.dept, i.major, i.class_name, i.graduation from (tb_user u join tb_user_role r on u.role_id=r.role_id) LEFT JOIN v_student_info i on u.uid=i.uid where u.username=#{username}")
    StuLoginResultsVO getStuLoginResults(String username);

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
    int updatePassword(String password, String username);

    @Select("select uid from tb_user where username=#{username}")
    int getUid(String username);

    @Select("select role_id from tb_user where username=#{username}")
    int getRole(String username);
}
