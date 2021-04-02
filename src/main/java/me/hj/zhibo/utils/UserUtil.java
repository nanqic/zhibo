package me.hj.zhibo.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtil {
    public static UserDetails getCurrentUser(){
        return (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
