package me.hj.zhibo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserListVO {
    private Integer uid;
    private String name;
    private String username;
    private String roleName;
    private Integer enabled;
}
