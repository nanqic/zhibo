package me.hj.zhibo.handler;

import me.hj.zhibo.vo.RespVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


public class LocalAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        //获取当前用户的信息
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        // 将用户角色存到cookie中，返回给前端
        String role = user.getAuthorities().toString().replace("[", "").replace("]", "");
        Cookie cookie = new Cookie("userRole", URLEncoder.encode(role, "utf-8"));
        httpServletResponse.addCookie(cookie);
        httpServletResponse.getWriter().print(RespVO.ok("ok"));
    }
}
