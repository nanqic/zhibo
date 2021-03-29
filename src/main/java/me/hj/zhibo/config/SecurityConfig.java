package me.hj.zhibo.config;


import me.hj.zhibo.handler.LocalAuthenticationFailureHandler;
import me.hj.zhibo.handler.LocalAuthenticationSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/loginReg.html")// 自定义登录页面
                .loginProcessingUrl("/login")// 登录请求的URL
                .successHandler(new LocalAuthenticationSuccessHandler()) // 登录验证成功后, 执行的内容
                .failureHandler(new LocalAuthenticationFailureHandler()) // 登录验证失败后, 执行的内容
                .permitAll();
        // 授权
        http.authorizeRequests()
                .antMatchers("/register").anonymous()
                .anyRequest().authenticated();// 所有请求页面必须授权后才能访问
        // 关闭csrf防护
        http.csrf().disable();
        // 禁用XFrame防护
        http.headers().frameOptions().disable();

        // 自定义注销信息
        http.logout() //
                .logoutUrl("/logout") // 登出验证api
//        .logoutSuccessHandler(new LocalLogoutSuccessHandler()) // 登录验证成功后, 执行的内容
//                 .logoutSuccessUrl("/loginReg.html") // 登录验证成功后, 跳转的页面, 如果自定义返回内容, 请使用logoutSuccessHandler方法
                .deleteCookies("JSESSIONID") // 退出登录后需要删除的cookies名称
                .deleteCookies("username") // 退出登录后需要删除的cookies名称
                .invalidateHttpSession(true) // 退出登录后, 会话失效
                .permitAll();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 放行静态资源，不走 Spring Security 过滤器链
        web.ignoring().antMatchers("/css/**","/js/**","/bootstrap/**","/favicon.ico");
    }

}

